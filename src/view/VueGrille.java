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
        gc.insets = new Insets(0,0,0,0);
        gc.fill = GridBagConstraints.BOTH;           
        gc.gridy = 0;
        
        grid = new ArrayList<>();
        
        // Création des boutons composant la grille 
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
        
        for (int i = 0; i < grid.size(); i++) {
            grid.get(i).setID(i);
            grid.get(i).addObservateur(this.observateur);
        }
    }

    public ArrayList<VueTuile> getGrid() {
        return grid;
    }
    
    // Affiche en vert toutes les tuiles accessibles par l'aventurier courant
    public void afficherTuilesDeplacer(ArrayList<Tuile> tuiles) {

        for (Tuile tuile : tuiles) {
            for (VueTuile vTuile : grid) {
//                vTuile.setEnabled(false);
                if (vTuile.getID() == tuile.getID()) {
                    vTuile.setBorder(new LineBorder(Color.GREEN, 3));
                    vTuile.setEnabled(true);
                }
            }
        }
    }
    public void afficherTuilesDeplacer(ArrayList<Tuile> tuiles, int numAv) {

        for (Tuile tuile : tuiles) {
            for (VueTuile vTuile : grid) {
//                vTuile.setEnabled(false);
                if (vTuile.getID() == tuile.getID()) {
                    vTuile.setBorder(new LineBorder(Color.GREEN, 3));
                    vTuile.setEnabled(true);
                    vTuile.setNumAv(numAv);
                }
            }
        }
    }
    
    // Affiche en bleu toutes les tuiles asséchables par l'aventurier courant
    public void afficherTuilesAssecher(ArrayList<Tuile> tuiles) {
        
        for (Tuile tuile : tuiles) {
            for (VueTuile vTuile : grid) {
//                vTuile.setEnabled(false);
                if (vTuile.getID() == tuile.getID()) {
                    vTuile.setBorder(new LineBorder(Color.BLUE, 3));
                    vTuile.setEnabled(true);
                }
            }
        }
    }
    
    public void afficherPions(ArrayList<Aventurier> aventuriers) {        
        // Remet toutes les bordures à la couleur par défaut
//        for (int i = 0; i < grid.size() ; i++) {
//            grid.get(i).setBorder(new LineBorder(new JButton().getBackground()));
//            grid.get(i).setBackground(new JButton().getBackground());
//        }
        
        // Met les labels sur toutes les cases de la grille
        for (int i = 0; i < grid.size() ; i++) {
//            grid.get(i).setBorder(new LineBorder(new JButton().getBackground()));
            // Met à jour les couleurs des bordures en fonction de la position des aventuriers
            
            int j = 0;
            for (Aventurier aventurier : aventuriers) {
                if (aventurier.getTuile().getID() == grid.get(i).getID()) {
//                    grid.get(i).setBorderPainted(true);
                        grid.get(i).afficherPion(j);
                        
                    /*if (aventurier.getCouleur().equals("Vert")) {
                        grid.get(i).setBorder(new LineBorder(Color.GREEN, 5));
                    } else if (aventurier.getCouleur().equals("Violet")) {
                        grid.get(i).setBorder(new LineBorder(new Color(255, 0,255), 5));
                    } else if (aventurier.getCouleur().equals("Jaune")) {
                        grid.get(i).setBorder(new LineBorder(Color.YELLOW, 5));
                    } else if (aventurier.getCouleur().equals("Noir")) {
                        grid.get(i).setBorder(new LineBorder(Color.BLACK, 5));
                    } else if (aventurier.getCouleur().equals("Gris")) {
                        grid.get(i).setBorder(new LineBorder(Color.GRAY, 5));
                    } else if (aventurier.getCouleur().equals("Bronze")) {
                        grid.get(i).setBorder(new LineBorder(new Color(176, 141, 87), 5));
                    } else if (aventurier.getCouleur().equals("Bleu")) {
                        grid.get(i).setBorder(new LineBorder(Color.BLUE, 5));
                    } else if (aventurier.getCouleur().equals("Rouge")) {
                        grid.get(i).setBorder(new LineBorder(Color.RED, 5));
                    }   */                 
                }
                j++;
            }    
        }   
    }
    
    public void initialiserGrille(ArrayList<Tuile> tuiles, ArrayList<Aventurier> aventuriers) {
        resetGrille(tuiles);   
        // Met les images sur toutes les tuiles de la grille
        for (int i = 0; i < grid.size() ; i++) {
            String nom = new String(tuiles.get(i).getNom());
            nom = nom.replaceAll("\\s", "");
            grid.get(i).setSechePanel(nom);  
            grid.get(i).setInondePanel(nom + "_Inonde");
            grid.get(i).setPionPanel(aventuriers);
        }
    }
    
    // Désactive tous les boutons de la grille, et redonne la possibilité de cliquer sur asécher et se déplacer
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