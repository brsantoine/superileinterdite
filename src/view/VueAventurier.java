package view;

import java.util.*;
import javax.swing.*;
import model.*;
import util.*;
 
public class VueAventurier implements Observe {
    
    private Observateur observateur;
    private int idAventurier;
    private JFrame aventurierWindow;
    private JPanel mainPanel;
    
    public VueAventurier(int id) {
        this.setID(id);
        
        aventurierWindow = new JFrame(Integer.toString(idAventurier));
                
                
        aventurierWindow.setSize(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER);
        
        aventurierWindow.setLocation(1500, Parameters.TOP_VUE_AVENTURIER+((Parameters.HAUTEUR_VUE_AVENTURIER + Parameters.ECART_VUE_AVENTURIER )*id));
        aventurierWindow.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        aventurierWindow.setUndecorated(Parameters.UNDECORATED);
        aventurierWindow.setResizable(Parameters.RESIZABLE);
        
        mainPanel = new JPanel();
        aventurierWindow.add(mainPanel);
        
        mainPanel.add(new JLabel("queue"));
        
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
        this.aventurierWindow.setVisible(true);
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