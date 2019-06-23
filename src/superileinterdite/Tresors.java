package superileinterdite;

import java.util.*;

public class Tresors {

    ArrayList<TuileTresor> sesTuiles = new ArrayList();
    private String nom; 
    private boolean etat; // true si obtenu

    public Tresors(String nom) {
        this.nom = nom;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Boolean getEtat() {
        return etat;
    }

    public ArrayList<TuileTresor> getSesTuiles() {
        return sesTuiles;
    }

    public void addTuile(TuileTresor tuileT) {
        sesTuiles.add(tuileT);
    }

    public String getNom() {
        return nom;
    }

    public boolean isEtat() {
        return etat;
    }
        
}
