abstract class Resept {
    private int id = 0;
    private static int idteller = 1;
    protected Lege utskrivendeLege;
    protected Legemiddel legemiddel;
    protected Pasient pasient;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.id = idteller++;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
    }

    public int hentId(){ return id; }

    public Legemiddel hentLegemiddel(){ return legemiddel; }

    public Lege hentLege(){ return utskrivendeLege; }

    public int hentPasientId(){ return pasient.hentPasientId(); }

    public int hentReit(){ return reit; }

    public boolean bruk(){
        if (reit > 0) {
            reit -= 1;
            return true;
        } else {
            return false;
        }
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    @Override
    public String toString(){
        return hentLegemiddel().hentNavn()+", ReseptID: "+hentId()+", Utskrivende lege: "+hentLege().hentNavn()+", Pasientens ID: "+hentPasientId()+", Reit: "+hentReit()+'\n';
    }
}
