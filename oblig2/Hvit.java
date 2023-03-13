class Hvit extends Resept{

    public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){    //konstruktør for P-resept
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    @Override
    public String farge(){
        return "Hvit";
    }

    @Override
    public int prisAaBetale() {
        return hentLegemiddel().hentPris();
    }

}
