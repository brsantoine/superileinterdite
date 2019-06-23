package model;

import model.Aventurier;
import java.util.*;

public class Tuile  {

    ArrayList<Aventurier> sesAventuriers = new ArrayList();
    private String nom;
    private int x;
    private int y;
    private int idTuile;
    private String etat; // seche, coulé, inondé

    public Tuile(String nom) {
        setNom(nom);
        // La tuile est initialisé a l'état seche
        this.etat = "seche";
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void setID(int id) {
        this.idTuile = id;
    }   

    public int getID() {
        return this.idTuile;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return this.etat;
    }

    public void removeAventurier(Aventurier av) {
        // Enlever l'aventurier donné en paramètre
        sesAventuriers.remove(av);
    }

    public void assecherTuile() {
        this.etat = "seche";	
    }

    public void inonderTuile() {
        // Inonde la tuile, si l'état est déjà inondé la tuile devient coulé
        if (this.etat.equalsIgnoreCase("inondé")) {
            this.etat = "coulé";
        } else if(this.etat.equalsIgnoreCase("seche")){
            this.etat = "inondé";
        }        
    }

    public void addAventurier(Aventurier aventurier) {
        // Ajoute un aventurier a la tuile
        this.sesAventuriers.add(aventurier);
    }

    public ArrayList<Aventurier> getSesAventuriers() {
        return sesAventuriers;
    }
        
}
