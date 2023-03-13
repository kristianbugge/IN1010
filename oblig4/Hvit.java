class Hvit extends Resept{

    public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){    //konstruktør for P-resept
        super(legemiddel, utskrivendeLege, pasient, 3);
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
