abstract class Resept {
    private int id = 0;
    private static int idteller = 0;
    protected Lege utskrivendeLege;
    protected Legemiddel legemiddel;
    protected int pasientId;
    protected int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        this.id = idteller++;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
    }

    public int hentId(){ return id; }

    public Legemiddel hentLegemiddel(){ return legemiddel; }

    public Lege hentLege(){ return utskrivendeLege; }

    public int hentPasientId(){ return pasientId; }

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
        return hentLegemiddel()+", ID: "+hentId()+", Utskrivende lege: "+hentLege()+", Legemiddel: "+hentLegemiddel()+", Pasientens ID: "+hentPasientId()+", Reit: "+hentReit();
    }
}
