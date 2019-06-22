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
    private JPanel southPanel, westPanel, eastPanel, centerPanel;  
    private JButton seDeplacerButton, assecherButton, endTurnButton, actionsRemainingButton, helicoButton;

    
    public VueJeu(ArrayList<Aventurier> aventuriers, ArrayList<JTextField> noms, ArrayList<JComboBox> couleurs) {
        
        // -------- Setup window --------

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setResizable(Parameters.RESIZABLE);
        this.setTitle("Ile Interdite");
        this.setSize((int) screenSize.getWidth() -100, (int) screenSize.getHeight() - 100);  

        // ---------------------------------------------------------------------
        // ---------------------------  GAME WINDOW  ---------------------------
        // ---------------------------------------------------------------------
        
        this.setLayout(new BorderLayout(10,10));
        vueNiveau = new VueNiveau(2);
        
        vueGrille = new VueGrille();
        centerPanel = new JPanel();
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
//        westPanel.setPreferredSize(new Dimension(300,300));
        

        // ------------ EASTPANEL ------------

//        eastPanel.setPreferredSize(new Dimension(300, 300));

        eastPanel.setLayout(new GridLayout(4,1,0,30));
//        eastPanel.setSize(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER*4);
        vuesAventuriers = new ArrayList<>();
        int x = 0;
        for(Aventurier av : aventuriers){
            VueAventurier va = new VueAventurier(x, av.getRole() + " (" + noms.get(x).getText() + ")");
            this.vuesAventuriers.add(va);
            x++;
        }
        
        for (VueAventurier aventurier : vuesAventuriers) {
            eastPanel.add(aventurier);
        }
        
        // ------------ SOUTHPANEL ------------
//        southPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 150));

        
        // Nombre d'actions restantes et boutons assécher et se déplacer
        seDeplacerButton = new JButton("Se déplacer");
        helicoButton = new JButton("Hélicoptère");
        assecherButton = new JButton("Assécher");  
        endTurnButton = new JButton("Fin tour");        
        actionsRemainingButton = new JButton("3 actions restantes");

        southPanel.add(actionsRemainingButton);
        southPanel.add(seDeplacerButton);
        southPanel.add(assecherButton);
        southPanel.add(endTurnButton);
        southPanel.add(helicoButton);
        
        // LISTENER 
        // -------- seDeplacerButton et assecherButton (changer le mode d'actions) --------
        seDeplacerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seDeplacerButton.setEnabled(false);
                assecherButton.setEnabled(true);
                Message m = new Message(Utils.Commandes.SE_DEPLACER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });        
        
        helicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helicoButton.setEnabled(false);
                seDeplacerButton.setEnabled(true);
                assecherButton.setEnabled(true);
                Message m = new Message(Utils.Commandes.SE_DEPLACER, 0, 0, null, 0);
                m.setHelico(true);
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
    }
    
    public void possibleAssecher() {
        assecherButton.setEnabled(true);
    }
    public void impossibleAssecher() {
        assecherButton.setEnabled(false);
    }
    
    // Change le label du nombre d'actions restantes
    public void updateActions(int actions) {
        actionsRemainingButton.setText(actions + " actions restantes");
    }

    public ArrayList<VueAventurier> getVuesAventuriers() {
        return vuesAventuriers;
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
    
    public void activerHelico() {
        helicoButton.setEnabled(true);
    }
    
    public void desactiverHelico() {
        helicoButton.setEnabled(false);
    }

    public void finTour() {
//        this.getGrille().resetGrille();
        seDeplacerButton.setEnabled(true);
        assecherButton.setEnabled(true);
    }
    
    public VueGrille getGrille() {
        return vueGrille;
    }

    public VueNiveau getVueNiveau() {
        return vueNiveau;
    }
    
    @Override
    public void addObservateur(Observateur o) {
        this.observateur = o;
        vueGrille.addObservateur(o);
        for (VueAventurier vA : vuesAventuriers) {
            vA.addObservateur(o);
        }
    }
    
    @Override
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
    
}
    

    

