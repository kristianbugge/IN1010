class Militaer extends Hvit{

    public Militaer(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }
    @Override
    public int prisAaBetale(){
        return 0;
    }

    @Override
    public String toString(){
        return super.toString()+", Farge: "+farge()+", Pris: "+prisAaBetale();
    }
}
