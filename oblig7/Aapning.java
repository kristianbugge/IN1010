import java.util.ArrayList;

public class Aapning extends HvitRute{

    public Aapning(char tegn, int x, int y, Labyrint labyrint) {
        super(tegn, x, y, labyrint);
    }

    protected Rute gaa(ArrayList<Tuppel> sti) {
        if( erStartRute(this.x, this.y) == false){
            Labyrint.logg("Fant utvei på koordinat: ("+x+","+y+")");
            sti.add(new Tuppel(x, y));
            labyrint.sti.add(sti);
            return this;
        } else {
            return super.gaa(sti);
        }
    }
}
