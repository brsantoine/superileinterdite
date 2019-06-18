package superileinterdite;

import model.Carte;
import model.Aventurier;
import java.util.*;

public class Pile {

	private ArrayList<Carte> sesCartes;
	//private Defausse saDefausse;
	private String nom;
        
        public Pile(String nom) {
            this.nom = nom;
            sesCartes = new ArrayList<Carte>();
        }

	/**
	 * 
	 * @param a
	 */
        
//        public void assignerPile(Defausse defausse) {
//            saDefausse = defausse;
//        }
        
        /* Soit on utilise le return Carte, et dans ce cas c'est le controleur qui donne la carte à l'aventurier, soit on utilise l'aventurier qui est en paramètre et dans ce cas cette méthode va directement à aventurier
        */
        
	public Carte piocherCarte(Aventurier a) {
            Random generate = new Random();
            int randomInt = generate.nextInt(sesCartes.size());
            return sesCartes.get(randomInt);
	}


	public void addPile(Carte carte) {
            // TODO - implement Pile.remplirPile
            sesCartes.add(carte);
	}
	public void randomizePile() {
            // TODO - implement Pile.remplirPile
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
            this.sesCartes = sesCartes;
        }
         
        

}