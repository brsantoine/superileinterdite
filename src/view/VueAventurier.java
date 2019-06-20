package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import model.*;
import util.*;
 
public class VueAventurier extends JPanel implements Observe {
    
    private Observateur observateur;
    private int idAventurier;
    private JPanel cardsPanel;
    private JButton helicoButton;
    
    public VueAventurier(int id, String role) {
        this.setID(id);                
//        this.setSize(new Dimension(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER));
//        this.add(new JLabel(role));
        
        this.setLayout(new BorderLayout());
        cardsPanel = new JPanel();

        String cap = role.substring(0, 1).toUpperCase() + role.substring(1);
        JLabel yeetLabel = new JLabel(cap + " (nom user)");
        yeetLabel.setFont(yeetLabel.getFont ().deriveFont (20.0f));

        this.add(yeetLabel, BorderLayout.NORTH);
        this.add(cardsPanel, BorderLayout.CENTER);
        
        cardsPanel.setLayout(new GridLayout(1,5));
        for (int i = 0; i < 5; i++) {            
            cardsPanel.add(new JButton("testtest"));        
        }
        
        if (role == "Pilote") {
            helicoButton = new JButton();
            helicoButton.setText("Helico");
            helicoButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    desactiverHelico();
                    Message m = new Message(Utils.Commandes.HELICO, 0, 0, null, 0);
                    notifierObservateur(m);
                }
            });
        }
    }
    
    public void afficherCartes(ArrayList<Carte> ca) {
        // TODO - implement VueAventurier.afficherCartes
        for (Carte carte : ca) {

        }
    }
    
    public void setID(int id) {
        this.idAventurier = id;
    }
    
    public int getID() {
        return this.idAventurier;
    }
    
    public void afficher() {
        this.setVisible(true);
    }
    
    public void activerHelico() {
        helicoButton.setEnabled(true);
    }
    
    public void desactiverHelico() {
        helicoButton.setEnabled(false);
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