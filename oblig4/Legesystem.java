 import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Legesystem {

    static Liste<Pasient> pasientListe = new Lenkeliste<Pasient>();
    static Liste<Legemiddel> legemiddelListe = new Lenkeliste<Legemiddel>();
    static Liste<Lege> legeListe = new SortertLenkeliste<Lege>();

    public Legesystem(String filnavn) {
        lesFraFil(filnavn);
    }
    public static void main(String[] args) {
        String filNavn = null;
        if (args[0] != null){
            filNavn = args[0];
        }

        Legesystem legesystem = new Legesystem(filNavn);
//        lesFraFil(filNavn);
        Scanner command = new Scanner(System.in);



        System.out.println("Skriv kommando(pasienter, leger, legemidler, resepter all info eller avslutt): ");
        boolean running = true;

        while(running){

            switch(command.nextLine()){

                case "pasienter":
                    System.out.println(legesystem.pasientListe);
                    break;
                case "leger":
                    System.out.println(legesystem.legeListe);
                    break;
                case "legemidler":
                    System.out.println(legesystem.legemiddelListe);
                    break;
                case "resepter":
                    for (Lege l: legeListe){
                        System.out.println(l.hentUtskrevedeResepter());
                    }
                    break;
                case "avslutt":
                    System.out.println("Avslutter...");
                    running = false;
                    break;
                case "all info":
                    System.out.println("Leger:"+'\n'+legesystem.legeListe+'\n'+"Pasienter:"+'\n'+legesystem.pasientListe+'\n'+"Leger: "+'\n'+legesystem.legemiddelListe+'\n'+"Resepter");
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldig kommando");
                    break;
            }
        }
        command.close();
    }

    public static int parseInt(String s){
        int i = Integer.parseInt(s);
        return i;
    }

    public static void lesFraFil(String filnavn) {
        File fil = new File(filnavn);
        String innDataType = "";
        Scanner in;
        try {
            System.out.println("abs path: "+new File(".").getAbsolutePath());
            in = new Scanner(fil);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            in = new Scanner("");
        }
        while (in.hasNextLine()) {

            String linje = in.nextLine();
            if (linje.startsWith("#")) {
                if (linje.equals("# Pasienter (navn, fnr)")) {
                    System.out.println("Leser pasienter....");
                    innDataType = "Pasient";
                    continue;
                } else if (linje.equals("# Legemidler (navn,type,pris,virkestoff,[styrke])")) {
                    System.out.println("Leser legemidler...");
                    innDataType = "Legemidler";
                    continue;
                } else if (linje.equals("# Leger (navn,kontrollid / 0 hvis vanlig lege)")) {
                    System.out.println("Leser Leger...");
                    innDataType = "Leger";
                    continue;
                } else if (linje.equals("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])")) {
                    System.out.println("Leser Resepter...");
                    innDataType = "Resepter";
                    continue;
                }
            }

            if (innDataType.equals("Pasient")){
                String[] alleData = linje.split(",");
                Pasient p = new Pasient(alleData[0], alleData[1]);
                pasientListe.leggTil(p);
            } else if (innDataType.equals("Legemidler")){
                String[] alleData = linje.split(",");
                switch (alleData[1]) {
                    case "narkotisk":
                        Narkotisk n = new Narkotisk(alleData[0], parseInt(alleData[2]), parseInt(alleData[3]), parseInt(alleData[4]));
                        legemiddelListe.leggTil(n);
                        break;
                    case "vanedannende":
                        Vanedannende v = new Vanedannende(alleData[0], parseInt(alleData[2]), parseInt(alleData[3]), parseInt(alleData[4]));
                        legemiddelListe.leggTil(v);
                        break;
                    case "vanlig":
                        Vanlig va = new Vanlig(alleData[0], parseInt(alleData[2]), parseInt(alleData[3]));
                        legemiddelListe.leggTil(va);
                        break;
                }

            } else if (innDataType.equals("Leger")){
                String[] alleData = linje.split(",");
                if (parseInt(alleData[1]) == 0){
                    Lege lege = new Lege(alleData[0]);
                    legeListe.leggTil(lege);
                }
                else {
                    Spesialist spesialist = new Spesialist(alleData[0], alleData[1]);
                    legeListe.leggTil(spesialist);}

            } else if (innDataType.equals("Resepter")){
                String[] alleData = linje.split(",");
                Lege funnetLege = finnLege(alleData[1]);
                Legemiddel legemiddel = finnLegemiddel((alleData[0]));
                Pasient pasient = finnPasient((alleData[2]));

                try {
                    if (funnetLege != null){
                        if (alleData[3] != null && alleData[3].length() == 1 && alleData[3].charAt(0) == 'p') {
                            PResept pResept = funnetLege.skrivResept(legemiddel, pasient);
                        } else {
                            Resept resept = funnetLege.skrivResept(legemiddel, pasient, Integer.parseInt(alleData[4]));
                        }
                    }

                } catch (UlovligUtskrift ulovligUtskrift) {
                    ulovligUtskrift.getMessage();
                    continue;
                }
            }
            }
        }

    private static Lege finnLege (String input) {
        for (Lege l: legeListe){
            if (l.hentNavn().equals(input)){
                return l;
            }
        }
        return null;
    }
    private static Pasient finnPasient (String input) {
        int intInput = parseInt(input);
        for (Pasient p : pasientListe){
            if (p.hentPasientId() == intInput){
                return p;
            }
        }
        return null;
    }
    private static Legemiddel finnLegemiddel (String input) {
        int intInput = parseInt(input);
        for (Legemiddel l : legemiddelListe){
            if (l.hentId() == intInput){
                return l;
            }
        }
        return null;
    }
}
