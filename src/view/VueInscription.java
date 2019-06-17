package view;

import util.Message;
import util.*;

/**
 *
 * @author IUT2-Dept Info
 */
public class VueInscription implements Observe {
    private Observateur observateur;
    
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
