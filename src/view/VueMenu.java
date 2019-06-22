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
    String[] couleurs = new String[]{"(Aucune)", "Vert","Violet","Jaune","Noir","Gris","Bronze","Bleu","Rouge"};
    private JButton playButton, randomButton, random2Button, quitButton;
    private final ArrayList<JTextField> nomsJoueurs;
    private JPanel panelBoutons, playersPanel;
    private final ArrayList<JComboBox> couleursJoueurs;
    private Observateur observateur;    

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
        randomButton = new JButton("???");
        random2Button = new JButton("???");
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
            playersPanel.add(new JLabel("Joueur " + (i+1) + ": "));
            playersPanel.add(nomsJoueurs.get(i));
            playersPanel.add(couleursJoueurs.get(i));
        }
        
        panelBoutons.add(randomButton);
        panelBoutons.add(random2Button);
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
                if ((!couleursJoueurs.get(0).isEnabled()) && couleursJoueurs.get(0).getSelectedItem().equals("(Aucune)")) {
                    couleursJoueurs.get(0).setEnabled(true);
                }
                if (nomsJoueurs.get(0).getText().equals("")){
                    if (!couleursJoueurs.get(0).getSelectedItem().equals("(Aucune)")) {
                        couleursJoueurs.get(1).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(2).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(3).addItem(couleursJoueurs.get(0).getSelectedItem());
                    }
                    couleursJoueurs.get(0).setSelectedIndex(0);
                    couleursJoueurs.get(2).setVisible(false);
                    couleursJoueurs.get(3).setVisible(false);
                    nomsJoueurs.get(2).setVisible(false);
                    nomsJoueurs.get(3).setVisible(false);
                    nomsJoueurs.get(2).setText("");
                    nomsJoueurs.get(3).setText("");
                    playButton.setEnabled(false);

                } else if (!nomsJoueurs.get(1).getText().equals("") && (!couleursJoueurs.get(1).getSelectedItem().equals("(Aucune)")) && (!couleursJoueurs.get(0).getSelectedItem().equals("(Aucune)"))){
                    playButton.setEnabled(true);
                    nomsJoueurs.get(2).setVisible(true);
                    couleursJoueurs.get(2).setVisible(true);
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
                if ((!couleursJoueurs.get(1).isEnabled()) && couleursJoueurs.get(1).getSelectedItem().equals("(Aucune)")) {
                    couleursJoueurs.get(1).setEnabled(true);
                }
                if (nomsJoueurs.get(1).getText().equals("")) {
                    if (!couleursJoueurs.get(1).getSelectedItem().equals("(Aucune)")) {
                        couleursJoueurs.get(0).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(2).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(3).addItem(couleursJoueurs.get(0).getSelectedItem());
                    }
                    couleursJoueurs.get(1).setSelectedIndex(0);
                    couleursJoueurs.get(2).setVisible(false);
                    couleursJoueurs.get(3).setVisible(false);
                    nomsJoueurs.get(2).setVisible(false);
                    nomsJoueurs.get(3).setVisible(false);
                    nomsJoueurs.get(2).setText("");
                    nomsJoueurs.get(3).setText("");
                    playButton.setEnabled(false);

                } else if ((nomsJoueurs.get(2).getText().equals("")) && (!nomsJoueurs.get(0).getText().equals("")) && (!couleursJoueurs.get(0).getSelectedItem().equals("(Aucune)")) && (!couleursJoueurs.get(1).getSelectedItem().equals("(Aucune)"))){
                    playButton.setEnabled(true);
                    nomsJoueurs.get(2).setVisible(true);
                    couleursJoueurs.get(2).setVisible(true);
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
                if (!couleursJoueurs.get(2).isEnabled()) {
                    if (couleursJoueurs.get(2).getSelectedItem().equals("(Aucune)")) {
                        couleursJoueurs.get(2).setEnabled(true);
                    } else {

                    }

                }
                if (nomsJoueurs.get(2).getText().equals("")){
                    if (!couleursJoueurs.get(2).getSelectedItem().equals("(Aucune)")) {
                        couleursJoueurs.get(0).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(1).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(3).addItem(couleursJoueurs.get(0).getSelectedItem());
                    }
                    couleursJoueurs.get(2).setSelectedIndex(0);
                    couleursJoueurs.get(3).setVisible(false);
                    nomsJoueurs.get(3).setVisible(false);
                    nomsJoueurs.get(3).setText("");
                    playButton.setEnabled(false);
                } else if ((nomsJoueurs.get(3).getText().equals("")) && (!nomsJoueurs.get(2).getText().equals(""))){
                    playButton.setEnabled(true);
                    nomsJoueurs.get(3).setVisible(true);
                    couleursJoueurs.get(3).setVisible(true);
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
                if ((!couleursJoueurs.get(3).isEnabled()) && couleursJoueurs.get(3).getSelectedItem().equals("(Aucune)")) {
                    couleursJoueurs.get(3).setEnabled(true);
                }
                if (nomsJoueurs.get(3).getText().equals("")){
                    if (!couleursJoueurs.get(3).getSelectedItem().equals("(Aucune)")) {
                        couleursJoueurs.get(0).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(1).addItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(2).addItem(couleursJoueurs.get(0).getSelectedItem());
                    }
                    couleursJoueurs.get(3).setSelectedIndex(0);
                }
            }
        });
        
        couleursJoueurs.get(0).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(!couleursJoueurs.get(0).getSelectedItem().toString().equals("(Aucune)")) {
                        couleursJoueurs.get(1).removeItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(2).removeItem(couleursJoueurs.get(0).getSelectedItem());
                        couleursJoueurs.get(3).removeItem(couleursJoueurs.get(0).getSelectedItem());
                    }
                }
                couleursJoueurs.get(0).setEnabled(false);
                if ((!nomsJoueurs.get(0).getText().equals("")) && (!nomsJoueurs.get(1).getText().equals(""))) {
                    playButton.setEnabled(true);
                }
            }
        });

        couleursJoueurs.get(1).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(!couleursJoueurs.get(1).getSelectedItem().toString().equals("(Aucune)")) {
                        couleursJoueurs.get(0).removeItem(couleursJoueurs.get(1).getSelectedItem());
                        couleursJoueurs.get(2).removeItem(couleursJoueurs.get(1).getSelectedItem());
                        couleursJoueurs.get(3).removeItem(couleursJoueurs.get(1).getSelectedItem());
                    }
                }
                couleursJoueurs.get(1).setEnabled(false);
                if ((!nomsJoueurs.get(0).getText().equals("")) && (!nomsJoueurs.get(1).getText().equals(""))) {
                    playButton.setEnabled(true);
                }
            }
        });

        couleursJoueurs.get(2).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(!couleursJoueurs.get(2).getSelectedItem().toString().equals("(Aucune)")) {
                        couleursJoueurs.get(0).removeItem(couleursJoueurs.get(2).getSelectedItem());
                        couleursJoueurs.get(1).removeItem(couleursJoueurs.get(2).getSelectedItem());
                        couleursJoueurs.get(3).removeItem(couleursJoueurs.get(2).getSelectedItem());
                    }
                }
                couleursJoueurs.get(2).setEnabled(false);
            }
        });

        couleursJoueurs.get(3).addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(!couleursJoueurs.get(3).getSelectedItem().toString().equals("(Aucune)")) {
                        couleursJoueurs.get(0).removeItem(couleursJoueurs.get(3).getSelectedItem());
                        couleursJoueurs.get(1).removeItem(couleursJoueurs.get(3).getSelectedItem());
                        couleursJoueurs.get(2).removeItem(couleursJoueurs.get(3).getSelectedItem());
                    }
                }
                couleursJoueurs.get(3).setEnabled(false);
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
