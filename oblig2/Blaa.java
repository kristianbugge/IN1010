class Blaa extends Resept{

    public Blaa(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    @Override
    public String farge(){
        return "Blå";
    }

    @Override
    public int prisAaBetale() {
        return (int) Math.round(hentLegemiddel().hentPris() * 0.25);
    }

    @Override
    public String toString(){
        return super.toString()+", Farge: "+farge()+", Pris: "+prisAaBetale();
    }
}
