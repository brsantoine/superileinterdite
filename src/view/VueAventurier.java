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
    private JPanel cardsPanel, namePanel;
    private ArrayList<VueCarte> cardsList;
    
    
    public VueAventurier(int id, String role) {
        this.setID(id);                
//        this.setSize(new Dimension(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER));
//        this.add(new JLabel(role));
        

        this.setLayout(new BorderLayout());
        
        cardsPanel = new JPanel();
        namePanel = new JPanel();
        cardsList = new ArrayList<>();

        // Rajouter une majuscule au role, et changer la taille de la police
        String cap = role.substring(0, 1).toUpperCase() + role.substring(1);
        JLabel nameLabel = new JLabel(cap);
        nameLabel.setFont(nameLabel.getFont ().deriveFont (20.0f));

        namePanel.add(nameLabel);
        this.add(namePanel, BorderLayout.NORTH);
        this.add(cardsPanel, BorderLayout.CENTER);

        for (int i = 0; i < 5; i++) {
            VueCarte vueC = new VueCarte(id, i);
            cardsList.add(vueC);
        }     
        
        for (VueCarte vueCarte : cardsList) {
            cardsPanel.add(vueCarte);            
        }
        
        this.setBorder(new LineBorder(new JButton().getBackground()));
        
        
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) { 
                if (((LineBorder) getBorder()).getLineColor() == Color.RED) {
                    Message m = new Message(Utils.Commandes.CHOISIR_JOUEUR,idAventurier,0,null,0);
                    notifierObservateur(m);
                }         
            }
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {}
            
        });    
        
    }   
    public void afficherCartes(ArrayList<CarteMain> cartes) {
        // Enleve toutes les cartes affichées
        for (VueCarte vC : cardsList) {
            vC.removeCardImage();
        }
        
        // Affiche les nouvelles cartes de l'aventurier
        int x = 0;
        for (Carte carte : cartes) {    
            System.out.println(carte);    
            cardsList.get(x).addCardImage(carte);
            x++;
        }
    }
    
    public void defausseLastCard() {
        cardsList.get(cardsList.size()-1).removeCardImage();
    }
    
    public void setID(int id) {
        this.idAventurier = id;
    }
    
    // Ajoute une bordure rouge aux cartes choisissables (si elles ont une image)
    public void afficherCardsBorder() {
        for (VueCarte vC : cardsList) {
            if (vC.getHasImage()) {
                vC.addCardBorder();
            }
        }
    }
    
    // Ajoute une bordure rouge aux cartes choisissables (si elles ont une image)
    public void cacherCardsBorder() {
        for (VueCarte vC : cardsList) {
            vC.removeCardBorder();
        }
    }
    
    public void cacherCardsBorder(int numCarte) {
        for (VueCarte vC : cardsList) {
            System.out.println(vC.getNumCarte());
            if (vC.getNumCarte() != numCarte) {
                vC.removeCardBorder();
            } else {
//                vC.setSelectedCard();
            }
        }
        Message m = new Message(Utils.Commandes.CHOISIR_JOUEUR,0,numCarte,null,0);
        notifierObservateur(m);

        

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
        for (VueCarte vC : cardsList) {
            vC.addObservateur(o);            
        }
    }
    
    @Override
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
    
   
}