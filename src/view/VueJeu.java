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
    private JPanel southPanel, eastPanel, middleSouthPanel;  
    private JButton seDeplacerButton, assecherButton, endTurnButton, actionsRemainingButton, helicoButton, giveButton, defausserButton, recupererButton, deplacerAutre;

    
    public VueJeu(ArrayList<Aventurier> aventuriers, ArrayList<JTextField> noms, ArrayList<JComboBox> couleurs) {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // ---------------------------------------------------------------------
        // ---------------------------  LEFT WINDOW  ---------------------------
        // ---------------------------------------------------------------------

        leftFrame = new JFrame() ;
        leftFrame.setLayout(new GridLayout(2,1,0,20));
        leftFrame.setLocation(20, 20);
        leftFrame.setSize(350, (int) screenSize.getHeight() - 100);
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
        gameFrame.setLayout(new BorderLayout(10,10));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        gameFrame.setResizable(Parameters.RESIZABLE);
        gameFrame.setTitle("Ile Interdite");
        gameFrame.setLocation(370, 20);
        gameFrame.setSize((int) screenSize.getWidth() -390, (int) screenSize.getHeight() - 100);  
        
        vueGrille = new VueGrille();
        eastPanel = new JPanel();
        southPanel = new JPanel();
        
        gameFrame.add(vueGrille);
        gameFrame.add(eastPanel, BorderLayout.EAST);
        gameFrame.add(southPanel, BorderLayout.SOUTH);  
        
        // ------------ EASTPANEL ------------

        eastPanel.setPreferredSize(new Dimension(600, 1000));
        eastPanel.setLayout(new GridLayout(4,1,0,10));
//        eastPanel.setSize(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER*4);
        vuesAventuriers = new ArrayList<>();
        int x = 0;
        for(Aventurier av : aventuriers){
            VueAventurier va = new VueAventurier(av.getId(), av.getRole() + " (" + noms.get(x).getText() + ")");
            this.vuesAventuriers.add(va);
            x++;
        }
        
        for (VueAventurier aventurier : vuesAventuriers) {
            eastPanel.add(aventurier);
        }
        
        // ------------ SOUTHPANEL ------------
//        southPanel.setPreferredSize(new Dimension((int) screenSize.getWidth(), 150));
        southPanel.setLayout(new BorderLayout());
        
        middleSouthPanel = new JPanel();
        southPanel.add(middleSouthPanel, BorderLayout.CENTER);
//        southPanel.add(messageBox, BorderLayout.WEST);
        
        // Nombre d'actions restantes et boutons assécher et se déplacer
        seDeplacerButton = new JButton("Se déplacer");
        helicoButton = new JButton("Hélicoptère");
        deplacerAutre = new JButton("Deplacer un autre joueur");
        assecherButton = new JButton("Assécher");  
        endTurnButton = new JButton("Fin tour");        
        actionsRemainingButton = new JButton("3 actions restantes");
        giveButton = new JButton("Donner carte");
        defausserButton = new JButton("Défausser une carte");
        recupererButton = new JButton("Recuperer un trésor");

        middleSouthPanel.add(actionsRemainingButton);
        middleSouthPanel.add(assecherButton);
        middleSouthPanel.add(seDeplacerButton);
        middleSouthPanel.add(endTurnButton);
        middleSouthPanel.add(helicoButton);
        middleSouthPanel.add(deplacerAutre);
        middleSouthPanel.add(giveButton);
        middleSouthPanel.add(defausserButton);
        middleSouthPanel.add(recupererButton);
        
        
        // LISTENER 
        // -------- seDeplacerButton et assecherButton (changer le mode d'actions) --------
        seDeplacerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seDeplacerButton.setEnabled(false);
                cacherCardsBorder();
                Message m = new Message(Utils.Commandes.SE_DEPLACER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });        
        
        helicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helicoButton.setEnabled(false);
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
                cacherCardsBorder();
                Message m = new Message(Utils.Commandes.ASSECHER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        // -------- giveButton --------
        
        giveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveButton.setEnabled(false);
                Message m = new Message(Utils.Commandes.DONNER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        // -------- defausserButton --------
        defausserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defausserButton.setEnabled(false);
                Message m = new Message(Utils.Commandes.DEFAUSSER_CARTE, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        deplacerAutre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deplacerAutre.setEnabled(false);
                Message m = new Message(Utils.Commandes.DEPLACER_AUTRE, 0, 0, null, 0);
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
        
        
        recupererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                recupererButton.setEnabled(false);
                Message m = new Message(Utils.Commandes.RECUPERER_TRESOR, 0, 0, null, 0);
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
                    vA.setBorder(new LineBorder(Color.RED, 5));
                    vA.setCardToGive(numCarte);
                }
            }
        }
//        defausserButton.setEnabled(true);
//        giveButton.setEnabled(true);
    }
    public void choisirJoueur(ArrayList<Aventurier> aL) {
        for (Aventurier aventurier : aL) {
            for (VueAventurier vA : vuesAventuriers) {
                if (aventurier.getId() == vA.getID()) {
                    vA.setBorder(new LineBorder(Color.RED, 5));
                }
            }
        }
//        defausserButton.setEnabled(true);
//        giveButton.setEnabled(true);
    }
    
    // Cache les bordures ajoutées à chaque VueAventurier
    public void cacherAventuriersBorder(){
        for (VueAventurier vA : vuesAventuriers) {
            if (((LineBorder) vA.getBorder()).getLineColor() == Color.RED) {
                vA.setBorder(new LineBorder(new JButton().getBackground()));
            }
        }
    }
    
    public MessageBox getMessageBox() {
        return messageBox;
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
      gameFrame.setEnabled(false);
      System.out.println("vous avez perdu");
    }
    
    public void afficherVictoire() {
      gameFrame.setEnabled(false);
      System.out.println("vous avez gagné");
    }
    
    public void activerHelico() {
        helicoButton.setEnabled(true);
    }
    
    public void desactiverHelico() {
        helicoButton.setEnabled(false);
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
    
    public void possibleDeplacer() {
        seDeplacerButton.setEnabled(true);
    }
    public void impossibleDeplacer() {
        seDeplacerButton.setEnabled(false);
    }
    
    public void possibleDeplacerAutre() {
        deplacerAutre.setEnabled(true);
    }
    public void impossibleDeplacerAutre() {
        deplacerAutre.setEnabled(false);
    }
        
    public void possibleGiveCarte() {
        giveButton.setEnabled(true);
    }
    public void impossibleGiveCarte() {
        giveButton.setEnabled(false);
    }
    
    public void possibleDefausser() {
        defausserButton.setEnabled(true);
    }
    public void impossibleDefausser() {
        defausserButton.setEnabled(false);
    }
    
    public void possibleRecupererTresor() {
        recupererButton.setEnabled(true);
    }
    public void impossibleRecupererTresor() {
        recupererButton.setEnabled(false);
    }
    
    
    
    
}
    

    

