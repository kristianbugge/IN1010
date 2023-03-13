public class Node {

    private int minneStr;
    private int antProsessorer;

    public Node(int minneStr, int antProsessorer) {
        setMinneStr(minneStr);
        setAntProsessorer(antProsessorer);
    }

    public int getMinneStr() {
        return minneStr;
    }

    public void setMinneStr(int minneStr) {
        this.minneStr = minneStr;
    }

    public int getAntProsessorer() {
        return antProsessorer;
    }

    public void setAntProsessorer(int antProsessorer) {
        this.antProsessorer = antProsessorer;
    }
}
