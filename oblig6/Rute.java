import java.util.ArrayList;

abstract class Rute {
    private char tegn;
    protected int x;
    protected int y;
    Labyrint labyrint;

    public Rute( char tegn, int x, int y, Labyrint labyrint){
        this.tegn = tegn;
        this.x = x;
        this.y = y;
        this.labyrint = labyrint;
    }

    abstract protected Rute gaa(ArrayList<Tuppel> sti);

    public Rute nord() { return sjekkRuteGyldig(x, y -1);}
    public Rute syd() { return sjekkRuteGyldig(x, y +1);}
    public Rute vest() { return sjekkRuteGyldig(x -1, y);}
    public Rute ost() { return sjekkRuteGyldig(x +1, y);}

    public boolean finnUtvei(int x, int y){
        ArrayList<Tuppel> sti = new ArrayList<>();
        Rute r = labyrint.hentRute(x, y).gaa(sti);
        return false;
    }

    private Rute sjekkRuteGyldig(int x, int y){
        if ((x >= 0 && x < labyrint.antKolonner) && (y >= 0 && y < labyrint.antRader)){
            return labyrint.hentRute(x,y);
        } else {
            return null;
        }
    }

    public boolean erStartRute(int kol, int rad){
        if (labyrint.startx == kol && labyrint.starty == rad){
            return true;
        }
        return false;
    }

    public char tilTegn(){
        return this.tegn;
    }
}
