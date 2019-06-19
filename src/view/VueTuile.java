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
//    private JLayeredPane layeredPane;
    
    public VueTuile() {   
        
        this.inondePanel = new JPanel();        
        this.sechePanel = new JPanel();        
        this.pionPanel = new JPanel();   
//        this.layeredPane = new JLayeredPane();
        this.setMaximumSize(new Dimension(300, 300));
//        layeredPane.setBounds(0, 0, 100, 100);
        this.add(sechePanel);
//        panelBlue.setBackground(Color.BLUE);
//        panelBlue.setBounds(0, 0, 600, 400);
//        panelBlue.setOpaque(true);
//        panelGreen.setBackground(Color.GREEN);
//        panelGreen.setBounds(200, 100, 100, 100);
//        panelGreen.setOpaque(true);
//        lpane.add(panelBlue, new Integer(0), 0);
//        lpane.add(panelGreen, new Integer(1), 0);
//        this.pack();
        
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

    public void setPionPanel(JPanel pionPanel) {
        
//        pionPanel.add(new JLabel(new ImageIcon(new ImageIcon(Parameters.TUILES + nomTuile + ".png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH))));
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