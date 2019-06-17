package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Carte;
import util.*;
 
public class VuePlateau implements Observe {
    
    private VueGrille grille;
    private Observateur observateur;
    private JButton seDeplacerButton, assecherButton, endTurnButton, actionsRemainingButton;
    private JFrame plateauWindow;
    
    public VuePlateau() { 
 
        
        plateauWindow = new JFrame();
        plateauWindow.setLayout(new BorderLayout());
        plateauWindow.add(grille); 
        
        
        // Nombre d'actions restantes et boutons assécher et se déplacer
        seDeplacerButton = new JButton("Se déplacer");
        assecherButton = new JButton("Assécher");  
        endTurnButton = new JButton("Fin tour");        
        actionsRemainingButton = new JButton("3 actions restantes");

        plateauWindow.add(actionsRemainingButton);
        plateauWindow.add(seDeplacerButton);
        plateauWindow.add(assecherButton);
        
    // LISTENER 
    // -------- modeDeplacement / Assechement (changer le mode d'actions) --------
        seDeplacerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seDeplacerButton.setEnabled(false);
            assecherButton.setEnabled(true);
            Message m = new Message(Utils.Commandes.SE_DEPLACER, 0, 0, null, 0);
            notifierObservateur(m);
        }
    });        

    assecherButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            assecherButton.setEnabled(false);
            seDeplacerButton.setEnabled(true);
            Message m = new Message(Utils.Commandes.ASSECHER, 0, 0, null, 0);
            notifierObservateur(m);
        }
    });
    // -------- bouton fin tour --------
    endTurnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message m = new Message(Utils.Commandes.FIN_TOUR, 0, 0, null, 0);
            notifierObservateur(m);
        }
    });
    
    }
    
    public void possibleFinTour() {
        endTurnButton.setEnabled(true);
    }
    
    // Empêche d'assécher et de finir le tour (pour le plongeur lorsqu'il est sur une case coulée)
    public void impossibleFinTour() {
        endTurnButton.setEnabled(false);
        assecherButton.setEnabled(false);
        seDeplacerButton.setEnabled(true);
    }
    
    // Change le label du nombre d'actions restantes
    public void updateActions(int actions) {
        actionsRemainingButton.setText(actions + " actions restantes");
    }
    
    public void afficherDefaite() {
        throw new UnsupportedOperationException();
    }
    
    public void afficher() {
        this.plateauWindow.setVisible(true);
    }

    public VueGrille getGrille() {
        return grille;
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