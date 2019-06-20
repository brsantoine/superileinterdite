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
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VueTuile extends JPanel implements Observe {
    
    private Observateur observateur;    
    private int idTuile;
    private JPanel inondePanel, sechePanel, pionPanel;
    private ArrayList<JLabel> pionsImages;
    private JLayeredPane layeredPane;
    
    public VueTuile() {   
        
        this.setLayout(new BorderLayout());
        pionsImages = new ArrayList<>();
        
        this.inondePanel = new JPanel();
        this.sechePanel = new JPanel();
        this.pionPanel = new JPanel();
        
        this.layeredPane = new JLayeredPane();
        sechePanel.setBounds(0, 0, 100, 100);
        inondePanel.setBounds(0, 0, 100, 100);
        pionPanel.setBounds(0,0,100,100);
        layeredPane.add(sechePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(inondePanel, JLayeredPane.DEFAULT_LAYER);
//        layeredPane.add(pionPanel, JLayeredPane.PALETTE_LAYER );
        
        this.add(layeredPane);
        this.setVisible(true);
        

        
        // LISTENER    
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (((LineBorder) getBorder()).getLineColor() == Color.GREEN || ((LineBorder) getBorder()).getLineColor() == Color.BLUE) {                
                    Message m = new Message(Utils.Commandes.CHOISIR_TUILE,0,0,null,idTuile);
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

        inondePanel.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.TUILES + nomTuile + ".png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
    }

    public void setSechePanel(String nomTuile){
//        this.setLayout(new BorderLayout());
//        frame.add(lpane, BorderLayout.CENTER);
        sechePanel.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.TUILES + nomTuile + ".png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
    }

    public void setPionPanel() {    
        
        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Vert.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
//        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Bleu.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
//        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Violet.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
//        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Jaune.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
//        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Rouge.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
//        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Noir.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
//        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Bronze.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
//        pionsImages.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.PIONS +"Gris.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
        pionPanel.add(pionsImages.get(0));
    }
    
    public void afficherInonde() {
        inondePanel.setVisible(true);
        sechePanel.setVisible(false);
    }
    
    public void afficherSeche() {
        sechePanel.setVisible(true);
        inondePanel.setVisible(false);
    }
    
    public void afficherPion(String color) {
        
        pionPanel.setVisible(true);
    }
    
    public void cacherPion() {
        pionPanel.setVisible(false);
        
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