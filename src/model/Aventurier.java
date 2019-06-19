package model;

import java.util.*;
import superileinterdite.CarteMain;
import superileinterdite.CarteTresors;
import superileinterdite.Grille;
import model.Tuile;

public abstract class Aventurier extends ObjetIdentifie{

	private ArrayList<CarteMain> sesCartes = new ArrayList();
	private Tuile saTuile;
	private String role;    //nom type de l'aventurier
        private boolean vivant;
        
        public Aventurier(String role) {
            this.role = role;
            this.setEtat(true);
        }
        
	/**
	 * 
	 * @param Grille
	 */

	public ArrayList<Tuile> TuilesAccessibles(Grille grille) {
		// TODO - implement Aventurier.deplacement
                
                //Récupération des coordonnées de l'aventurier
		int x = saTuile.getX();
                int y = saTuile.getY();
                
                
                //On parcourt les tuiles de la grille, puis, on vérifie si les tuiles sont adjacentes au joueur (fonction TuileAccessible)
                ArrayList<Tuile> tuilesAccess1 = new ArrayList<>();
                for (Tuile tuile : grille.getTuiles()) {
                    if (TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                        tuilesAccess1.add(tuile);
                    }
                }
                
                //On ajoute dans une HashMap les tuiles que l'on a récupéré, avec un Integer qui indique le nombre de coût à rétirer
//                ArrayList<Tuile> tuilesAccess = new ArrayList<>();
//                for (Tuile tuile : tuilesAccess1) {
//                    
//                    tuilesAccess.putIfAbsent(tuile);
//                }
//
                return tuilesAccess1;
	}

	/**
	 * 
	 * @param a
	 * @param ca
	 */
        
	public void donnerCarte(Aventurier a, CarteTresors ca) {
            //On change le propriétaire de sur l'objet carte, on l'ajoute à l'aventurier correspondant, et on retire cette carte à l'ancien propiriétaire
            ca.changerProprio(a.getRole());
            a.addCarte(ca);
            sesCartes.remove(ca);
	}

	/**
	 * 
	 * @param ca
	 */
        
        public void addCarte(CarteMain ca) {
            sesCartes.add(ca);
        }
        
	public void defausserCarte(CarteMain ca) {
            ca.changerProprio("defausse");
            sesCartes.remove(ca);
        }

	public String getRole() {
            return role;
        }

	public ArrayList<CarteMain> getCartes() {
            return sesCartes;
	}

        public void assecher (Tuile t) {
            //Fonction quui va changer l'état de la tuile vers "seche"
            t.assecherTuile();
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
            tuilesAssech.add(saTuile);
            return tuilesAssech;
	}

	/**
	 * 
	 * @param x de la tuile
	 * @param y de la tuile 
	 * @param x2 de la tuile a tester
	 * @param y2 de la tuile a tester
	 * @param etat de la tuile a tester
	 */
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
                        (y2 == y && (x2 == x-1 || x2 == x+1))            );        
//                        ||                        (y2 == y && x2 == x));
            } else {
                return false;
            }
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
        
        

	/**
	 * 
	 * @param tuile
	 */
	public void updateTuile(Tuile tuile) {
            saTuile = tuile;
	}

}