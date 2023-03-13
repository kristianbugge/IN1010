public class TestLegemiddel {

    public static void main(String args[]){

        Narkotisk narko = new Narkotisk("Marijuana", 800, 200, 18);
        System.out.println("Id                : "+testLegemiddelId(narko, 1));
        System.out.println("Navn              : "+testLegemiddelNavn(narko, "Marijuana"));
        System.out.println("Pris              : "+testLegemiddelPris(narko, 800));
        System.out.println("Mengde virkestoff : "+testLegemiddelVirkestoff(narko, 200));
        System.out.println("Styrke            : "+testLegemiddelStyrke(narko, 18));
        System.out.println(narko);

        Vanedannende vanedannende = new Vanedannende("Valium", 120, 30, 2);
        System.out.println("Id                : "+testLegemiddelId(vanedannende, 2));
        System.out.println("Navn              : "+testLegemiddelNavn(vanedannende, "Valium"));
        System.out.println("Pris              : "+testLegemiddelPris(vanedannende, 120));
        System.out.println("Mengde virkestoff : "+testLegemiddelVirkestoff(vanedannende, 30));
        System.out.println("Styrke            : "+testLegemiddelStyrke(vanedannende, 2));
        System.out.println(vanedannende);

        Vanlig vanlig = new Vanlig("Paracet", 90, 6);
        System.out.println("Id                : "+testLegemiddelId(vanlig, 3));
        System.out.println("Navn              : "+testLegemiddelNavn(vanlig, "Paracet"));
        System.out.println("Pris              : "+testLegemiddelPris(vanlig, 90));
        System.out.println("Mengde virkestoff : "+testLegemiddelVirkestoff(vanlig, 6));
        System.out.println(vanlig);
    }

    public static boolean testLegemiddelId(Legemiddel legemiddel,
                                           int forventetLegemiddelId){
        return legemiddel.hentId() == forventetLegemiddelId;
    }
    public static boolean testLegemiddelNavn(Legemiddel legemiddel,
                                             String forventetLegemiddelNavn){
        return legemiddel.hentNavn().equals(forventetLegemiddelNavn);
    }
    public static boolean testLegemiddelPris(Legemiddel legemiddel,
                                             int forventetLegemiddelPris){
        return legemiddel.hentPris() == forventetLegemiddelPris;
    }
    public static boolean testLegemiddelVirkestoff(Legemiddel legemiddel,
                                                   int forventetLegemiddelVirkestoff){
        return legemiddel.hentVirkestoff() == forventetLegemiddelVirkestoff;
    }
    public static boolean testLegemiddelStyrke(Narkotisk legemiddel,
                                               int forventetLegemiddelStyrke){
        return legemiddel.hentNarkotiskStyrke() == forventetLegemiddelStyrke;
    }
    public static boolean testLegemiddelStyrke(Vanedannende legemiddel,
                                               int forventetLegemiddelStyrke){
        return legemiddel.hentVanedannendeStyrke() == forventetLegemiddelStyrke;
    }
}

