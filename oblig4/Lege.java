public class Lege implements Comparable<Lege>{
    protected String navn = "";
    private int id = 0;
    private static int idteller = 1;
    protected Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();

    public Lege(String navn){
        this.navn = navn;
        this.id = idteller++;
    }

    public String hentNavn(){ return navn; }

    public Lenkeliste<Resept> hentUtskrevedeResepter() { return utskrevedeResepter; }

    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }
        Hvit hvitResept = new Hvit(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(hvitResept);
        return hvitResept;
    }

    public PResept skrivResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if (legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this, legemiddel);
        }
        PResept pResept = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(pResept);
        return pResept;
    }

    @Override
    public String toString() {
        return "Lege: "+hentNavn()+", ID: "+id+'\n';
    }

    @Override
    public int compareTo(Lege l) {
        int verdi = this.navn.compareTo(l.navn);
        System.out.println("Verdi: "+ verdi);
        return verdi;
    }
}
