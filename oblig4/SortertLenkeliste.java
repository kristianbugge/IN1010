public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

    @Override
    public void leggTil(T innData) {

        if(hentHode() == null){
            settHode(new Node(innData));
            return;
        }

        Node forrigeNode = hentHode();

        int indeks = 0;
        Node node = hentHode();
        while (node != null){
            if (node.hentData().compareTo(innData) > 0){
                super.leggTil(indeks, innData);
                return;
            }
            indeks++;
            forrigeNode = node;
            node = node.hentNeste();
        }
        // Vi har nådd enden på lista og skal legge inn innData helt på enden.
        forrigeNode.settNeste(new Node(innData));
    }

    @Override
    public T fjern() {
        sjekkOmListaErTom();
        Node node = hentHode();
        if (stoerrelse() == 1){
            settHode(null);
            return node.hentData();
        }
        Node nestSisteNode = hentHode();
        while (node.hentNeste() != null){
            nestSisteNode = node;
            node = node.hentNeste();
        }
        nestSisteNode.settNeste(null);
        return node.hentData();
    }

    @Override
    public void sett(int pos, T x) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Metoden sett() er ikke implementert!");
    }

    @Override
    public void leggTil(int pos, T x) throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Metoden leggTil() er ikke implementert!");
    }
}
