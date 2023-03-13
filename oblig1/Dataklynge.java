import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dataklynge {

    private int antallNoderPerRack = 0;
    List<Rack> rackList = new ArrayList<Rack>();

    public Dataklynge(int antNodePerRack) {
        antallNoderPerRack = antNodePerRack;
    }

    public Dataklynge(String filnavn) {
        lesFraFil(filnavn);
    }

    public void leggTilRackOgNoder(int antNoder, int minneStr, int antProsessorer) {
        Rack sisteRack = null;
        int antallIRack = 0;

        if (!rackList.isEmpty()) {                          //Hvis vi har en rack-liste som ikke er tom, henter vi øverste rack og hvor mange noder den har
            sisteRack = rackList.get(rackList.size() - 1);
            antallIRack = sisteRack.getNodeList().size();
        }
        for (int i = 0; i < antNoder; i++) {
            if ((antallIRack % antallNoderPerRack) == 0) {  // Hver gang vi har fylt opp racket, dvs antall noder er 12
                sisteRack = leggTilNyttRackTilRackList();
            }
            sisteRack.leggTilNodeTilNodeliste(new Node(minneStr, antProsessorer));
            antallIRack = sisteRack.getNodeList().size();
        }
        System.out.println("generert initiell Dataklynge");
    }

    public int antProsessorer() {
        int antProsessorer = 0;
        for (Rack rack : rackList) {
            for (Node node : rack.getNodeList()) {
                antProsessorer += node.getAntProsessorer();
            }
        }
        return antProsessorer;
    }

    public int antRacks() {
        return rackList.size();
    }

    public int noderMedNokMinne(int paakrevdMinne) {
        List<Node> nodeMedNokMinne = finnNoderMedNokMinne(paakrevdMinne);
        return nodeMedNokMinne.size();
    }

    public void leggTilNodeIRack(Node node) {
        Boolean nodeLagtTill = false;
        Rack sisteRack = null;

        for (Rack rack : rackList) {
            if (rack.getNodeList().size() < antallNoderPerRack) {
                rack.getNodeList().add(node);
                nodeLagtTill = true;
            }
        }
        if (nodeLagtTill == false) {
            sisteRack = leggTilNyttRackTilRackList();
            sisteRack.leggTilNodeTilNodeliste(node);
        }
    }

    private List<Node> finnNoderMedNokMinne(int paakrevdMinne) {
        List<Node> nodeMedNokMinne = new ArrayList<Node>();
        for (Rack rack : rackList) {
            for (Node node : rack.getNodeList()) {
                if (node.getMinneStr() >= paakrevdMinne) {
                    nodeMedNokMinne.add(node);
                }
            }
        }
        return nodeMedNokMinne;
    }

    private Rack leggTilNyttRackTilRackList() {
        Rack rack = new Rack();
        rackList.add(rack);
        return rack;

    }

    public void lesFraFil(String filnavn) {
        File fil = new File(filnavn);
        Scanner in;
        try {
            in = new Scanner(fil);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            in = new Scanner("");
        }
        if (in.hasNextLine()){
            String linje = in.nextLine();                   //if-sjekk om fila har en ny linje, hvis den har det angir vi antallNoderPerRack som 12 i dette tilfellet
                                                            //fyller så inn mettoden leggTilRack og Noder ved å splitte de følgene linjene og så føre verdiene inn med liste med variabelen alleData
            antallNoderPerRack = Integer.parseInt(linje);
        }
        while (in.hasNextLine()) {

            String linje = in.nextLine();
            String[] alleData = linje.split(" ");
            leggTilRackOgNoder(Integer.parseInt(alleData[0]), Integer.parseInt(alleData[1]), Integer.parseInt(alleData[2]));
        }
    }
}
