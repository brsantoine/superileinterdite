package view;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import util.*;

public class VueTuile extends JPanel implements Observe {
    
    private Observateur observateur;    
    private int idTuile;
    
    public VueTuile() {
    
        // Creation du bouton ajouté au panel VueTuile
        JButton button = new JButton();
        this.add(button);
        
        // LISTENER    
        button.addActionListener(new ActionListener() {
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