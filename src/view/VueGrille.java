package view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import util.*;
import model.*;
 
public class VueGrille extends JPanel implements Observe{
    
    private ArrayList<VueTuile> grid;
    private Observateur observateur;
    public VueGrille() {
        
        this.setLayout(new GridBagLayout());       
        GridBagConstraints gc = new GridBagConstraints();
     
        gc.weightx = 0.15;
        gc.weighty = 0.15;        
        gc.fill = GridBagConstraints.BOTH;           
        gc.gridy = 0;
        
        grid = new ArrayList<>();
        
        // Création des vueTuiles composant la grille 
        
        for (int i = 2; i < 4; i++) {
            gc.gridx = i;
            VueTuile tuile = new VueTuile();  
            this.add(tuile, gc);
            grid.add(tuile);
        }
        
        gc.gridy = 1;
        for (int i = 1; i < 5; i++) {
            gc.gridx = i;
            VueTuile tuile = new VueTuile();  
            this.add(tuile, gc);
            grid.add(tuile);
        }
        
        gc.gridy = 2;
        for (int i = 0; i < 6; i++) {
            gc.gridx = i;
            VueTuile tuile = new VueTuile();  
            this.add(tuile, gc);
            grid.add(tuile);
        }
        
        gc.gridy = 3;
        for (int i = 0; i < 6; i++) {
            gc.gridx = i;
            VueTuile tuile = new VueTuile();  
            this.add(tuile, gc);
            grid.add(tuile);
        }
        
        gc.gridy = 4;
        for (int i = 1; i < 5; i++) {
            gc.gridx = i;
            VueTuile tuile = new VueTuile();  
            this.add(tuile, gc);
            grid.add(tuile);
        }
  
        gc.gridy = 5;
        for (int i = 2; i < 4; i++) {
            gc.gridx = i;
            VueTuile tuile = new VueTuile();  
            this.add(tuile, gc);
            grid.add(tuile);
        }
        
        // Ajoute les id des tuiles, de 0 a 23 (identique aux tuiles)
        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).setID(i);
            grid.get(i).addObservateur(this.observateur);
        }
    }

    public ArrayList<VueTuile> getGrid() {
        return grid;
    }
    
    // Affiche en vert toutes les tuiles accessibles par l'aventurier courant (ou avec la carte Heli)
    public void afficherTuilesDeplacer(ArrayList<Tuile> tuilesAccessibles) {
        for (Tuile tuile : tuilesAccessibles) {
            for (VueTuile vTuile : grid) {
                if (vTuile.getID() == tuile.getID()) {
                    vTuile.setBorder(new LineBorder(Color.GREEN, 3));
                    vTuile.setEnabled(true);
                }
            }
        }
    }
    
    // Affiche en vert toutes les tuiles accessibles par l'aventurier d'id numAv (utilisé par Navigateur pour déplacer un autre aventurier)
    public void afficherTuilesDeplacer(ArrayList<Tuile> tuiles, int numAv) {
        for (Tuile tuile : tuiles) {
            for (VueTuile vTuile : grid) {
                if (vTuile.getID() == tuile.getID()) {
                    vTuile.setBorder(new LineBorder(Color.GREEN, 3));
                    vTuile.setEnabled(true);
                    vTuile.setNumAv(numAv);
                }
            }
        }
    }
    
    // Affiche en bleu toutes les tuiles asséchables par l'aventurier courant (ou avec la carte Sac)
    public void afficherTuilesAssecher(ArrayList<Tuile> tuiles) {
        for (Tuile tuile : tuiles) {
            for (VueTuile vTuile : grid) {
                if (vTuile.getID() == tuile.getID()) {
                    vTuile.setBorder(new LineBorder(Color.BLUE, 3));
                    vTuile.setEnabled(true);
                }
            }
        }
    }
    
    // Affiche les pions sur la grille
    public void afficherPions(ArrayList<Aventurier> aventuriers) {        
        for (int i = 0; i < grid.size() ; i++) {
            int j = 0;
            for (Aventurier aventurier : aventuriers) {
                if (aventurier.getTuile().getID() == grid.get(i).getID()) {
                    grid.get(i).afficherPion(j);
                }
                j++;
            }    
        }   
    }
    
    // Met les images sur toutes les tuiles de la grille
    public void initialiserGrille(ArrayList<Tuile> tuiles, ArrayList<Aventurier> aventuriers) {
        resetGrille(tuiles);   
        for (int i = 0; i < grid.size() ; i++) {
            String nom = new String(tuiles.get(i).getNom());
            nom = nom.replaceAll("\\s", "");
            grid.get(i).setSechePanel(nom);  
            grid.get(i).setInondePanel(nom + "_Inonde");
            grid.get(i).setPionPanel(aventuriers);
        }
    }
    
    // Cache les pions, retire les bordures et affiche uniquement les tuiles en fonction de leur état
    public void resetGrille(ArrayList<Tuile> tuiles) {
        int i = 0;
        for (Tuile tuile : tuiles) {
            grid.get(i).cacherPion();
            grid.get(i).setBorder(new LineBorder(new JButton().getBackground()));
            if (tuile.getEtat().equals("seche")) {
                grid.get(i).afficherSeche();
            } else if (tuile.getEtat().equals("inondé")) {
                grid.get(i).afficherInonde();
            } else {
                grid.get(i).afficherCoule();
            }
            i++;
        }
    }
    
    @Override
    public void addObservateur(Observateur o) {
        this.observateur = o;
        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).addObservateur(o);
        }
    }
    
    @Override
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
}