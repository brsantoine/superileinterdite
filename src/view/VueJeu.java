/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import model.*;
import util.*;

/**
 *
 * @author aragnoua
 */
public class VueJeu extends JFrame implements Observe{

    /**
     * @param args the command line arguments
     */
    
    private Observateur observateur;
    private VueNiveau vueNiveau;
    private VueGrille vueGrille;
    private ArrayList<VueAventurier> vuesAventuriers;
    private JPanel southPanel, westPanel, eastPanel;  
    private JButton seDeplacerButton, assecherButton, endTurnButton, actionsRemainingButton;

    
    public VueJeu(ArrayList<Aventurier> aventuriers) {
        
        // -------- Setup window --------

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setTitle("Ile Interdite");
        this.setSize(1920,1080);   
        
        // ---------------------------------------------------------------------
        // ---------------------------  GAME WINDOW  ---------------------------
        // ---------------------------------------------------------------------
        
        this.setLayout(new BorderLayout());
        vueNiveau = new VueNiveau(2);
        vueGrille = new VueGrille();
        eastPanel = new JPanel();
        westPanel = new JPanel();
        southPanel = new JPanel();
        
        this.add(vueGrille, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(westPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.SOUTH);
         
        
        // ------------ WESTPANEL ------------
        
        westPanel.setLayout(new BorderLayout());
//        westPanel.add(new JPanel(), BorderLayout.NORTH);
        westPanel.add(vueNiveau, BorderLayout.CENTER);
        

        // ------------ EASTPANEL ------------        
        vuesAventuriers = new ArrayList<>();
        int x = 0;
        for(Aventurier av : aventuriers){
            VueAventurier va = new VueAventurier(x);
            this.vuesAventuriers.add(va);
            x++;
        }
        
        eastPanel.setLayout(new GridLayout(4,1));
        
        for (VueAventurier aventurier : vuesAventuriers) {
            eastPanel.add(aventurier);
        }
        
        // ------------ SOUTHPANEL ------------
        
        // Nombre d'actions restantes et boutons assécher et se déplacer
        seDeplacerButton = new JButton("Se déplacer");
        assecherButton = new JButton("Assécher");  
        endTurnButton = new JButton("Fin tour");        
        actionsRemainingButton = new JButton("3 actions restantes");

        southPanel.add(actionsRemainingButton);
        southPanel.add(seDeplacerButton);
        southPanel.add(assecherButton);
        
        
        
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

    public void afficher() {
        this.setVisible(true);
        for (VueAventurier va : vuesAventuriers) {
            va.afficher();
        }
        vueGrille.setVisible(true);
        vueNiveau.afficher();
        
    }
    
    public void afficherDefaite() {
        throw new UnsupportedOperationException();
    }

    
    public VueGrille getGrille() {
        return vueGrille;
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
    

    

