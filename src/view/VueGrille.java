package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import model.Aventurier;
import model.Tuile;
 
public class VueGrille extends JPanel {
    
    private ArrayList<VueTuile> grid;

    public VueGrille() {

        this.setLayout(new GridBagLayout());       
        GridBagConstraints gc = new GridBagConstraints();
     
        gc.weightx = 0.15;
        gc.weighty = 0.15;        
        gc.insets = new Insets(0,1,1,0);
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
        }
    }

    public ArrayList<VueTuile> getGrid() {
        return grid;
    }
    
    // Affiche en vert toutes les tuiles accessibles par l'aventurier courant
    public void afficherTuilesDeplacer(ArrayList<Tuile> tuiles) {
        for (VueTuile tuile : grid) {
            tuile.setBackground(new JButton().getBackground());
        }    
            
        for (Tuile tuile : tuiles) {
            for (VueTuile vTuile : grid) {
                vTuile.setEnabled(false);
                if (vTuile.getID() == tuile.getId()) {
                    vTuile.setBackground(Color.GREEN);
                }
            }
        }
        
        for (VueTuile vTuile : grid) {
            if (vTuile.getBackground() == Color.GREEN) {
                vTuile.setEnabled(true);
            }
        }
    }
    
    // Affiche en bleu toutes les tuiles asséchables par l'aventurier courant
    public void afficherTuilesAssecher(ArrayList<Tuile> tuiles) {
            // TODO - implement VueAventurier.afficherTuilesAssecher
        for (VueTuile tuile : grid) {
            tuile.setBackground(new JButton().getBackground());
        }    
            
        for (Tuile tuile : tuiles) {
            for (VueTuile vTuile : grid) {
                vTuile.setEnabled(false);
                if (vTuile.getID() == tuile.getId()){
                    if (tuile.getEtat().equals("inondé")) {
                        vTuile.setBackground(Color.BLUE); 
                   } else {
                        vTuile.setBackground(new JButton().getBackground());
                    }
                }
            }
        }
        
        for (VueTuile vTuile : grid) {
            if (vTuile.getBackground() == Color.BLUE) {
                vTuile.setEnabled(true);
            }
        }
    }
    
    public void updateDeplacement() {        
        // Remet toutes les bordures à la couleur par défaut
  /*      for (int i = 0; i < grid.size() ; i++) {
            grid.get(i).setBorder(new LineBorder(new JButton().getBackground()));
        }
//        
//        // Met les labels sur toutes les cases de la grille
        for (int i = 0; i < grid.size() ; i++) {
            grid.get(i).setText(observateur.getGrille().getTuiles().get(i).getNom());*/
//            
//            // Met à jour les couleurs des bordures en fonction de la position des aventuriers
//            for (Aventurier aventurier : observateur.getJoueurs()) {
//                if (aventurier.getTuile() == observateur.getGrille().getTuiles().get(i)) {
//                    
//                    grid.get(i).setBorderPainted(true);  
//                    
//                    if (aventurier.getRole().equals("plongeur")) {
//                        grid.get(i).setBorder(new LineBorder(Color.BLACK, 5));
//                    } else if (aventurier.getRole().equals("pilote")) {
//                        grid.get(i).setBorder(new LineBorder(Color.BLUE, 5));
//                    } else if (aventurier.getRole().equals("navigateur")) {
//                        grid.get(i).setBorder(new LineBorder(Color.YELLOW, 5));
//                    } else if (aventurier.getRole().equals("messager")) {
//                        grid.get(i).setBorder(new LineBorder(Color.GRAY, 5));
//                    } else if (aventurier.getRole().equals("ingenieur")) {
//                        grid.get(i).setBorder(new LineBorder(Color.RED, 5));
//                    } else if (aventurier.getRole().equals("explorateur")) {
//                        grid.get(i).setBorder(new LineBorder(Color.GREEN, 5));
//                    }                    
//                }
//            }    
        }   
    //}
    
    public void intitialiserGrille(ArrayList<Tuile> tuiles){
        
        for (int i = 0; i < grid.size() ; i++) {
            grid.get(i).setBorder(new LineBorder(new JButton().getBackground()));
        }
        
        // Met les labels sur toutes les cases de la grille
        for (int i = 0; i < grid.size() ; i++) {
            grid.get(i).setText(tuiles.get(i).getNom());
        }
        
    }
    
    // Désactive tous les boutons de la grille, et redonne la possibilité de cliquer sur asécher et se déplacer
    public void resetGrille() {
        for (int i = 0; i < grid.size() ; i++) {
            grid.get(i).setBackground(new JButton().getBackground());
            grid.get(i).setEnabled(false);
        }
    }
    

    
    
}