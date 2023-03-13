import java.util.HashMap;

public class FilnavnOgSmittestatus {

    private String filnavn;
    private boolean erSmittet;

    public FilnavnOgSmittestatus(String pasientFil, boolean erSmittet) {
        this.filnavn = pasientFil;
        this.erSmittet = erSmittet;
    }

    public String getFilnavn() {
        return filnavn;
    }

    public void setFilnavn(String filnavn) {
        this.filnavn = filnavn;
    }

    public boolean isErSmittet() {
        return erSmittet;
    }

}
