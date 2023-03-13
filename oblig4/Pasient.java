public class Pasient {
    protected String navn = "";
    private int id = 0;
    private static int idteller = 1;
    protected String foedselsNr = "";
    protected Stabel<Resept> reseptListe = new Stabel<Resept>();

    public Pasient(String navn, String foedselsNr){
        this.navn = navn;
        this.foedselsNr = foedselsNr;
        this.id = idteller++;
    }

    public int hentPasientId(){ return id; }

    public String hentPasientNavn(){ return navn; }

    public String hentPasientFoedselsNr(){ return foedselsNr; }

    public void leggTilResept(Resept resept){
        reseptListe.leggPaa(resept);
    }

    public Stabel<Resept> hentReseptListe(){ return reseptListe; }

    @Override
    public String toString() {
        return "Pasient: "+hentPasientNavn()+", ID: "+id+", Foedselsnummer: "+hentPasientFoedselsNr()+'\n';
    }
}
