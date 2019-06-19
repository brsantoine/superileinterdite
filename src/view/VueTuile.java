package view;
 
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import javax.swing.*;

import util.*;

public class VueTuile extends JButton implements Observe {
    
    private Observateur observateur;    
    private int idTuile;
    
    public VueTuile() {   
        
        // LISTENER    
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(Utils.Commandes.CHOISIR_TUILE,0,0,null,idTuile);
                notifierObservateur(m);
            }
        });       
    }
    
    public void setID(int id) {
        this.idTuile = id;
    }
    
    public int getID() {
        return this.idTuile;
    }
    
    public void setText(String nom){
        JLabel nomLabel = new JLabel(nom);
        this.add(nomLabel);
    }
    
    public void setImage(String nomTuile) {
        this.setIcon(new ImageIcon(new ImageIcon(Parameters.TUILES + nomTuile + ".png").getImage().getScaledInstance(150,150, Image.SCALE_SMOOTH)));
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