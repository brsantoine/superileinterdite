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
import javax.swing.border.LineBorder;
import model.*;
import superileinterdite.*;
import util.*;

/**
 *
 * @author aragnoua
 */
public class VueJeu implements Observe{

    /**
     * @param args the command line arguments
     */
    
    private JFrame leftFrame, gameFrame;
    private Observateur observateur;
    private VueNiveau vueNiveau;
    private VueGrille vueGrille;
    private MessageBox messageBox;
    private ArrayList<VueAventurier> vuesAventuriers;
    private JPanel westPanel, eastPanel, gridButtonsPanel, aventurierButtonsPanel, aventuriersPanel;  
    private JLabel actionsRemainingLabel;
    private JButton seDeplacerButton, assecherButton, endTurnButton, helicoButton, giveButton, defausserButton;

    
    public VueJeu(ArrayList<Aventurier> aventuriers, ArrayList<JTextField> noms){

        // ---------------------------------------------------------------------
        // ---------------------------  LEFT WINDOW  ---------------------------
        // ---------------------------------------------------------------------

        leftFrame = new JFrame() ;
        leftFrame.setLayout(new GridLayout(2,1));
        leftFrame.setLocation(Parameters.HORIZONTAL_BUFFER, Parameters.VERTICAL_BUFFER - Parameters.TASKBAR_BUFFER);
        leftFrame.setSize(Parameters.LEFT_FRAME_WIDTH, Parameters.LEFT_FRAME_HEIGHT);
        leftFrame.setUndecorated(Parameters.UNDECORATED);
        leftFrame.setResizable(Parameters.RESIZABLE);      

        messageBox = new MessageBox();
        vueNiveau = new VueNiveau(2);
        
        leftFrame.add(vueNiveau);
        leftFrame.add(messageBox);
        
        leftFrame.setVisible(true);
        
        // ---------------------------------------------------------------------
        // ---------------------------  GAME WINDOW  ---------------------------
        // ---------------------------------------------------------------------      
        
        // -------- Setup window --------
        
        gameFrame = new JFrame();
        gameFrame.setLayout(new BorderLayout(Parameters.HORIZONTAL_SPACE,0));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        gameFrame.setResizable(Parameters.RESIZABLE);
        gameFrame.setTitle("Ile Interdite");
        gameFrame.setLocation(Parameters.LEFT_FRAME_WIDTH + Parameters.HORIZONTAL_BUFFER, Parameters.VERTICAL_BUFFER - Parameters.TASKBAR_BUFFER);
        gameFrame.setSize(Parameters.GAME_FRAME_WIDTH, Parameters.GAME_FRAME_HEIGHT);  
        
        eastPanel = new JPanel();
        westPanel = new JPanel();
        
        gameFrame.add(new JPanel(), BorderLayout.WEST);
        gameFrame.add(westPanel, BorderLayout.CENTER);
        gameFrame.add(eastPanel, BorderLayout.EAST);
        
        // ============= WESTPANEL =============
        westPanel.setLayout(new BorderLayout(0,Parameters.VERTICAL_SPACE));
        
        gridButtonsPanel = new JPanel();
        vueGrille = new VueGrille();
        
        westPanel.add(new JPanel(), BorderLayout.NORTH);
        westPanel.add(vueGrille, BorderLayout.CENTER);
        westPanel.add(gridButtonsPanel, BorderLayout.SOUTH);   
        
            // ------------- gridButtonsPanel -------------
            
            // Met la bonne taille au panel des boutons
            gridButtonsPanel.setPreferredSize(new Dimension(Parameters.GRID_WIDTH, Parameters.BUTTONS_PANEL_HEIGHT));

            // Nombre d'actions restantes et boutons assécher et se déplacer
            seDeplacerButton = new JButton("Se déplacer");
            helicoButton = new JButton("Hélicoptère");
            assecherButton = new JButton("Assécher");  
            endTurnButton = new JButton("Fin tour");      
            
            actionsRemainingLabel = new JLabel("3 actions restantes");

            gridButtonsPanel.add(actionsRemainingLabel);
            gridButtonsPanel.add(seDeplacerButton);
            gridButtonsPanel.add(assecherButton);
            gridButtonsPanel.add(endTurnButton);
            gridButtonsPanel.add(helicoButton);
        
        // ============= EASTPANEL =============
        
        eastPanel.setPreferredSize(new Dimension(Parameters.VUE_AVENTURIER_WIDTH, Parameters.GAME_FRAME_HEIGHT));
        
        eastPanel.setLayout(new BorderLayout(0,Parameters.VERTICAL_SPACE));
        aventuriersPanel = new JPanel();
        aventurierButtonsPanel = new JPanel();
        
        eastPanel.add(new JPanel(), BorderLayout.NORTH);        
        eastPanel.add(aventuriersPanel, BorderLayout.CENTER);        
        eastPanel.add(aventurierButtonsPanel, BorderLayout.SOUTH);      

            // ------------- aventuriersPanel -------------
            
            aventuriersPanel.setLayout(new GridLayout(4,1,0,Parameters.CARD_VERTICAL_SPACE));            
            // Ligne qui sépare la grille des vuesAventuriers
            aventuriersPanel.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));
            
            vuesAventuriers = new ArrayList<>();
            int x = 0;
            for(Aventurier av : aventuriers){
                VueAventurier va = new VueAventurier(av.getId(), av.getRole() + " (" + noms.get(x).getText() + ")");
                this.vuesAventuriers.add(va);
                x++;
            }

            int i = 0;
            for (VueAventurier aventurier : vuesAventuriers) {                
                aventuriersPanel.add(aventurier);
                aventurier.setNameBorder(aventuriers.get(i).getCouleur()); 
                i++;
            }
            
            // ------------- aventurierButtonsPanel -------------
            
            // Met la bonne taille au panel des boutons
            aventurierButtonsPanel.setPreferredSize(new Dimension(Parameters.VUE_AVENTURIER_WIDTH,Parameters.BUTTONS_PANEL_HEIGHT));
                    
            giveButton = new JButton("Donner carte");
            defausserButton = new JButton("Défausser une carte");
            
            aventurierButtonsPanel.add(giveButton);
            aventurierButtonsPanel.add(defausserButton);
            

        // ============= LISTENERS =============
        
        // -------- seDeplacerButton et assecherButton (changer le mode d'actions) --------
        seDeplacerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seDeplacerButton.setEnabled(false);
                assecherButton.setEnabled(true);
                cacherCardsBorder();
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
                cacherCardsBorder();
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
                cacherCardsBorder();
                Message m = new Message(Utils.Commandes.ASSECHER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        // -------- giveButton --------
        
        giveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assecherButton.setEnabled(true);
                seDeplacerButton.setEnabled(true);
                giveButton.setEnabled(false);
                
                defausserButton.setEnabled(true);
                Message m = new Message(Utils.Commandes.DONNER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        // -------- defausserButton --------
        defausserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defausserButton.setEnabled(false);
                assecherButton.setEnabled(true);
                seDeplacerButton.setEnabled(true);
                defausserButton.setEnabled(false);
                giveButton.setEnabled(true);
                Message m = new Message(Utils.Commandes.DEFAUSSER_CARTE, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        
        
        // -------- endTurnButton --------
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cacherCardsBorder();
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
        actionsRemainingLabel.setText(actions + " actions restantes");
    }
    
    public void updateCards(int id, ArrayList<CarteMain> cartes) {
        for (VueAventurier vA : vuesAventuriers) {
            if (vA.getID() == id) {
                vA.afficherCartes(cartes);
            }
        }
    }
    
    // Enleve l'image de la derniere carte après une défausse
    public void defausseLastCard(int id) {
        for (VueAventurier vA : vuesAventuriers) {
            if (vA.getID() == id) {
                vA.defausseLastCard();
            }
        }
    }
    
    public void updateCardsBorder() {
        for (VueAventurier vA : vuesAventuriers) {
            vA.afficherCardsBorder();
        }   
    }
    
    public void updateCardsBorder(int id) {
        for (VueAventurier vA : vuesAventuriers) {
            if (id == vA.getID()) {
                vA.afficherCardsBorder();
            }
        }   
        giveButton.setEnabled(false);
    }

    
    public void cacherCardsBorder() {
        for (VueAventurier vA : vuesAventuriers) {
            vA.cacherCardsBorder();
        }
        defausserButton.setEnabled(true);
        giveButton.setEnabled(true);
        
    }
    
    public void cacherCardsBorder(int numCarte, int idAventurier) {
        for (VueAventurier vA : vuesAventuriers) {
            vA.cacherCardsBorder(numCarte, idAventurier);
        }
        defausserButton.setEnabled(true);
        giveButton.setEnabled(true);
        
    }
    
    // aL : joueurs qui peuvent recevoir une carte, numCarte : numéro de la carte à donner
    // Rajoute une bordure rouge aux joueurs auxquels on peut donner une carte
    public void choisirJoueur(ArrayList<Aventurier> aL, int numCarte) {
        for (Aventurier aventurier : aL) {
            for (VueAventurier vA : vuesAventuriers) {
                if (aventurier.getId() == vA.getID()) {
                    vA.setNameBackground(Parameters.COULEUR_JOUEUR_SELECTIONNABLE);
                    vA.setCardToGive(numCarte);
                }
            }
        }
//        defausserButton.setEnabled(true);
//        giveButton.setEnabled(true);
    }
    
    // Cache les bordures ajoutées à chaque VueAventurier
    public void cacherNameBackground(){
        for (VueAventurier vA : vuesAventuriers) {
            if (vA.getNamePanel().getBackground() == Parameters.COULEUR_JOUEUR_SELECTIONNABLE) {
                System.out.println("yeet");
                vA.setNameBackground(Color.WHITE);
            }
        }
    }
    
    public MessageBox getMessageBox() {
        return messageBox;
    }
    
    public void afficherJoueurCourant(int idAventurierCourant){
        for (VueAventurier vA : vuesAventuriers) {
            if (vA.getID() == idAventurierCourant) {
                vA.setNameBackground(Parameters.COULEUR_JOUEUR_COURANT);
            } else {
                vA.setNameBackground(Color.WHITE);
            }
        }
    }
            
    public void afficher() {
        gameFrame.setVisible(true);
        for (VueAventurier va : vuesAventuriers) {
            va.afficher();
        }
        vueGrille.setVisible(true);
//        vueNiveau.afficher();
        
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
    
    public ArrayList<VueAventurier> getVuesAventuriers() {
        return vuesAventuriers;
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
    

    

