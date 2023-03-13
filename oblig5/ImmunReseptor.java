import java.util.*;

public class ImmunReseptor {

    private HashMap<String, Integer> reseptorMap = new HashMap<>();
    private boolean erSmittet = false;

    public ImmunReseptor(boolean erSmittet) {
        this.erSmittet = erSmittet;
    }

    public HashMap<String, Integer> getReseptorMap() {
        return reseptorMap;
    }

    public void leggTilSubSekvens(String nokkel, Integer verdi){
        reseptorMap.put(nokkel, verdi);
    }
    public void leggTilSubSekvens(String nokkel){
        if (reseptorMap.containsKey(nokkel) == false){
            reseptorMap.put(nokkel, 1);
        } else {
            Integer antForekomster = reseptorMap.get(nokkel) + 1;
            reseptorMap.put(nokkel, antForekomster);
        }
    }

    public void slaaSammenSubSekvenser(String nokkel, Integer verdi){
        if (reseptorMap.containsKey(nokkel) == false){
            reseptorMap.put(nokkel, verdi);
        } else {
            Integer antForekomster = reseptorMap.get(nokkel) + verdi;
            reseptorMap.put(nokkel, antForekomster);
        }
    }







}
