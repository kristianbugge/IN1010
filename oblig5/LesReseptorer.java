import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class LesReseptorer implements Runnable{

    String filnavn;
    int sekvenslengde;
    boolean erSmittet;
    ImmunReseptorBeholder ikkeSmittetAvVirusBeholder;
    ImmunReseptorBeholder smittetAvVirusBeholder;


    public LesReseptorer(String filnavn, int sekvenslengde, boolean erSmittet,
                           ImmunReseptorBeholder ikkeSmittetAvVirusBeholder, ImmunReseptorBeholder smittetAvVirusBeholder) {
        this.filnavn = filnavn;
        this.sekvenslengde = sekvenslengde;
        this.erSmittet = erSmittet;
        this.ikkeSmittetAvVirusBeholder = ikkeSmittetAvVirusBeholder;
        this.smittetAvVirusBeholder = smittetAvVirusBeholder;
    }

    @Override
    public void run() {
        File fil = new File(filnavn);

        Scanner in;
        try {
            in = new Scanner(fil);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            in = new Scanner("");
        }
        ImmunReseptor immunReseptor = new ImmunReseptor(erSmittet);
        while (in.hasNextLine()) {
            int startIndeks = 0;
            int stoppIndeks = sekvenslengde;

            String linje = in.nextLine();
            int linjeLengde = linje.length();
            while(stoppIndeks <= linjeLengde){
                String subSekvens = linje.substring(startIndeks, stoppIndeks);
                immunReseptor.leggTilSubSekvens(subSekvens);
                startIndeks++;
                stoppIndeks++;
            }
        }
        if (erSmittet){
            smittetAvVirusBeholder.leggTilReseptor(immunReseptor);
        } else {
            ikkeSmittetAvVirusBeholder.leggTilReseptor(immunReseptor);
        }

        System.out.println("ferdig");

    }
}
