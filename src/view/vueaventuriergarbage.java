////apackage superileinterdite;
//
//import model.Aventurier;
//import java.util.*;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.border.LineBorder;
//import util.Message;
//import util.Utils;
//        
//public class VueAventurier {
//    
//    private JFrame gameWindow;
//
//    public VueAventurier() {
//        
//
//        // ---------------------------------------------------------------------
//        // ---------------------------  GAME WINDOW  ---------------------------
//        // ---------------------------------------------------------------------
//        
//        // -------- Setup window --------
//        gameWindow = new JFrame();
//        gameWindow.setTitle("Ile Interdite");
//        gameWindow.setLayout(new BorderLayout(200,10));
//        gameWindow.setSize(1440, 900);
//        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        
//        // -------- Main window --------
//        JPanel gamePanelNorth = new JPanel();
//        JPanel gamePanelEast = new JPanel();
//        JPanel gamePanelWest = new JPanel();
//        JPanel gamePanelSouth = new JPanel();
//        JPanel gamePanelCenter = new JPanel();
//        
//        gameWindow.add(gamePanelNorth, BorderLayout.NORTH);
//        gameWindow.add(gamePanelEast, BorderLayout.EAST);
//        gameWindow.add(gamePanelWest, BorderLayout.WEST);
//        gameWindow.add(gamePanelSouth, BorderLayout.SOUTH);
//        gameWindow.add(gamePanelCenter, BorderLayout.CENTER);
//        
//     
//        // -------- gamePanelNorth --------
//        gamePanelNorth.setLayout(new BorderLayout(5,0));        
//        
//        JPanel firstPlayerPanel = new JPanel();
//        JPanel secondPlayerPanel = new JPanel();
//        JPanel thirdPlayerPanel = new JPanel();
//
//        gamePanelNorth.add(firstPlayerPanel, BorderLayout.WEST);    
//        gamePanelNorth.add(secondPlayerPanel, BorderLayout.CENTER);  
//        gamePanelNorth.add(thirdPlayerPanel, BorderLayout.EAST);  
//        
//            // -------- firstPlayerPanel (haut gauche de la fenêtre) --------
//            firstPlayerPanel.setLayout(new BorderLayout());
//            firstPlayerPanel.setPreferredSize(new Dimension(gameWindow.getSize().width/3,gameWindow.getSize().height/6));
//
//            JPanel firstPlayerCardsPanel = new JPanel();
//            firstPlayerPanel.add(firstPlayerCardsPanel, BorderLayout.CENTER);   
//            
//                // -------- firstPlayerCardsPanel --------
//                firstPlayerCardsPanel.setLayout(new GridLayout(1,5));
//                for (int i = 0; i < 5; i++) {
//                    firstPlayerCardsPanel.add(new JButton("Carte"));
//                }
//            firstPlayerPanel.add(new JButton("AVENTURIER"), BorderLayout.SOUTH);
//        
//            
//            // -------- secondPlayerPanel (haut centre de la fenêtre) --------
//            secondPlayerPanel.setLayout(new BorderLayout());
//            secondPlayerPanel.setPreferredSize(new Dimension(gameWindow.getSize().width/3,gameWindow.getSize().height/6));
//
//            JPanel secondPlayerCardsPanel = new JPanel();
//            secondPlayerPanel.add(secondPlayerCardsPanel, BorderLayout.CENTER);
//                // -------- secondPlayerCardsPanel --------
//                secondPlayerCardsPanel.setLayout(new GridLayout(1,5));
//                for (int i = 0; i < 5; i++) {
//                    secondPlayerCardsPanel.add(new JButton("Carte"));
//                }
//            secondPlayerPanel.add(new JButton("AVENTURIER"), BorderLayout.SOUTH);
//
//        
//            // -------- thirdPlayerPanel (haut droit de la fenêtre) --------
//            thirdPlayerPanel.setLayout(new BorderLayout());
//            thirdPlayerPanel.setPreferredSize(new Dimension(gameWindow.getSize().width/3,gameWindow.getSize().height/6));
//
//            JPanel thirdPlayerCardsPanel = new JPanel();
//            thirdPlayerPanel.add(thirdPlayerCardsPanel, BorderLayout.CENTER);            
//                // -------- thirdPlayerCardsPanel --------
//                thirdPlayerCardsPanel.setLayout(new GridLayout(1,5));
//                for (int i = 0; i < 5; i++) {
//                    thirdPlayerCardsPanel.add(new JButton("Carte"));
//                }
//            thirdPlayerPanel.add(new JButton("AVENTURIER"), BorderLayout.SOUTH);
//
//        
//
//        // -------- gamePanelEast (droite de la fenêtre) --------
//        
//        gamePanelEast.setLayout(new GridLayout(6,1,0,10));
//        gamePanelEast.setPreferredSize(new Dimension(200,0));
//        
//        gamePanelEast.add(new JButton("Cartes Inondation"));
//        gamePanelEast.add(new JButton("Defausse Inondation"));
//
//        // -------- gamePanelWest (gauche de la fenêtre) --------
//        gamePanelWest.setLayout(new GridLayout(6,1,0,10));
//        gamePanelWest.add(new JLabel(""));
//        gamePanelWest.add(new JLabel(""));
//
//        gamePanelWest.setPreferredSize(new Dimension(200,0));
//        JPanel helpSettingsPanel = new JPanel();
//        
//        gamePanelWest.add(helpSettingsPanel);   
//        
//            // -------- helpSettingsPanel --------
//            helpSettingsPanel.setLayout(new GridLayout(1,2,5,0));
//            helpSettingsPanel.add(new JButton("Options"));
//            helpSettingsPanel.add(new JButton("Aide"));
//            
//        gamePanelWest.add(new JButton("Cartes Tresor"));
//        gamePanelWest.add(new JButton("Defausse Tresor"));
//
//        // -------- gamePanelSouth (bas de la fenêtre) --------    
//        gamePanelSouth.setLayout(new BorderLayout(80,0));
//
//        JPanel tresorPanel = new JPanel();
//        JPanel currentPlayerCardsPanel = new JPanel();
//        JPanel actionsPanel = new JPanel();
//
//        gamePanelSouth.add(tresorPanel, BorderLayout.WEST);
//        gamePanelSouth.add(currentPlayerCardsPanel, BorderLayout.CENTER);
//        gamePanelSouth.add(actionsPanel, BorderLayout.EAST);
//
//            // -------- tresorPanel (bas gauche de la fenêtre) --------         
//            tresorPanel.setLayout(new BorderLayout());
//            tresorPanel.setPreferredSize(new Dimension(180,180));
//
//            // 4 cases vides pour les 4 trésors            
//            JPanel listeTresorsPanel = new JPanel();            
//            tresorPanel.add(listeTresorsPanel, BorderLayout.CENTER);
//            listeTresorsPanel.setLayout(new GridLayout(2,2));      
//            
//            for (int i = 0; i<4; i++) {
//                listeTresorsPanel.add(new JButton("Tresor"));
//            }            
//           
//            // -------- currentPlayerCardsPanel (bas centre de la fenêtre) --------
//         
//            currentPlayerCardsPanel.setLayout(new GridLayout(1,6,2,0));  
//            
//            // les 5 cartes du joueur + le rôle
//            for (int i = 0; i<5; i++) {      
//                currentPlayerCardsPanel.add(new JButton("Placeholder"));
//            }     
//                currentPlayerCardsPanel.add(new JButton("AVENTURIER"));
//
//            // -------- actionsPanel (bas droit de la fenêtre) --------
//            
//            actionsPanel.setLayout(new GridLayout(3,1));
//               actionsPanel.setPreferredSize(new Dimension(180,180));
//
//        // Label des grilles
////        this.updateDeplacement();
// 
//    }
//
//    /**
//     * 
//     * @param ac
//     */
//    public void afficherActionsPossibles(ArrayList<Utils.Commandes> ac) {
//        System.out.println(ac);
//    }
//
//    /**
//     * 
//     * @param tuiles
//     */
//    
//    
//
//    
// 
//
//}
//
