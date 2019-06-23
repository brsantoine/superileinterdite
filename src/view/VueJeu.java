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
    
    private JFrame leftFrame, gameFrame, piocherFrame;
    private Observateur observateur;
    private VueNiveau vueNiveau;
    private VueGrille vueGrille;
    private MessageBox messageBox;
    private ArrayList<VueAventurier> vuesAventuriers;
    private ArrayList<VueCarte> vuesCartesPioche;
    private JButton seDeplacerButton, assecherButton, endTurnButton, helicoButton, giveButton, defausserButton, recupererButton, deplacerAutreButton, actionSpecialeButton, recommencerButton, annulerButton, remplacerButton;
    private JPanel westPanel, eastPanel, gridButtonsPanel, aventurierButtonsPanel, aventuriersPanel, cardsProposesPanel, cardsProposesButtonsPanel, cardsProposesButtonsCenterPanel, cardsProposesGridPanel;  
    private JLabel actionsRemainingLabel, cartesPiocheesLabel;

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
            deplacerAutreButton = new JButton("Deplacer autre joueur");                  
            recupererButton = new JButton("Recuperer Trésor");  
            recommencerButton = new JButton("Recommencer");
            actionsRemainingLabel = new JLabel("3 actions restantes");
            recommencerButton.setEnabled(false);            
            
            gridButtonsPanel.add(recommencerButton);
            gridButtonsPanel.add(actionsRemainingLabel);
            gridButtonsPanel.add(seDeplacerButton);
            gridButtonsPanel.add(assecherButton);
            gridButtonsPanel.add(endTurnButton);
            gridButtonsPanel.add(helicoButton);
            gridButtonsPanel.add(deplacerAutreButton);
        
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
            actionSpecialeButton = new JButton("Utiliser une carte");
            recupererButton = new JButton("Recuperer Trésor");      
            aventurierButtonsPanel.add(giveButton);
            aventurierButtonsPanel.add(defausserButton);
            aventurierButtonsPanel.add(recupererButton);
            aventurierButtonsPanel.add(actionSpecialeButton);
            
            
        // --------------------------------------------------------------------
        // -------------------------  PIOCHER WINDOW  -------------------------
        // --------------------------------------------------------------------     
        
        piocherFrame = new JFrame();     
        cardsProposesPanel = new JPanel();
        cardsProposesButtonsPanel = new JPanel();
        

        piocherFrame.setLayout(new BorderLayout());
//        piocherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        piocherFrame.setResizable(Parameters.RESIZABLE);
        piocherFrame.setUndecorated(Parameters.UNDECORATED);
        piocherFrame.setLocation(Parameters.LEFT_FRAME_WIDTH + Parameters.HORIZONTAL_BUFFER + Parameters.HORIZONTAL_SPACE + (Parameters.GRID_WIDTH/4),
                                 Parameters.VERTICAL_BUFFER - Parameters.TASKBAR_BUFFER + Parameters.VERTICAL_SPACE*2 + (Parameters.GRID_HEIGHT/3));
        piocherFrame.setSize(Parameters.PIOCHER_FRAME_WIDTH, Parameters.PIOCHER_FRAME_HEIGHT);  
        
        piocherFrame.add(cardsProposesPanel, BorderLayout.NORTH);
        piocherFrame.add(cardsProposesButtonsPanel, BorderLayout.CENTER);
        piocherFrame.add(new JPanel(), BorderLayout.SOUTH);
        
        
            // ------------- cardsProposesPanel -------------
            
            cardsProposesPanel.setPreferredSize(new Dimension(Parameters.PIOCHER_FRAME_WIDTH, Parameters.PIOCHER_FRAME_HEIGHT*87/100));            
            cardsProposesPanel.setLayout(new BorderLayout());
            
            cartesPiocheesLabel = new JLabel("Cartes piochées: ", SwingConstants.CENTER);            
            cartesPiocheesLabel.setFont(cartesPiocheesLabel.getFont ().deriveFont (20.0f));
            cardsProposesGridPanel = new JPanel();
            
            cardsProposesPanel.add(cartesPiocheesLabel, BorderLayout.NORTH);
            cardsProposesPanel.add(cardsProposesGridPanel, BorderLayout.CENTER);
            
            vuesCartesPioche = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                VueCarte vC = new VueCarte(-1, j);
                vuesCartesPioche.add(vC);
            }
            for (VueCarte vueCarte : vuesCartesPioche) {
                cardsProposesGridPanel.add(vueCarte);
            }

            // ------------- cardsProposesButtonsPanel -------------
            
            cardsProposesButtonsPanel.setLayout(new BorderLayout());
            
            cardsProposesButtonsCenterPanel = new JPanel(new GridLayout(1,2,30,0));
            
            cardsProposesButtonsPanel.add(cardsProposesButtonsCenterPanel, BorderLayout.CENTER);
            cardsProposesButtonsPanel.add(new JPanel(), BorderLayout.WEST);
            cardsProposesButtonsPanel.add(new JPanel(), BorderLayout.EAST);
            
            remplacerButton = new JButton("Remplacer");
            annulerButton = new JButton("Annuler");   
            
            cardsProposesButtonsCenterPanel.add(remplacerButton);
            cardsProposesButtonsCenterPanel.add(annulerButton);
            
        // ============= LISTENERS: GRIDBUTTONS =============
        
        // -------- seDeplacerButton et assecherButton (changer le mode d'actions) --------
        seDeplacerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cacherCardsBorder();
                Message m = new Message(Utils.Commandes.SE_DEPLACER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });        
        
        helicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cacherCardsBorder();
                Message m = new Message(Utils.Commandes.SE_DEPLACER, 0, 0, null, 0);
                m.setHelico(true);
                notifierObservateur(m);
            }
        });        

        assecherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cacherCardsBorder();
                Message m = new Message(Utils.Commandes.ASSECHER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        deplacerAutreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(Utils.Commandes.DEPLACER_AUTRE, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cacherCardsBorder();
                Message m = new Message(Utils.Commandes.FIN_TOUR, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        // ============= LISTENERS: AVENTURIERSBUTTONS =============
        
        giveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveButton.setEnabled(false);
                Message m = new Message(Utils.Commandes.DONNER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        defausserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(Utils.Commandes.DEFAUSSER_CARTE, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        recupererButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(Utils.Commandes.RECUPERER_TRESOR, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        actionSpecialeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                Message m = new Message(Utils.Commandes.ACTION_SPECIALE, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
      
        recommencerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                recupererButton.setEnabled(false);
                Message m = new Message(Utils.Commandes.RECOMMENCER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        // ============= LISTENERS: CARDSPROPOSESBUTTONS =============
        
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(Utils.Commandes.RECOMMENCER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });
        
        remplacerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(Utils.Commandes.REMPLACER, 0, 0, null, 0);
                notifierObservateur(m);
            }
        });

    }
    
    // ===================================== METHODES =====================================
    
    public void possibleFinTour() {
        endTurnButton.setEnabled(true);
    }
    
    // Empêche de finir le tour (pour le plongeur lorsqu'il est sur une case coulée)
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
    
    // Affiche les cartes piochées si le joueur courant en a déjà 5 dans sa main
    public void afficherCartesPioche(ArrayList<CarteMain> cartes) {
        // Enleve toutes les cartes affichées
        for (VueCarte vC : vuesCartesPioche) {
            vC.removeCardImage();
        }
        // Affiche les nouvelles cartes piochées avec une bordure rouge
        int x = 0;
        for (Carte carte : cartes) {    
            vuesCartesPioche.get(x).addCardImage(carte);
            vuesCartesPioche.get(x).setBorder(new LineBorder(Color.RED, 3)); 
            x++;
        }
    }
    
    // Enlève l'image de la carte d'id idCarte
    public void removeCardReplaced(int idCarte) {
        for (VueCarte vC : vuesCartesPioche) {
            if (vC.getNumCarte() == idCarte) {
                vC.removeCardImage();
                vC.setBorder(new LineBorder(new JButton().getBackground()));
            } else if (vC.getHasImage()){
                vC.setBorder(new LineBorder(Color.RED, 3));
            }
        }
    }
    
    // Enleve l'image de la derniere carte après une défausse
    public void defausseLastCard(int idAventurier) {
        for (VueAventurier vA : vuesAventuriers) {
            if (vA.getID() == idAventurier) {
                vA.defausseLastCard();
            }
        }
    }
//    // Enleve l'image de la derniere carte des cartes piochées
//    public void defausseLastCardPioche(int idCarte) {
//        for (VueCarte vC : vuesCartesPioche) {
//            if (vC.getNumCarte() == idCarte) {
//                vuesCartesPioche.get(vuesCartesPioche.size()-1).removeCardImage();
//            }
//        }
//    }
    
    // Affiche des bordures rouges autour de chaque carte de chaque aventurier
    public void afficherCardsBorder() {
        for (VueAventurier vA : vuesAventuriers) {
            vA.afficherCardsBorder();
        }   
    }
    
    // Affiche des bordures rouges autour de chaque carte de l'aventurier d'id idAventurier
    public void afficherCardsBorder(int idAventurier) {
        for (VueAventurier vA : vuesAventuriers) {
            if (idAventurier == vA.getID()) {
                vA.afficherCardsBorder();
            }
        }   
    }
    
    // Affiche des bordures rouges autour des cartes Action Spéciales
    public void afficherCardsActionSpecialeBorder() {
        for (VueAventurier vA : vuesAventuriers) {
                vA.afficherCardsActionSpecialeBorder();
        }   
    }
    
    // Enlève les bordures des cartes affichées après une pioche, sauf celle choisie auquelle on met une bordure bleue
    public void cacherCardsPiocheBorder(int numCarte) {
        int x = 0;
        for (VueCarte vC : vuesCartesPioche) {
            if (vC.getNumCarte() == numCarte) {
                vuesCartesPioche.get(x).setBorder(new LineBorder(Color.BLUE, 3)); 
            } else {
                vuesCartesPioche.get(x).setBorder(new LineBorder(new JButton().getBackground()));
            }
            x++;
        }
    }
    
    // Enlève les bordures de toutes les cartes
    public void cacherCardsBorder() {
        for (VueAventurier vA : vuesAventuriers) {
            vA.cacherCardsBorder();
        }
    }
    
    // Enlève les bordures de toutes les cartes de chaque vueAventuriker sauf celle choisie  auquelle on met une bordure bleue
    public void cacherCardsBorder(int numCarte, int idAventurier) {
        for (VueAventurier vA : vuesAventuriers) {
            if (vA.getID() != idAventurier) {
                vA.cacherCardsBorder();
            } else {
                vA.cacherCardsBorder(numCarte, idAventurier);
            }
        }
    }
    
    // aL : joueurs qui peuvent recevoir une carte, numCarte : numéro de la carte à donner
    // Rajoute un arrière plan bleu clair au panel Nom aux joueurs auxquels on peut donner une carte
    public void choisirJoueur(ArrayList<Aventurier> aL, int numCarte) {
        for (Aventurier aventurier : aL) {
            for (VueAventurier vA : vuesAventuriers) {
                if (aventurier.getId() == vA.getID()) {
                    vA.setNameBackground(Parameters.COULEUR_JOUEUR_SELECTIONNABLE);
                    vA.setCardToGive(numCarte);
                }
            }
        }
    }
    
    public void choisirJoueur(ArrayList<Aventurier> aL) {
        for (Aventurier aventurier : aL) {
            for (VueAventurier vA : vuesAventuriers) {
                if (aventurier.getId() == vA.getID()) {
                    vA.setNameBackground(Parameters.COULEUR_JOUEUR_SELECTIONNABLE);
                }
            }
        }
    }
    
    // Enlève l'arrière plan ajouté à chaque VueAventurier
    public void cacherNameBackground(){
        for (VueAventurier vA : vuesAventuriers) {
            if (vA.getNamePanel().getBackground() == Parameters.COULEUR_JOUEUR_SELECTIONNABLE) {
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
        leftFrame.setVisible(true);
        for (VueAventurier va : vuesAventuriers) {
            va.afficher();
        }
        piocherFrame.setVisible(true);
    }
    
    public void cacher(){
        gameFrame.setVisible(false);
        leftFrame.setVisible(false);
    }
    
    public void afficherDefaite() {
        System.out.println("vous avez perdu");
    }
    
    public void afficherVictoire() {
       System.out.println("vous avez gagné");
    }
    
    public void afficherBoutonRecommencer(){
        recommencerButton.setEnabled(true);
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
        for (VueCarte vC : vuesCartesPioche) {
            vC.addObservateur(o);
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
        deplacerAutreButton.setEnabled(true);
    }
    public void impossibleDeplacerAutre() {
        deplacerAutreButton.setEnabled(false);
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
    
    public void possibleActionSpeciale() {
        actionSpecialeButton.setEnabled(true);
    }
    public void impossibleActionSpeciale() {
        actionSpecialeButton.setEnabled(false);
    }
    
    public void possibleAnnuler() {
        annulerButton.setEnabled(true);
    }
    public void impossibleAnnuler() {
        annulerButton.setEnabled(false);
    }
    
    public void possibleRemplacer() {
        remplacerButton.setEnabled(true);
    }
    public void impossibleRemplacer() {
        remplacerButton.setEnabled(false);
    }
    
}
    
