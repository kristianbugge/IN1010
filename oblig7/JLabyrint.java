import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JLabyrint extends JPanel {
    Labyrint l;
    Klikk klikkeLytter;

    public JLabyrint(Labyrint l, Klikk klikkeLytter) {
        this.klikkeLytter = klikkeLytter;
        this.klikkeLytter.jLabyrint = this;
        this.l = l;
        this.setLayout(new GridLayout(l.antRader, l.antKolonner));
        lagJRuter(l);
    }

    //Opprette JLabelsene i GridLayoutet og implementere klikking av dem
    public void lagJRuter(Labyrint l){
        for (int i = 0; i < l.ruter.length; i++) {
            for (int j = 0; (l.ruter[i] != null && j < l.ruter[i].length); j++) {
                JRute rute = new JRute(l, new Tuppel(i, j), l.ruter[i][j].tilTegn());
                this.add(rute);
                rute.addMouseListener(klikkeLytter);

            }
        }
    }
}
