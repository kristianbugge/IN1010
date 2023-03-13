import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Labyrint {

    public int antRader;
    public int antKolonner;

    public int startx;
    public int starty;

    public Rute[][] ruter;
    public boolean[][] besokt;

    File fil = null;

    ArrayList<ArrayList<Tuppel>> sti = null;

    static boolean verbose;

    public Labyrint(File fil, boolean verbose) throws FileNotFoundException {
        this.fil = fil;
        lesLabyrint(fil);
        this.verbose = verbose;
        System.out.println(this);
    }

    public Rute hentRute(int x, int y){
        return ruter[y][x];
    }

    private void lesLabyrint(File fil) throws FileNotFoundException {
        int j = 0;
        Scanner in = new Scanner(fil);
        String linje = in.nextLine();
        String[] alleData = linje.split(" ");
        this.antRader = Integer.parseInt(alleData[0]);
        this.antKolonner = Integer.parseInt(alleData[1]);

        this.ruter = new Rute[this.antRader][this.antKolonner];
        this.besokt = new boolean[this.antRader][this.antKolonner];
        for (int i = 0; i < this.antRader && in.hasNextLine(); i++) {
            linje = in.nextLine();
            j = 0;
            for(char c : linje.toCharArray()) {
                this.ruter[i][j] = lagEnRute(c, j, i);
                j++;
            }
        }
        return;
    }

    public int getAntRader() {
        return antRader;
    }

    public int getAntKolonner() {
        return antKolonner;
    }

    public Rute lagEnRute(char c, int i , int j){
        if (c == '.'){
            if (i == antKolonner -1 || i == 0 || j == antRader -1 || j == 0){
                return new Aapning(c, i , j, this);
            }
            else
                return new HvitRute(c, i, j, this);
        }
        else if (c == '#'){
            return new SortRute(c, i , j, this);
        }
        return null;
    }

    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int startx, int starty){
        nullstillData();
        this.startx = startx;
        this.starty = starty;
        logg( "Starkoordinat: (" + startx + "," + starty + ")");
        Rute startRute = hentRute(startx, starty);
        startRute.finnUtvei(startx, starty);
        if (this.sti.size() == 0)
            logg("Fant ingen utveier...\n");
        return this.sti;
    }



    public boolean erGyldigRute(int x, int y){
        if (x < 0 || x > antKolonner || y < 0 || y > antRader){
            return false;
        }
        return true;
    }

    public boolean erBesokt(int x, int y){
        return besokt[y][x];
    }

    public void settBesokt(int x, int y){
        besokt[y][x] = true;
    }

    private void nullstillData(){
        if (sti != null){
            sti.clear();
        }
        sti = new ArrayList<>();
        for (int y = 0; y < antRader; y++){
            for (int x = 0; x < antKolonner; x++){
                besokt[y][x] = false;
            }
        }

    }

    @Override
    public String toString() {
        String s = "Labyrint lest: \n";
        for (int i = 0; i < ruter.length; i++) {
            for (int j = 0; (ruter[i] != null && j < ruter[i].length); j++) {
                s += (ruter[i][j].tilTegn() + " ");
            }
            s+="\n";
        }
        return s;
    }
    public static void logg(String s){
        if (verbose == true){
            System.out.println(s);
        }
    }


}
