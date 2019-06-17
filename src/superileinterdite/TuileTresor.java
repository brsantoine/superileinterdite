package superileinterdite;

import model.Tuile;

public class TuileTresor extends Tuile {

	Tresors sonTresor;

        public TuileTresor (String nom, Tresors tresor) {
            super(nom);
	    // Initialise la tuile avec son trésor
            this.sonTresor = tresor;
        }
        
        public Tresors getTresor(){
            return this.sonTresor;
        }
}
