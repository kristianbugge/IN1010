import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.*;


public class Hovedprogram {

    private static ImmunReseptorBeholder smittetAvVirusBeholder = new ImmunReseptorBeholder();;
    private static ImmunReseptorBeholder ikkeSmittetAvVirusBeholder = new ImmunReseptorBeholder();;

    // Eks på hvordan kjøre:
    // > java Hovedprogram metadatafilnavn
    // eks:
    // > java metadata.csv
    //
    public static void main(String[] args) {
        List<FilnavnOgSmittestatus> metadataFileListe = null;

        Scanner in = new Scanner(System.in);
        System.out.println("Hvilken sekvenslengde vil du bruke?");
        int sekvenslengde = Integer.parseInt(in.nextLine());

        String metadatafilnavn = null;
        try {
            if (args[0] != null){
                metadatafilnavn = args[0];
            }
        } catch (Exception e) {
            System.out.println("Bruk: > java Hovedprogram katalog metadatafilnavn");
        }
        if (metadatafilnavn == null){
            System.out.println("Bruk: > java Hovedprogram katalog metadatafilnavn");
        }
        // Lese metadata-fil
        metadataFileListe = lesMetadatafil(metadatafilnavn);

        // Behandle innholdet i filene i parallell
        behandlePasientfilerIParallell(metadataFileListe, sekvenslengde);

        // Slå sammen like sub-sekvenser for de med smitte
        samleReseptorerMedSmitteIParallell();

        // Slå sammen like sub-sekvenser for de uten smittew
        samleReseptorerUtenSmitteIParallell();

        //Skriv ut statistikk
        skrivStatistikk();
    }

    private static List<FilnavnOgSmittestatus> lesMetadatafil(String filnavn){
        List<FilnavnOgSmittestatus> metadataFileListe = new ArrayList<>();
        File fil = new File(filnavn);
        Scanner in;
        try {
            in = new Scanner(fil);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            in = new Scanner("");
        }
        String linje = in.nextLine();  // Skip first line...
        while (in.hasNextLine()){
            linje = in.nextLine();
            String[] alleData = linje.split(",");
            String pasientFil = alleData[0];
            boolean erSmittet = Boolean.parseBoolean(alleData[1]);
            metadataFileListe.add(new FilnavnOgSmittestatus(pasientFil, erSmittet));
        }
        return metadataFileListe;
    }

    private static void behandlePasientfilerIParallell(List<FilnavnOgSmittestatus> metadataFileListe, int sekvenslengde){
        for (FilnavnOgSmittestatus fil : metadataFileListe) {
            System.out.println("Behandler fil: " + fil.getFilnavn() + " Er smittet: " + fil.isErSmittet());
            LesReseptorer lesRepertoar = new LesReseptorer(fil.getFilnavn(), sekvenslengde, fil.isErSmittet(),
                    ikkeSmittetAvVirusBeholder, smittetAvVirusBeholder);
            Thread traad = new Thread(lesRepertoar);
            traad.setName("LeseTraad_" + fil.getFilnavn());
            traad.start();
            try {
                traad.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }


    private static void samleReseptorerMedSmitteIParallell(){

        int i = 0;
        ImmunReseptor reseptorSamlet = smittetAvVirusBeholder.getReseptorList().get(0);
        for (ImmunReseptor reseptorSomSkalLeggesTil: smittetAvVirusBeholder.getReseptorList()) {
            if (reseptorSomSkalLeggesTil == reseptorSamlet)
                continue;
            SamleReseptorer samleRepertoar = new SamleReseptorer(reseptorSamlet, reseptorSomSkalLeggesTil);
            Thread samleTraad = new Thread(samleRepertoar);
            samleTraad.setName("samleTraad_" + i++);
            samleTraad.start();
            try {
                samleTraad.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void samleReseptorerUtenSmitteIParallell(){

        int i = 0;
        ImmunReseptor reseptorSamlet = ikkeSmittetAvVirusBeholder.getReseptorList().get(0);
        for (ImmunReseptor reseptorSomSkalLeggesTil: ikkeSmittetAvVirusBeholder.getReseptorList()) {
            if (reseptorSomSkalLeggesTil == reseptorSamlet)
                continue;
            SamleReseptorer samleRepertoar = new SamleReseptorer(reseptorSamlet, reseptorSomSkalLeggesTil);
            Thread samleTraad = new Thread(samleRepertoar);
            samleTraad.setName("samleTraad_" + i++);
            samleTraad.start();
            try {
                samleTraad.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void skrivStatistikk(){
        String subSekvens = "";
        int antallSmittet = 0;
        int antallIkkeSmittet = 0;
        int differanse = 0;

        ImmunReseptor reseptorSmittet = smittetAvVirusBeholder.getReseptorList().get(0); // Denne inneholder samlet resultat
        ImmunReseptor reseptorIkkeSmittet = ikkeSmittetAvVirusBeholder.getReseptorList().get(0);

        System.out.println("                      Statistikk");
        System.out.println("---------------------------------------------------------");
        System.out.println("Sub-sekvens   Ant.smittet   Ant.ikke smittet   differanse");
        for (Map.Entry<String, Integer> smittet : reseptorSmittet.getReseptorMap().entrySet()) {
            subSekvens = smittet.getKey();
            antallSmittet = smittet.getValue();
            antallIkkeSmittet = 0;
            if (reseptorIkkeSmittet.getReseptorMap().containsKey(smittet.getKey())){
                antallIkkeSmittet = reseptorIkkeSmittet.getReseptorMap().get(smittet.getKey());
            }
            differanse = antallSmittet - antallIkkeSmittet;
            if (differanse >= 5){
                System.out.printf( "%-12s %5s %15s %15s%n", subSekvens, antallSmittet,antallIkkeSmittet,differanse);
            }
        }
    }
}
