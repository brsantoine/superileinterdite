package view;
 
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.*;
import util.*;

public class VueTuile extends JPanel implements Observe {
    
    private Observateur observateur;    
    private int idTuile,numAv = -1;
    private JPanel inondePanel, sechePanel, pionPanel;
    private ArrayList<JLabel> pionsImages;
    private JLayeredPane layeredPane;
    
    public VueTuile() {   
        
        this.setLayout(new BorderLayout());
        pionsImages = new ArrayList<>();
        
        this.inondePanel = new JPanel();        
        this.sechePanel = new JPanel();
        this.pionPanel = new JPanel(new GridLayout(2,2));
        inondePanel.setOpaque(false);
        sechePanel.setOpaque(false);
        pionPanel.setOpaque(false);
        this.layeredPane = new JLayeredPane();
        sechePanel.setBounds(0, 0, Parameters.TUILE_BOUNDS, Parameters.TUILE_BOUNDS);
        inondePanel.setBounds(0, 0, Parameters.TUILE_BOUNDS, Parameters.TUILE_BOUNDS);        
        pionPanel.setBounds(Parameters.PION_HORIZONTAL_SPACE, Parameters.PION_VERTICAL_SPACE, Parameters.PION_BOUNDS, Parameters.PION_BOUNDS);
        
        layeredPane.add(sechePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(inondePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(pionPanel, JLayeredPane.PALETTE_LAYER );
        
        this.add(layeredPane);
        this.setVisible(true);
        
        // LISTENER    
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (((LineBorder) getBorder()).getLineColor() == Color.GREEN || ((LineBorder) getBorder()).getLineColor() == Color.BLUE) {                
                    Message m = new Message(Utils.Commandes.CHOISIR_TUILE,numAv,0,null,idTuile);
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
    
    public void setInondePanel(String nomTuile) {
//        this.setLayout(new BorderLayout());
//        frame.add(lpane, BorderLayout.CENTER);

        inondePanel.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.TUILES + nomTuile + ".png").getImage().getScaledInstance(Parameters.TUILE_SIZE,Parameters.TUILE_SIZE, Image.SCALE_SMOOTH))));
    }

    public void setSechePanel(String nomTuile){
//        this.setLayout(new BorderLayout());
//        frame.add(lpane, BorderLayout.CENTER);
        sechePanel.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.TUILES + nomTuile + ".png").getImage().getScaledInstance(Parameters.TUILE_SIZE,Parameters.TUILE_SIZE, Image.SCALE_SMOOTH))));
    }

    public void setPionPanel(ArrayList<Aventurier> aventuriers) {
        for (int i = 0; i < aventuriers.size(); i++) {
            pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"pion" + aventuriers.get(i).getCouleur() + ".png").getImage().getScaledInstance(Parameters.PION_SIZE,Parameters.PION_SIZE, Image.SCALE_SMOOTH))));
//            pionsImages.get(i).setHorizontalAlignment(30*i);
//            pionsImages.get(i).setVerticalAlignment(30*i);
            pionsImages.get(i).setVisible(false);
            pionPanel.add(pionsImages.get(i));
        }
        
    }
    
    public void afficherInonde() {
        inondePanel.setVisible(true);
        sechePanel.setVisible(false);
    }
    
    public void afficherSeche() {
        sechePanel.setVisible(true);
        inondePanel.setVisible(false);
    }
    
    public void afficherCoule() {
        sechePanel.setVisible(false);
        inondePanel.setVisible(false);
    }
    
    public void afficherPion(int numLabel) {
        pionsImages.get(numLabel).setVisible(true);
    }

    public void cacherPion() {
        for (JLabel pionsImage : pionsImages) {
            pionsImage.setVisible(false);
        }
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

    public void setNumAv(int numAv) {
        this.numAv = numAv;
    }
    
    
}