import java.util.ArrayList;

public class HvitRute extends Rute{

    public HvitRute(char tegn, int x, int y, Labyrint labyrint) {
        super(tegn, x, y, labyrint);

    }

    protected Rute gaa(ArrayList<Tuppel> sti) {

        // Uthoppskriterie
        if ( !labyrint.erGyldigRute(x, y) || labyrint.erBesokt(x, y) ){
            return null;
        }

        // Ta vare på koordinatene i stien
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        nySti.add(new Tuppel(x, y));
        labyrint.settBesokt(x, y);

        // Rekursive kall
        ost().gaa(nySti);
        syd().gaa(nySti);
        vest().gaa(nySti);
        nord().gaa(nySti);
        return null;
    }
}
