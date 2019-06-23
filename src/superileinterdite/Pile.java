package superileinterdite;

import model.Carte;
import java.util.*;

public class Pile {

    private ArrayList<Carte> sesCartes;
    private String nom;

    public Pile(String nom) {
        this.nom = nom;
        sesCartes = new ArrayList<Carte>();
    }

    public void addPile(Carte carte) {
        sesCartes.add(carte);
    }
    public void randomizePile() {
        Collections.shuffle(sesCartes);
    }

    public ArrayList<Carte> getSesCartes() {
        return sesCartes;
    }

    public String getNom() {
        return nom;
    }

    public void RemoveCarte(Carte carte) {
        sesCartes.remove(carte);
    }

    public void ViderPile() {
        getSesCartes().removeAll(getSesCartes());
    }

    public void setSesCartes(ArrayList<Carte> sesCartes) {
        for (Carte c : sesCartes) {
            this.sesCartes.add(c);          
        }
    }

}