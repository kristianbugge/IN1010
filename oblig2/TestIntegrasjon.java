public class TestIntegrasjon {    //bygde videre på testresept for å skrive denne, ettersom det bare var utvidelse av Lege-objekter som var forskjellen

    public static void main(String args[]){

        // Opprette noen Legemidler
        Narkotisk narko = new Narkotisk("Marijuana", 800, 200, 18);
        System.out.println("Id                : "+testLegemiddelId(narko, 0));
        System.out.println("Navn              : "+testLegemiddelNavn(narko, "Marijuana"));
        System.out.println("Pris              : "+testLegemiddelPris(narko, 800));
        System.out.println("Mengde virkestoff : "+testLegemiddelVirkestoff(narko, 200));
        System.out.println("Styrke            : "+testLegemiddelStyrke(narko, 18));
        System.out.println(narko);

        Vanedannende vanedannende = new Vanedannende("Valium", 120, 30, 2);
        System.out.println("Id                : "+testLegemiddelId(vanedannende, 1));
        System.out.println("Navn              : "+testLegemiddelNavn(vanedannende, "Valium"));
        System.out.println("Pris              : "+testLegemiddelPris(vanedannende, 120));
        System.out.println("Mengde virkestoff : "+testLegemiddelVirkestoff(vanedannende, 30));
        System.out.println("Styrke            : "+testLegemiddelStyrke(vanedannende, 2));
        System.out.println(vanedannende);

        Vanlig vanlig = new Vanlig("Paracet", 100, 6);
        System.out.println("Id                : "+testLegemiddelId(vanlig, 2));
        System.out.println("Navn              : "+testLegemiddelNavn(vanlig, "Paracet"));
        System.out.println("Pris              : "+testLegemiddelPris(vanlig, 100));
        System.out.println("Mengde virkestoff : "+testLegemiddelVirkestoff(vanlig, 6));
        System.out.println(vanlig);

        // Opprette noen leger og spesialister
        Lege lege1 = new Lege("Aksel");
        Spesialist spesialist1 = new Spesialist("Jonas", "A_314");

        // Opprette noen resepter av forskjellige typer
        Militaer militaerResept = new Militaer(vanlig, lege1, 12345, 3);
        System.out.println("PasientId          : "+testReseptPasientId(militaerResept, 12345));
        System.out.println("PrisAaBetale       : "+testReseptPrisAaBetale(militaerResept, 0));
        System.out.println("Reit, før bruk     : "+testReseptReit(militaerResept, 3));
        militaerResept.bruk();
        System.out.println("Reit, etter bruk   : "+testReseptReit(militaerResept, 2));
        System.out.println(militaerResept);

        PResept pResept = new PResept(vanedannende, lege1, 11111);
        System.out.println("PasientId          : "+testReseptPasientId(pResept, 11111));
        System.out.println("PrisAaBetale       : "+testReseptPrisAaBetale(pResept, 12));
        System.out.println("Reit, før bruk     : "+testReseptReit(pResept, 3));
        militaerResept.bruk();
        System.out.println("Reit, etter bruk   : "+testReseptReit(pResept, 3));
        System.out.println(pResept);

        Blaa blaaResept = new Blaa(narko, spesialist1, 54321, 3);
        System.out.println("PasientId          : "+testReseptPasientId(blaaResept, 54321));
        System.out.println("PrisAaBetale       : "+testReseptPrisAaBetale(blaaResept, 200));
        System.out.println("Reit, før bruk     : "+testReseptReit(blaaResept, 3));
        blaaResept.bruk();
        System.out.println("Reit, etter bruk   : "+testReseptReit(blaaResept, 2));

        System.out.println(blaaResept);


    }

    // Test: Legemiddel
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
    // Test  Resept-klasser
    public static boolean testReseptPasientId(Resept mResept,
                                              int forventetPasientId){
        return mResept.hentPasientId() == forventetPasientId;
    }
    public static boolean testReseptPrisAaBetale(Resept resept,
                                                 int forventetPrisAaBetale){
        return resept.prisAaBetale() == forventetPrisAaBetale;
    }
    public static boolean testReseptReit(Resept resept,
                                         int forventetReit){
        return resept.hentReit() == forventetReit;
    }
    // Test Lege / Spesialist
    public static boolean testLegeNavn(Lege lege,
                                       String forventetLegeNavn){
        return lege.hentNavn() == forventetLegeNavn;
    }
    public static boolean testLegeKontrollID(Spesialist spesialist,
                                             String forventetSpesialistKontrollID){
        return spesialist.hentKontrollID() == forventetSpesialistKontrollID;
    }


}
