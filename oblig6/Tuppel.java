public class Tuppel {
    public int x;
    public int y;

    public Tuppel(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y +")";
    }
}
