package superileinterdite;

import model.Carte;
import model.Aventurier;
import java.util.*;

public class Pile {

	private ArrayList<Carte> sesCartes = new ArrayList();
	//private Defausse saDefausse;
	private String nom;
        
        public Pile(String nom, ArrayList<Carte> cartes) {
            this.nom = nom;
            sesCartes = new ArrayList<Carte>(cartes);
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

	public boolean pileVide() {
		// TODO - implement Pile.pileVide
		if (sesCartes.isEmpty()) {
                    return true;
                } else {
                    return false;
                }
	}

	/**
	 * 
	 * @param cartes
	 */
	public void remplirPile(ArrayList<Carte> cartes) {
		// TODO - implement Pile.remplirPile
		sesCartes = cartes;
	}

}