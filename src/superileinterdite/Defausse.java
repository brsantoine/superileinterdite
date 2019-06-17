package superileinterdite;

import model.Carte;
import java.util.*;

public class Defausse  {

	private Pile saPile;
	private ArrayList<Carte> sesCartes;
	private String nom;

        public Defausse(String nom, ArrayList<Carte> cartes) {
            this.nom = nom;
            sesCartes = new ArrayList<Carte>(cartes);
        }
        
	/**
	 * 
	 * @param ca
	 */
        
        public void assignerPile(Pile pile) {
            saPile = pile;
        }
        
        
	public void ajouterCarte(Carte ca) {
		// TODO - implement Defausse.ajouterCarte
		sesCartes.add(ca);
	}

	public ArrayList<Carte> recupererCartes() {
		// TODO - implement Defausse.recupererCartes
		ArrayList<Carte> recupCarte = new ArrayList<>(sesCartes);
                sesCartes = new ArrayList<>();
                return recupCarte;
	}

}