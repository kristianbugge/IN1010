import java.util.ArrayList;
import java.util.List;

public class Rack {

    private List<Node> nodeList = new ArrayList<Node>();

    public List<Node> getNodeList(){
        return nodeList;
    }

    public void leggTilNodeTilNodeliste(Node node){
        getNodeList().add(node);
    }
}
