class Spesialist extends Lege implements Godkjenningsfritak{
    protected String kontrollID = "";

    public Spesialist(String navn, String kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    public String hentKontrollID() {
        return kontrollID;
    }

    @Override
    public String toString() {
        return super.toString()+hentNavn()+ " er spesialist, med Kontroll-ID: " + hentKontrollID()+'\n';

    }

    @Override
    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit){
        Hvit hvitResept = new Hvit(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(hvitResept);
        return hvitResept;
    }
}