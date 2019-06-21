/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import model.*;
import util.*;

/**
 *
 * @author gervraua
 */
public class VueCarte extends JPanel implements Observe{
    
    private Observateur observateur;
    private int idAventurier;
    private int numCarte;
    private JLabel img;
    private boolean hasImage = false;
    
    public VueCarte(int idAventurier, int numCarte) {
        
        img = new JLabel();
        this.numCarte = numCarte;
        this.idAventurier = idAventurier;
        
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
    
    
    public void addCardImage(Carte carte) {
        hasImage = true;
        img.setIcon((new ImageIcon(new ImageIcon(Parameters.CARTES + carte.getNom() + ".png").getImage().getScaledInstance(80,110, Image.SCALE_SMOOTH))));
        this.add(img);
    }
    
    public void removeCardImage() {
        hasImage = false;
        img.setIcon(null);
    }
    
//    public void setSelectedCard() {
//        this.setBorder(new LineBorder(Color.BLUE, 5));
//    }
    
    public void addCardBorder() {
        this.setBorder(new LineBorder(Color.RED, 5)); 
    }
    
    public void removeCardBorder() {        
        this.setBorder(new LineBorder(new JButton().getBackground()));
    }

    public boolean getHasImage() {
        return hasImage;
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
