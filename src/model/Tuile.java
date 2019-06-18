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

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
        
        public void setID(int id) {
            this.idTuile = id;
        }   
    
        public int getID() {
            return this.idTuile;
        }
        
	public void removeAventurier(Aventurier av) {
	    // enlever l'aventurier donné en paramètre
            sesAventuriers.remove(av);
	}

	public void assecherTuile() {
                this.etat = "seche";	
	}

	public void inonderTuile() {
	    // Inonde la tuile, si l'etat est déjà inondé la tuile devient coulé
            if (this.etat.equalsIgnoreCase("inondé")) {
                this.etat = "coulé";
            } else {
                this.etat = "inondé";
            }        
	}

        public String getNom() {
            return this.nom;
        }
	public String getEtat() {
		return this.etat;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
        
        public void addAventurier(Aventurier aventurier) {
	    // Ajoute un aventurier a la tuile
            this.sesAventuriers.add(aventurier);
        }

}
