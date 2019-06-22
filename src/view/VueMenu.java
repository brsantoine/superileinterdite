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
    private Observateur observateur;    
    private JFrame menuWindow;
    Integer[] nbJoueurs = new Integer[]{2, 3, 4};
    String[] couleurs = new String[]{"(Couleur)", "Vert", "Violet", "Jaune", "Noir", "Gris", "Bronze", "Bleu", "Rouge"};
    String[] difficulte = new String[]{"Novice", "Normal", "Elite", "Légendaire"};
    private JButton playButton, demoButton, quitButton;
    private JPanel panelBoutons, playersPanel, eauPanel;
    private final ArrayList<JTextField> nomsJoueurs;
    private final ArrayList<JComboBox> couleursJoueurs;
    private JComboBox niveauxDifficulte;
    
    public VueMenu() {
        
        // ---------------------------------------------------------------------
        // ---------------------------  MENU WINDOW  ---------------------------
        // ---------------------------------------------------------------------    
        
        menuWindow = new JFrame();
        menuWindow.setTitle("Ile Interdite");
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
        eauPanel = new JPanel();
        demoButton = new JButton("???");
        quitButton = new JButton("Quitter");
        
        panelBoutons.add(playButton);
        panelBoutons.add(playersPanel);

        playersPanel.setLayout(new GridLayout(4,3,10,10));

        // ------- PLAYERSPANEL --------

        nomsJoueurs = new ArrayList<>();
        couleursJoueurs = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            nomsJoueurs.add(new JTextField());
        }
        
        for (int i = 0; i < 4; i++) {
            couleursJoueurs.add(new JComboBox(couleurs));
        }

        for (int i = 0; i < 4; i++) {
            playersPanel.add(new JLabel("Joueur " + (i+1) + " :"));
            playersPanel.add(nomsJoueurs.get(i));
            playersPanel.add(couleursJoueurs.get(i));
        }
        
        //-- Eau panel
        eauPanel.setLayout(new GridLayout(3,3, 10,10));
        for (int i = 0; i < 3; i++) {
            eauPanel.add(new JPanel());
        }
        eauPanel.add(new JLabel("Difficulté :"), BorderLayout.CENTER);
        niveauxDifficulte = new JComboBox(difficulte);
        eauPanel.add(new JPanel());
        eauPanel.add(niveauxDifficulte, BorderLayout.CENTER);
        for (int i = 0; i < 3; i++) {
            eauPanel.add(new JPanel());
        }
        
        panelBoutons.add(eauPanel);
        panelBoutons.add(demoButton);
        panelBoutons.add(quitButton);

        JPanel panelVide = new JPanel();
        panelBoutons.add(panelVide);

        playButton.setEnabled(false);
        
        nomsJoueurs.get(2).setVisible(false);
        nomsJoueurs.get(3).setVisible(false);
        couleursJoueurs.get(2).setVisible(false);
        couleursJoueurs.get(3).setVisible(false);
        
        for (JTextField nom : nomsJoueurs) {
            nom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (nom.getText().length() >= 17 ) // limit to 17 characters
                    e.consume();
                }
            });
        }
        
        nomsJoueurs.get(0).getDocument().addDocumentListener(new DocumentListener() {
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
                if (estPret(2)) {
                    playButton.setEnabled(true);
                    nouveauJoueur(2);
                } else {
                    playButton.setEnabled(false);
                    if (nomsJoueurs.get(0).getText().equals("")) {
                        nomReset(0);
                    }
                }
            }
        });


        nomsJoueurs.get(1).getDocument().addDocumentListener(new DocumentListener() {
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
               if (estPret(2)) {
                    playButton.setEnabled(true);
                   nouveauJoueur(2);
               } else {
                   playButton.setEnabled(false);
                   if (nomsJoueurs.get(1).getText().equals("")) {
                        nomReset(1);
                    }
               }
            }
        });


        nomsJoueurs.get(2).getDocument().addDocumentListener(new DocumentListener() {
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
               if (estPret(3)) {
                    playButton.setEnabled(true);
                   nouveauJoueur(3);
               } else {
                   playButton.setEnabled(false);
                   if (nomsJoueurs.get(2).getText().equals("")) {
                        nomReset(2);
                    }
               }
            }
        });

        nomsJoueurs.get(3).getDocument().addDocumentListener(new DocumentListener() {
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
               if (estPret(4)) {
                   playButton.setEnabled(true);
               } else {
                   playButton.setEnabled(false);
                   if (nomsJoueurs.get(3).getText().equals("")) {
                        nomReset(3);
                    }
               }
            }
        });
        
        couleursJoueurs.get(0).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    couleurChoisi(0);
                    if (estPret(2)) {
                       playButton.setEnabled(true);
                       nouveauJoueur(2);
                    } else {
                        playButton.setEnabled(false);
                        deleteJoueur(2);
                        deleteJoueur(3);
                    }
                }
            }
        });

        couleursJoueurs.get(1).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    couleurChoisi(1);
                    if (estPret(2)) {
                        playButton.setEnabled(true);
                        nouveauJoueur(2);
                    } else {
                        playButton.setEnabled(false);
                        deleteJoueur(2);
                        deleteJoueur(3);
                    }
                }
            }
        });

        couleursJoueurs.get(2).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    couleurChoisi(2);
                    if (estPret(3)) {
                        playButton.setEnabled(true);
                        nouveauJoueur(3);
                    } else {
                        playButton.setEnabled(false);
                        deleteJoueur(3);
                    }
                }
            }
        });

        couleursJoueurs.get(3).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    couleurChoisi(3);
                    if (estPret(4)) {
                        playButton.setEnabled(true);
                    } else {
                        playButton.setEnabled(false);
                    }
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
                m.setCouleurs(couleursJoueurs);
                m.setNiveauEau(niveauxDifficulte);
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
        this.deleteJoueur(3);
        this.deleteJoueur(2);
        nomsJoueurs.get(0).setText("");
        nomsJoueurs.get(1).setText("");
        niveauxDifficulte.setSelectedIndex(0);
    }
    
    public void cacher() {
        this.menuWindow.setVisible(false);
    }
    
    public boolean estPret(int k) {
        int j = 0;
        for (int i = 0; i < k; i++) {
            if ((!nomsJoueurs.get(i).getText().equals("")) && (!couleursJoueurs.get(i).getSelectedItem().equals("(Couleur)"))){
                j++;
            }
        }
        return j == k;
    }
    
    public void couleurChoisi(int k) {
        if (!couleursJoueurs.get(k).getSelectedItem().equals("(Couleur)")) {
            couleursJoueurs.get((k+1)%4).removeItem(couleursJoueurs.get(k).getSelectedItem());
            couleursJoueurs.get((k+2)%4).removeItem(couleursJoueurs.get(k).getSelectedItem());
            couleursJoueurs.get((k+3)%4).removeItem(couleursJoueurs.get(k).getSelectedItem());
            
            couleursJoueurs.get(k).setEnabled(false);
        }
    }
    
    public void nomReset(int k) {
        if (couleursJoueurs.get(k).getSelectedIndex() != 0) {
            couleursJoueurs.get((k+1)%4).addItem(couleursJoueurs.get(k).getSelectedItem());
            couleursJoueurs.get((k+2)%4).addItem(couleursJoueurs.get(k).getSelectedItem());
            couleursJoueurs.get((k+3)%4).addItem(couleursJoueurs.get(k).getSelectedItem());
            couleursJoueurs.get(k).setSelectedIndex(0);
        }
        
        couleursJoueurs.get(k).setEnabled(true);
    }
  
    public void nouveauJoueur(int k) {
        nomsJoueurs.get(k).setVisible(true);
        couleursJoueurs.get(k).setVisible(true);
    }
    
    public void deleteJoueur(int k) {
        nomsJoueurs.get(k).setVisible(false);
        couleursJoueurs.get(k).setVisible(false);
        nomReset(k);
        nomsJoueurs.get(k).setText("");
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
