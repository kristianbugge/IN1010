import javax.swing.*;
import java.awt.*;

public class JRute extends JLabel {
    public Labyrint labyrint;
    public Tuppel posisjon;

    public JRute (Labyrint labyrint, Tuppel posisjon, char type) {
        this.labyrint = labyrint;
        this.posisjon = posisjon;
        this.setName(posisjon.toString());
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setPreferredSize(new Dimension( 20, 20));
        if (type == '.') {
                this.setBackground(Color.WHITE);
        }

        else {
            this.setBackground(Color.BLACK);
        }
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    @Override
    public String toString() {
        return posisjon.y + " " + posisjon.x;
    }

}
