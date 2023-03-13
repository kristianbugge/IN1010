public class Lege {
    protected String navn = "";
    private int id = 0;
    private static int idteller = 0;

    public Lege(String navn){
        this.navn = navn;
        this.id = idteller++;
    }


    public String hentNavn(){ return navn; }

    @Override
    public String toString() {
        return "Lege: "+hentNavn()+", ID: "+id;
    }
}
