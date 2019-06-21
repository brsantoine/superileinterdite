package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.*;
import superileinterdite.*;
import util.*;
 
public class VueAventurier extends JPanel implements Observe {
    
    private Observateur observateur;
    private int idAventurier;
    private JPanel cardsPanel, namePanel, card0Panel, card1Panel, card2Panel, card3Panel, card4Panel;
    private ArrayList<VueCarte> cardsList;
    
    
    public VueAventurier(int id, String role) {
        this.setID(id);                
//        this.setSize(new Dimension(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER));
//        this.add(new JLabel(role));
        
        this.setLayout(new BorderLayout());
        cardsPanel = new JPanel();
        namePanel = new JPanel();
        cardsList = new ArrayList<>();

        String cap = role.substring(0, 1).toUpperCase() + role.substring(1);
        JLabel nameLabel = new JLabel(cap);
        nameLabel.setFont(nameLabel.getFont ().deriveFont (20.0f));

        namePanel.add(nameLabel);
        this.add(namePanel, BorderLayout.NORTH);
        this.add(cardsPanel, BorderLayout.CENTER);
        
        card0Panel = new JPanel();
        card1Panel = new JPanel();
        card2Panel = new JPanel();
        card3Panel = new JPanel();
        card4Panel = new JPanel();
        
        cardsPanel.add(card0Panel);
        cardsPanel.add(card1Panel);
        cardsPanel.add(card2Panel);
        cardsPanel.add(card3Panel);
        cardsPanel.add(card4Panel);
        
        cardsList.add(card0Panel);
        cardsList.add(card1Panel);
        cardsList.add(card2Panel);
        cardsList.add(card3Panel);
        cardsList.add(card4Panel);
        
//        this.addMouseListener(new MouseListener() {
//
//            @Override
//            public void mouseClicked(MouseEvent arg0) { 
//                System.out.println("gay nigger");
//            }
//            @Override
//            public void mousePressed(MouseEvent arg0) {}
//            @Override
//            public void mouseReleased(MouseEvent arg0) {}
//            @Override
//            public void mouseEntered(MouseEvent arg0) {}
//            @Override
//            public void mouseExited(MouseEvent arg0) {}
//            
//        });    
        
    }   
    
    public void afficherCartes(ArrayList<CarteMain> cartes) {
        // Enleve toutes les cartes affichées
        for (JPanel jPanel : cardsList) {
            jPanel.removeAll();
        }
        
        // Affiche les nouvelles cartes de l'aventurier
        int x = 0;
        System.out.println("fortnite");
        for (Carte carte : cartes) {    
            System.out.println(carte);            
            cardsList.get(x).add(new JLabel(new ImageIcon(new ImageIcon(Parameters.CARTES + carte.getNom() + ".png").getImage().getScaledInstance(90,125, Image.SCALE_SMOOTH))));
            x++;
        }
        
    }
    
    public void setID(int id) {
        this.idAventurier = id;
    }
    
    public void afficherCardsBorder() {
        for (JPanel jPanel : cardsList) {
            jPanel.setBorder(new LineBorder(Color.RED, 5)); 

        }

    }
    
    public int getID() {
        return this.idAventurier;
    }
    
    public void afficher() {
        this.setVisible(true);
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