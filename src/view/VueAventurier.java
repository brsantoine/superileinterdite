package view;

import java.util.*;
import javax.swing.*;
import model.*;
import util.*;
 
public class VueAventurier extends JPanel implements Observe {
    
    private Observateur observateur;
    private int idAventurier;
    private JPanel mainPanel;
    
    public VueAventurier(int id) {
        this.setID(id);                
        setSize(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER);
        this.add(new JLabel("test"));
        
    }
    
    public void afficherCartes(ArrayList<Carte> ca) {
        // TODO - implement VueAventurier.afficherCartes
        for (Carte carte : ca) {

        }
    }
    
    public void setID(int id) {
        this.idAventurier = id;
    }
    
    public int getID() {
        return this.idAventurier;
    }
    
    public void afficher() {
        this.setVisible(true);
    }
    @Override
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }
    
    @Override
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
    
   
}