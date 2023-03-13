class PResept extends Hvit{

    public PResept(Legemiddel legemiddel, Lege lege, int pasientId) {
        super(legemiddel, lege, pasientId, 3);
    }

    @Override
    public int prisAaBetale(){
        int pris =(hentLegemiddel().hentPris() - 108);
        if (pris<0){
            return 0;
        } else{
            return pris;
        }
    }

    @Override
    public String toString(){
        return super.toString()+", Farge: "+farge()+", Pris: "+prisAaBetale();
    }

}
