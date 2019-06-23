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
    private int idAventurier, cardToGive;
    private JPanel cardsPanel, namePanel;
    private ArrayList<VueCarte> cardsList;
    
    
    public VueAventurier(int id, String role) {
        this.setID(id);                
//        this.setSize(new Dimension(Parameters.LARGEUR_VUE_AVENTURIER, Parameters.HAUTEUR_VUE_AVENTURIER));
//        this.add(new JLabel(role));
        

        this.setLayout(new BorderLayout(0,Parameters.CARD_VERTICAL_SPACE));
        
        cardsPanel = new JPanel(new GridLayout(1,5,Parameters.CARD_HORIZONTAL_SPACE,0));
        namePanel = new JPanel();
        cardsList = new ArrayList<>();

        // Rajouter une majuscule au role, et changer la taille de la police
        String cap = role.substring(0, 1).toUpperCase() + role.substring(1);
        JLabel nameLabel = new JLabel(cap);
        nameLabel.setFont(nameLabel.getFont ().deriveFont (20.0f));

        namePanel.add(nameLabel);
        this.add(namePanel, BorderLayout.NORTH);
        this.add(cardsPanel, BorderLayout.CENTER);

        // id: id de l'aventurier qui a la carte, i: position de la carte
        for (int i = 0; i < 5; i++) {
            VueCarte vueC = new VueCarte(id, i);
            cardsList.add(vueC);
        }
        
        for (VueCarte vueCarte : cardsList) {
            cardsPanel.add(vueCarte);            
        }
        
        this.setBorder(new LineBorder(new JButton().getBackground()));
        
        namePanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) { 
                if (namePanel.getBackground() == Parameters.COULEUR_JOUEUR_SELECTIONNABLE) {
                    Message m = new Message(Utils.Commandes.CHOISIR_JOUEUR,idAventurier,cardToGive,null,0);
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
                vC.setBorder(new LineBorder(Color.RED, 3)); 
            }
        }
    }
    
    public void afficherCardsActionSpecialeBorder() {
        for (VueCarte vC : cardsList) {
            if (vC.getIsSpecial()){
                vC.setBorder(new LineBorder(Color.RED, 3));
            }
        }
    }
    
    // Enlève les bordures de toutes les cartes
    public void cacherCardsBorder() {
        for (VueCarte vC : cardsList) {
            vC.setBorder(new LineBorder(new JButton().getBackground()));
        }
    }
    
    // Enlève les bordures de toutes les cartes sauf celle choisie (numCarte)
    public void cacherCardsBorder(int numCarte, int idAventurier) {
        for (VueCarte vC : cardsList) {
            if (vC.getNumCarte() != numCarte) {
                vC.setBorder(new LineBorder(new JButton().getBackground()));
            } else if (idAventurier == vC.getIdAventurier()){
                vC.setBorder(new LineBorder(Color.BLUE, 3)); 
            }
        }

    }
    
    public void setNameBorder(String color) {
        if (color.equals("Vert")) {
            namePanel.setBorder(new LineBorder(Color.GREEN, 5));
        } else if (color.equals("Violet")) {
            namePanel.setBorder(new LineBorder(new Color(255, 0,255), 5));
        } else if (color.equals("Jaune")) {
            namePanel.setBorder(new LineBorder(Color.YELLOW, 5));
        } else if (color.equals("Noir")) {
            namePanel.setBorder(new LineBorder(Color.BLACK, 5));
        } else if (color.equals("Gris")) {
            namePanel.setBorder(new LineBorder(Color.GRAY, 5));
        } else if (color.equals("Bronze")) {
            namePanel.setBorder(new LineBorder(new Color(176, 141, 87), 5));
        } else if (color.equals("Bleu")) {
            namePanel.setBorder(new LineBorder(Color.BLUE, 5));
        } else if (color.equals("Rouge")) {
            namePanel.setBorder(new LineBorder(Color.RED, 5));
        }
    }
    
    public void setNameBackground(Color color) {
        this.namePanel.setBackground(color);
    }

    public JPanel getNamePanel() {
        return namePanel;
    }
    
    public void setCardToGive(int numCarte) {
        this.cardToGive = numCarte;
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