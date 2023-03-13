import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vindu{

    public static void main(String[] args) {
        JFrame vindu = new JFrame("Labyrintløser");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Labyrint l = null;

        //JFileChooser
        File fil;
        String filnavn = "";
        Scanner inn;
        int svar;
        JFileChooser filVelger = new JFileChooser(".");

        filVelger.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        svar = filVelger.showOpenDialog(null);

        if(svar == JFileChooser.APPROVE_OPTION) {
            fil = filVelger.getSelectedFile();
            filnavn = fil.getName();
            try {
                l = new Labyrint(fil, true);
            } catch (FileNotFoundException e) {
                System.exit(1);
            }
        }
        JOptionPane.showMessageDialog(null, "Lastet inn labyrint: " + filnavn + ".\n\n" + "Trykk på ruta du vil løse fra.");

        Klikk klikkeLytter = new Klikk(l);

        JLabyrint labyrintPanel = new JLabyrint(l, klikkeLytter);

        vindu.add(labyrintPanel);
        vindu.pack();
        vindu.setResizable(false);
        vindu.setVisible(true);
    }
}

