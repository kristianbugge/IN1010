import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public class SamleReseptorer implements Runnable {

    private ImmunReseptor reseptorSamlet;
    private ImmunReseptor reseptorLeggeTil;

    public SamleReseptorer(ImmunReseptor reseptorSamlet, ImmunReseptor reseptorLeggetil) {
        this.reseptorSamlet = reseptorSamlet;
        this.reseptorLeggeTil = reseptorLeggetil;
    }

    @Override
    public void run() {
        HashMap<String, Integer> samletMap = reseptorSamlet.getReseptorMap();
        HashMap<String, Integer> leggeTilMap = reseptorLeggeTil.getReseptorMap();

        for (Map.Entry<String, Integer> reseptor : leggeTilMap.entrySet()) {
            reseptorSamlet.slaaSammenSubSekvenser(reseptor.getKey(), reseptor.getValue());
        }
    }
}
