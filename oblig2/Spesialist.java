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
        return super.toString() + ",Spesialist, Kontroll-ID: " + hentKontrollID();

    }
}