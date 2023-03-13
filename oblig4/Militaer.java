class Militaer extends Hvit{

    public Militaer(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    @Override
    public int prisAaBetale(){
        return 0;
    }

    @Override
    public String toString(){
        return super.toString()+", Farge: "+farge()+", Pris: "+prisAaBetale()+'\n';
    }
}
