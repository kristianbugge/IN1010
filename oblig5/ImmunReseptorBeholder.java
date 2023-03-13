import java.util.ArrayList;
import java.util.List;

public class ImmunReseptorBeholder {

    private List<ImmunReseptor> reseptorList = new ArrayList<>();

    public ImmunReseptorBeholder() {

    }

    public List<ImmunReseptor> getReseptorList() {
        return reseptorList;
    }

    public void leggTilReseptor(ImmunReseptor immunReseptor){
        reseptorList.add(immunReseptor);
    }
}
