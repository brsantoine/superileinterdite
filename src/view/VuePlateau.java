//package view;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.*;
//import javax.swing.*;
//import model.Carte;
//import util.*;
// 
//public class VuePlateau extends JPanel implements Observe {
//    
//    private VueGrille grille;
//    private Observateur observateur;
//    
//    public VuePlateau() { 
//        
//        grille = new VueGrille();
//        this.add(grille);
//        
//    }
//
//    public void afficher() {
//        this.setVisible(true);
//    }
//
//    public VueGrille getGrille() {
//        return grille;
//    }
//    
//    @Override
//    public void addObservateur(Observateur o) {
//        this.observateur = o;
//    }
//    
//    @Override
//    public void notifierObservateur(Message m) {
//        if (observateur != null) {
//            observateur.traiterMessage(m);
//        }
//    }
//    
//    
//}