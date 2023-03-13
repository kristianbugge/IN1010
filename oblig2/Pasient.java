public class Pasient {
    protected String navn = "";
    private int id = 0;
    private static int idteller = 0;
    protected String foedselsNr = "";
    protected Stabel<Resept> reseptListe = new Stabel<Resept>();

}
