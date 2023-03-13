import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Klikk extends MouseAdapter{

    Labyrint l;
    JLabyrint jLabyrint;
    ArrayList<Tuppel> kortesteSti = null;

    public Klikk(Labyrint l) {
        this.l = l;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JRute rute = (JRute) e.getSource();
        String ruteNavn = rute.toString();
        String[] ord = ruteNavn.split(" ");
        try {
            int startKol = Integer.parseInt(ord[0]);
            int startRad = Integer.parseInt(ord[1]);
            ArrayList<ArrayList<Tuppel>> utveier = l.finnUtveiFra(startKol, startRad);
            JOptionPane.showMessageDialog(null, "Fant " + utveier.size() + " utveier.\n\n" + "Viser korteste utvei...");
            skrivUtvei(utveier);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Ugyldig input!");
        }
        tegnKortesteSti(l);
    }


    public void tegnKortesteSti(Labyrint labyrint){
        int kortesteStiLen = Integer.MAX_VALUE;
        ArrayList<ArrayList<Tuppel>> sti = labyrint.sti;
        //fjerner forrige utvei fra labyrinten ved flere klikk
        if (kortesteSti != null) {
            for (Tuppel t : kortesteSti) {
                settFargeStiRute(labyrint, t, Color.WHITE);
            }
        }
        //finner korteste utvei
        for (int i = 0; i < sti.size(); i++) {
            ArrayList<Tuppel> enSti = sti.get(i);
            if (enSti.size() < kortesteStiLen) {
                kortesteStiLen = enSti.size();
                kortesteSti = enSti;
            }
        }
        //tegner korteste utvei vi fant
        for (Tuppel t : kortesteSti) {
            settFargeStiRute(labyrint, t, Color.GREEN);
        }

    }

    private void settFargeStiRute(Labyrint l, Tuppel t, Color farge){
        int indeks = t.y * l.antKolonner + t.x;
        System.out.println("endrer fage for "+indeks);
        jLabyrint.getComponents()[indeks].setBackground(farge);
    }

    private void skrivUtvei(ArrayList<ArrayList<Tuppel>> utveier) {
        System.out.println("Utveier:");
        for (ArrayList<Tuppel> lis: utveier) {
            for(Tuppel t: lis)
                System.out.println(t);
            System.out.println();
        }
        System.out.println();
    }
}
