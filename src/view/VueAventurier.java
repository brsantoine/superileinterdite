package view;

import java.util.*;
import model.Carte;
import util.*;
 
public class VueAventurier implements Observe {
    
    private Observateur observateur;
    private int idAventurier;
    
    public VueAventurier(int id) {
        this.setID(id);
    }
    
    public void afficherCartes(ArrayList<Carte> ca) {
        // TODO - implement VueAventurier.afficherCartes
        for (Carte carte : ca) {

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
    
    public void setID(int id) {
        this.idAventurier = id;
    }
    
    public int getID() {
        return this.idAventurier;
    }
}