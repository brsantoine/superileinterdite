package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import model.*;
import superileinterdite.*;
import util.*;

public class VueCarte extends JPanel implements Observe{
    
    private Observateur observateur;
    private int idAventurier;
    private int numCarte;
    private JLabel img;
    private boolean hasImage = false, isSpecial = false;
    
    public VueCarte(int idAventurier, int numCarte) {
        
        img = new JLabel();
        this.numCarte = numCarte;
        this.idAventurier = idAventurier;
        
        this.setBorder(new LineBorder(new JButton().getBackground()));

        this.addMouseListener(new MouseListener() {            
            @Override
            public void mouseClicked(MouseEvent arg0) {                  
                if (((LineBorder) getBorder()).getLineColor() == Color.RED) {
                    Message m = new Message(Utils.Commandes.CHOISIR_CARTE,idAventurier,numCarte,null,0);
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
    
    // Crée et ajoute l'image de la carte
    public void addCardImage(Carte carte) {
        hasImage = true;
        img.setIcon((new ImageIcon(new ImageIcon(Parameters.CARTES + carte.getNom() + ".png").getImage().getScaledInstance(Parameters.CARD_WIDTH,Parameters.CARD_HEIGHT, Image.SCALE_SMOOTH))));
        isSpecial = carte instanceof CarteActionSpeciale;
        this.add(img);
    }
    
    public void removeCardImage() {
        hasImage = false;
        img.setIcon(null);
        isSpecial = false;
    }

    public int getIdAventurier() {
        return idAventurier;
    }

    public boolean getHasImage() {
        return hasImage;
    }
    
    public boolean getIsSpecial() {
        return isSpecial;
    }

    public int getNumCarte() {
        return numCarte;
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