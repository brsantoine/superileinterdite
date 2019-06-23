package model;

import java.util.*;
import superileinterdite.CarteMain;
import superileinterdite.CarteTresors;
import superileinterdite.Grille;
import model.Tuile;

public abstract class Aventurier extends ObjetIdentifie{

    private ArrayList<CarteMain> sesCartes = new ArrayList();
    private Tuile saTuile;
    private String role, couleur;
    private boolean vivant;

    public Aventurier(String role) {
        this.role = role;
        this.setEtat(true);
    }

    public ArrayList<Tuile> TuilesAccessibles(Grille grille) {
        int x = saTuile.getX();
        int y = saTuile.getY();

        //On parcourt les tuiles de la grille, puis, on vérifie si les tuiles sont adjacentes au joueur (fonction TuileAccessible)
        ArrayList<Tuile> tuilesAccess1 = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAccess1.add(tuile);
            }
        }
        return tuilesAccess1;
    }
    
    public ArrayList<Tuile> TuilesAssechables(Grille grille) {
        int x = saTuile.getX();
        int y = saTuile.getY();

        //Même méthode que pour les tuilesAccessibles
        ArrayList<Tuile> tuilesAssech = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (TuileAssechable(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAssech.add(tuile);
            }
        }
        return tuilesAssech;
    }

    public boolean TuileAccessible(int x, int y, int x2, int y2, String etat) {
        //Si la tuile est adjacente et non coulé, renvoie vrai, faux sinon
        if (!etat.equals("coulé")) {
            return ((x2 == x && (y2 == y-1 || y2 == y+1)) ||
                    (y2 == y && (x2 == x-1 || x2 == x+1)));          
        } else {
            return false;
        }
    }

    public boolean TuileAssechable(int x, int y, int x2, int y2, String etat) {
        //Si la tuile est adjacente et inondé, renvoie vrai, faux sinon
        if (etat.equals("inondé")) {
            return ((x2 == x && (y2 == y || y2 == y-1 || y2 == y+1)) ||
                    (y2 == y && (x2 == x-1 || x2 == x+1)) ||  (y2 == y && x2 == x));
        } else {
            return false;
        }
    }
    
    public void assecher (Tuile t) {
        //Fonction quui va changer l'état de la tuile vers "seche"
        t.assecherTuile();
    }
    
    public void donnerCarte(Aventurier a, CarteTresors ca) {
        //On change le propriétaire de sur l'objet carte, on l'ajoute à l'aventurier correspondant, et on retire cette carte à l'ancien propiriétaire
        a.addCarte(ca);
        sesCartes.remove(ca);
    }

    public void addCarte(CarteMain ca) {
        sesCartes.add(ca);
    }
    
    public ArrayList<CarteMain> getCartes() {
        return sesCartes;
    }

    public void defausserCarte(CarteMain ca) {
        sesCartes.remove(ca);
    }

    public String getRole() {
        return role;
    }







    public Tuile getTuile() {
        return saTuile;
    }

    public boolean getEtat() {
        return vivant;
    }

    public void setEtat(boolean etat) {
        this.vivant = etat;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setTuile(Tuile tuile) {
        saTuile = tuile;
    }

}