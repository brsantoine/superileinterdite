/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import util.*;

/**
 *
 * @author gervraua
 */
public class VueCarte extends JPanel implements Observe{
    
    private Observateur observateur;
    
    
    public VueCarte() {
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) { 
        
                if (((LineBorder) getBorder()).getLineColor() == Color.RED) {              
                    Message m = new Message(Utils.Commandes.CHOISIR_CARTE,idAventurier,4,null,0);
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
