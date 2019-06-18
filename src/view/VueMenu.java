package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Message;
import util.*;

/**
 *
 * @author marquest
 */
public class VueMenu implements Observe {
    
    private JFrame menuWindow;
    Integer[] nbJoueurs = new Integer[]{2, 3, 4};
    private JComboBox listeNbJoueurs;
    private Observateur observateur;    

    public VueMenu() {
        
        // ---------------------------------------------------------------------
        // ---------------------------  MENU WINDOW  ---------------------------
        // ---------------------------------------------------------------------       
        menuWindow = new JFrame();
        menuWindow.setTitle("Ile Interdite");
        menuWindow.setLayout(new BorderLayout());
        menuWindow.setSize(800, 800);
        
        JPanel panelTitre = new JPanel();
        menuWindow.add(panelTitre, BorderLayout.NORTH);
        JLabel labelTitre = new JLabel("Ile Interdite");
        panelTitre.add(labelTitre);
        labelTitre.setFont(labelTitre.getFont ().deriveFont (64.0f));
        
        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new GridLayout(6, 3));
        menuWindow.add(panelBoutons, BorderLayout.CENTER);
        
        JButton buttonJouer = new JButton("Jouer");
        JButton buttonx = new JButton("???");
        JButton buttony = new JButton("???");
        JButton buttonQuitter = new JButton("Quitter");
        
        for (int i = 0; i < 17; i++) {
            if (i == 4) {
                panelBoutons.add(buttonJouer);
            } else if (i == 5) {
                JPanel panelNbJoueurs = new JPanel();
                panelNbJoueurs.setLayout(new GridLayout(4, 1));
                
                JLabel labelNbJoueurs = new JLabel("Nombre de joueurs");

                listeNbJoueurs = new JComboBox(nbJoueurs);
                
                panelNbJoueurs.add(new JPanel());
                panelNbJoueurs.add(labelNbJoueurs);
                panelNbJoueurs.add(listeNbJoueurs);
                panelNbJoueurs.add(new JPanel());

                panelBoutons.add(panelNbJoueurs);
            } else if (i == 7) {
                panelBoutons.add(buttonx);
            } else if (i == 10) {
                panelBoutons.add(buttony);
            } else if (i == 13) {
                panelBoutons.add(buttonQuitter);
            } else {
                JPanel panelVide = new JPanel();
                panelBoutons.add(panelVide);
            }
        }
        
        
        
        // Jouer: change les fenêtres lorsque l'on appuie sur le bouton Jouer     
        buttonJouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//               menuWindow.setVisible(false);
//               gameWindow.setVisible(true);
               Message m = new Message(Utils.Commandes.COMMENCER_JEU,listeNbJoueurs.getSelectedIndex()+2,0,null,0);
               notifierObservateur(m);
//               afficherJeu();
           } 
        });
        
        // Quitter
        buttonQuitter.addActionListener(new ActionListener() {
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
