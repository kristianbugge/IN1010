public class Lenkeliste<T> implements Liste<T> {

    private Node hode = null;  // Hode er det foerste Noden i den linkede lista. Hode er indeks 0 i den lenkede lista.


    protected Node hentHode(){
        return hode;
    }

    protected void settHode(Node hode){
        this.hode = hode;
    }

    @Override
    public int stoerrelse() {
        int storrelse = 0;
        Node node = hode;
        while(node != null){
            storrelse++;
            node = node.neste;
        }
        return storrelse;
    }

    // Legger til en node x i lista på en gitt posisjon pos, listas første node har posisjon 0.
    @Override
    public void leggTil(int pos, T x) {
        sjekkAtIndeksEr0VedTomListe(pos);
        sjekkOmIndeksEksisterer(pos);

        int indeks = 0;
        Node node = hode;
        Node forrigeNode = null;

        // Spesialhandtering for hode (= node 0)
        if (pos == 0){
            Node forrigeHode = hode;
            hode = new Node(x);
            hode.neste = forrigeHode;
            return;
        }
        if (pos == stoerrelse()){
            leggTil(x);
            return;
        }


        // Dersom det eksisterer noder i lista
        while(node != null){
            if (indeks == pos){
                Node nyNode = new Node(x);
                forrigeNode.neste = nyNode;
                nyNode.neste = node;
                return;
            }
            forrigeNode = node;
            indeks++;
            node = node.neste;
        }
    }

    // Legger til ny Node på slutten av listen.
    @Override
    public void leggTil(T x) {

        if(hode == null){
            hode = new Node(x);
            return;
        }
        siste().neste = new Node(x);
    }

    @Override
    public void sett(int pos, T x) {
        sjekkOmIndeksErUgyldig(pos);
        Node funnetNode = finnNodePaaPos(pos);
        if (funnetNode != null)
            funnetNode.data = x;
    }

    @Override
    public T hent(int pos) {
        sjekkOmIndeksErUgyldig(pos);
        Node funnetNode = finnNodePaaPos(pos);
        if (funnetNode != null)
            return funnetNode.data;
        return null;
    }

    @Override
    public T fjern(int pos) {
        sjekkOmIndeksErUgyldig(pos);
        int indeks = 0;
        Node forrigeNode = null;
        Node node = hode;
        T nodeData = null;

        // Håndtere fjerning av node nr 0
        if (pos == 0){
            return fjern();
        }

        while(node != null){
            if (indeks == pos){
                forrigeNode.neste = node.neste;
                return node.data;// Obs returner data for noden som fjernes
            }
            indeks++;
            forrigeNode = node;
            node = node.neste;
        }
        return null;
    }

    // Returnerer node i starten av lista.
    @Override
    public T fjern() {
        sjekkOmListaErTom();
        Node forsteNode = hode;
        hode = hode.neste;
        return forsteNode.data; // Obs returner data for noden som fjernes (dette er ikke spesifisert i oppgaven)
    }

    protected Node siste(){
        Node siste = hode;

        while(siste.neste != null){
            siste = siste.neste;
        }
        return siste;
    }

    private Node finnNodePaaPos(int pos) {
        int indeks = 0;
        Node node = hode;
        while(node != null){
            if (indeks == pos){
                return node;
            }
            indeks++;
            node = node.neste;
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node node = hode;
        while(node != null){
            if (node.neste == null)
                sb.append(node.data);
            else
                sb.append(node.data).append("->");
            node = node.neste;
        }
        return sb.toString();
    }

    public class Node{
        private Node neste;
        private T data;

        public Node(T data){
            this.data = data;
            this.neste = null;
        }

        public Node hentNeste(){
            return this.neste;
        }
        public void settNeste(Node node){
            this.neste = node;
        }

        public T hentData(){
            return this.data;
        }

        @Override
        public String toString(){
            return data.toString();
        }
    }

    // Metoder for aa sjekke lovlige verdier for indeks
    public void sjekkOmListaErTom() throws UgyldigListeIndeks{
        if (hode == null && stoerrelse() == 0)
            throw new UgyldigListeIndeks(0);
    }

    public void sjekkAtIndeksEr0VedTomListe(int indeks){
        if(stoerrelse() == 0 && indeks != 0){
            throw new UgyldigListeIndeks(indeks);
        }
    }

    public void sjekkOmIndeksErUgyldig(int indeks) throws UgyldigListeIndeks{
        if( (indeks >= 0 && indeks < stoerrelse()) == false )
            throw new UgyldigListeIndeks(indeks);
        return;
    }

    public void sjekkOmIndeksEksisterer(int indeks) throws UgyldigListeIndeks{
        if (indeks < 0 || indeks > stoerrelse())
            throw new UgyldigListeIndeks(indeks);
    }
}
