package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import util.*;

/**
 *
 * @author marquest
 */
public class VueMenu implements Observe {
    
    private JFrame menuWindow;
    Integer[] nbJoueurs = new Integer[]{2, 3, 4};
    String[] couleurs = new String[]{"Vert","Violet","Jaune","Noir","Gris","Bronze","Bleu","Rouge"};
    private JTextField joueur1, joueur2, joueur3, joueur4;
    private JButton playButton, randomButton, random2Button, quitButton;
    private final ArrayList<JTextField> nomsJoueurs;
    private JPanel panelBoutons, playersPanel;
    private JComboBox listeNbJoueurs, couleurJoueur1, couleurJoueur2, couleurJoueur3, couleurJoueur4;
    private final ArrayList<JComboBox> couleursJoueurs;
    private Observateur observateur;    

    public VueMenu() {
        
        // ---------------------------------------------------------------------
        // ---------------------------  MENU WINDOW  ---------------------------
        // ---------------------------------------------------------------------    
        
        menuWindow = new JFrame();
        menuWindow.setTitle("Ile Interdite");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        menuWindow.setResizable(Parameters.RESIZABLE);
        
        menuWindow.setLayout(new BorderLayout(100,100));
        menuWindow.setSize(600, 800);
        
        JPanel panelTitre = new JPanel();
        menuWindow.add(panelTitre, BorderLayout.NORTH);
        JLabel labelTitre = new JLabel("Ile Interdite");
        panelTitre.add(labelTitre);
        labelTitre.setFont(labelTitre.getFont ().deriveFont (64.0f));
        
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new GridLayout(6, 1));
        menuWindow.add(panelBoutons, BorderLayout.CENTER);
        
        JPanel emptyPanel1 = new JPanel();
        JPanel emptyPanel2 = new JPanel();
        
        menuWindow.add(emptyPanel1, BorderLayout.WEST);
        menuWindow.add(emptyPanel2, BorderLayout.EAST);
        
        playButton = new JButton("Jouer");
        playersPanel = new JPanel();
        randomButton = new JButton("???");
        random2Button = new JButton("???");
        quitButton = new JButton("Quitter");
        
            panelBoutons.add(playButton);
            panelBoutons.add(playersPanel);

            playersPanel.setLayout(new GridLayout(4,3,10,10));
            
            // ------- PLAYERSPANEL --------
            
            nomsJoueurs = new ArrayList<>();
            couleursJoueurs = new ArrayList<>();
            
            joueur1 = new JTextField();
            joueur2 = new JTextField();
            joueur3 = new JTextField();
            joueur4 = new JTextField();  
            
            couleurJoueur1 = new JComboBox(couleurs);       
            couleurJoueur2 = new JComboBox(couleurs);       
            couleurJoueur3 = new JComboBox(couleurs);       
            couleurJoueur4 = new JComboBox(couleurs);       
            
            nomsJoueurs.add(joueur1);
            nomsJoueurs.add(joueur2);
            nomsJoueurs.add(joueur3);
            nomsJoueurs.add(joueur4);
            
            couleursJoueurs.add(couleurJoueur1);
            couleursJoueurs.add(couleurJoueur2);
            couleursJoueurs.add(couleurJoueur3);
            couleursJoueurs.add(couleurJoueur4);

            playersPanel.add(new JLabel("Joueur 1: "));
            playersPanel.add(joueur1);
            playersPanel.add(couleurJoueur1);
            
            playersPanel.add(new JLabel("Joueur 2: "));
            playersPanel.add(joueur2);
            playersPanel.add(couleurJoueur2);

            playersPanel.add(new JLabel("Joueur 3: "));
            playersPanel.add(joueur3);
            playersPanel.add(couleurJoueur3);

            playersPanel.add(new JLabel("Joueur 4: "));
            playersPanel.add(joueur4);
            playersPanel.add(couleurJoueur4);


            panelBoutons.add(randomButton);
            panelBoutons.add(random2Button);
            panelBoutons.add(quitButton);
            
            JPanel panelVide = new JPanel();
            panelBoutons.add(panelVide);
            
            playButton.setEnabled(false);
            joueur3.setVisible(false);
            joueur4.setVisible(false);
            couleurJoueur3.setVisible(false);
            couleurJoueur4.setVisible(false);
            
            
            joueur1.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                  changed();
                }
                public void removeUpdate(DocumentEvent e) {
                  changed();
                }
                public void insertUpdate(DocumentEvent e) {
                  changed();
                }
                
                public void changed() {
                    if (joueur1.getText().equals("")){
                        couleurJoueur3.setVisible(false);
                        couleurJoueur4.setVisible(false);
                        joueur3.setVisible(false);
                        joueur4.setVisible(false);
                        joueur3.setText("");
                        joueur4.setText("");
                        playButton.setEnabled(false);
                        
                    } else if (!joueur2.getText().equals("")){
                        playButton.setEnabled(true);
                        joueur3.setVisible(true);
                        couleurJoueur3.setVisible(true);
                    }
                }
            });
            
            
            joueur2.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                  changed();
                }
                public void removeUpdate(DocumentEvent e) {
                  changed();
                }
                public void insertUpdate(DocumentEvent e) {
                  changed();
                }
                
                public void changed() {
                   if (joueur2.getText().equals("")){
                        couleurJoueur3.setVisible(false);
                        couleurJoueur4.setVisible(false);
                        joueur3.setVisible(false);
                        joueur4.setVisible(false);
                        joueur3.setText("");
                        joueur4.setText("");
                        playButton.setEnabled(false);
                        
                    } else if (!joueur2.getText().equals("")){
                        playButton.setEnabled(true);
                        joueur3.setVisible(true);
                        couleurJoueur3.setVisible(true);
                    }
                }
            });
            
            
            joueur3.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                  changed();
                }
                public void removeUpdate(DocumentEvent e) {
                  changed();
                }
                public void insertUpdate(DocumentEvent e) {
                  changed();
                }
                
                public void changed() {
                   if (joueur3.getText().equals("")){
                        couleurJoueur4.setVisible(false);
                        joueur4.setVisible(false);
                        joueur4.setText("");
                        playButton.setEnabled(false);
                        
                    } else if (!joueur3.getText().equals("")){
                        playButton.setEnabled(true);
                        joueur4.setVisible(true);
                        couleurJoueur4.setVisible(true);
                    }
                }
            });
 
            
        // Jouer: change les fenêtres lorsque l'on appuie sur le bouton Jouer   
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int nbJoueurs = 0;
                for (JTextField nom : nomsJoueurs) {
                    if (!nom.getText().equals("")) {
                        nbJoueurs++;
                    }
                }                                
                Message m = new Message(Utils.Commandes.COMMENCER_JEU,nbJoueurs,0,null,0);                
                m.setNoms(nomsJoueurs);
                notifierObservateur(m);
              
           } 
        });
        
        // Quitter
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuWindow.dispose();
           } 
        });
    }
    
    public void afficher() {
        this.menuWindow.setVisible(true);
    }
    
    public void cacher() {
        this.menuWindow.setVisible(false);
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
