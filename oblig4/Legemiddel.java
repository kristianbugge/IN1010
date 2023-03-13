abstract class Legemiddel {
    protected String navn;
    private int id = 0;
    private static int idteller = 1;
    protected int pris = 0;
    protected double virkestoff;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = idteller++;
    }

    public int hentId(){
        return id;
    }

    public String hentNavn(){ return navn; }

    public int hentPris(){ return pris; }

    public double hentVirkestoff(){ return virkestoff; }

    @Override
    public String toString(){
        return hentNavn()+", LegemiddelID: "+hentId()+", Virkestoff: "+hentVirkestoff()+" mg, Pris: "+hentPris()+" kr";
    }
}
