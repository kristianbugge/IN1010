import java.io.File;

public class Hovedprogram {

    //For å lese inn fil skriv dette i cmd:
    // > java Hovedprogram "dataklynge.txt"

    public static void main(String[] args) {
        String filNavn = null;
        if (args[0] != null){
            filNavn = args[0];
        }
        System.out.println("Lager dataklynge basert på fil: "+filNavn);
// For å kjøre uten å lese fra fil

//        Dataklynge abel = new Dataklynge(12);
//        abel.leggTilRackOgNoder(650, 64, 1);
//
//        abel.leggTilRackOgNoder(16, 1024, 2);
//        System.out.println("Noder med minst 32 GB: "+abel.noderMedNokMinne(32));
//        System.out.println("Noder med minst 64 GB: "+abel.noderMedNokMinne(64));
//        System.out.println("Noder med minst 128 GB: "+abel.noderMedNokMinne(128));
//        System.out.println("Antall prosessorer: "+abel.antProsessorer());
//        System.out.println("Antall rack: "+abel.antRacks());

//        System.out.println(new File(".").getAbsolutePath());

// For å kjøre med å laste fra fil
        Dataklynge abel2 = new Dataklynge(filNavn);
        System.out.println("Noder med minst 32 GB: "+abel2.noderMedNokMinne(32));
        System.out.println("Noder med minst 64 GB: "+abel2.noderMedNokMinne(64));
        System.out.println("Noder med minst 128 GB: "+abel2.noderMedNokMinne(128));

        System.out.println("Antall prosessorer: "+abel2.antProsessorer());
        System.out.println("Antall rack: "+abel2.antRacks());

    }

}
