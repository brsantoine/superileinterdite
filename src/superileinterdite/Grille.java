package superileinterdite;

import model.Tuile;
import java.util.*;

public class Grille {

	ArrayList<Tuile> sesTuiles = new ArrayList();
     
	public Grille() {
            sesTuiles = new ArrayList<>();
        }
        

	public ArrayList<Tuile> getTuiles() {
		return sesTuiles;
	}
        
        public void addTuile(Tuile tuile) {
            sesTuiles.add(tuile);
        }
        public void randomizeGrille() {
            Collections.shuffle(sesTuiles);
            //On obtient les tuiles dans la grille par un indice et on affecte x et y;
            
            
            
            // x + 1 a chaque tuile et y + 1 a chaque commentaire
            sesTuiles.get(0).setX(2);
            sesTuiles.get(0).setY(0);
            
            sesTuiles.get(1).setX(3);
            sesTuiles.get(1).setY(0);
            
            //
            sesTuiles.get(2).setX(1);            
            sesTuiles.get(2).setY(1);
            
            sesTuiles.get(3).setX(2);            
            sesTuiles.get(3).setY(1);
            
            sesTuiles.get(4).setX(3);            
            sesTuiles.get(4).setY(1);
            
            sesTuiles.get(5).setX(4);            
            sesTuiles.get(5).setY(1);
            
            //
            sesTuiles.get(6).setX(0);            
            sesTuiles.get(6).setY(2);
            
            sesTuiles.get(7).setX(1);            
            sesTuiles.get(7).setY(2);
            
            sesTuiles.get(8).setX(2);            
            sesTuiles.get(8).setY(2);
            
            sesTuiles.get(9).setX(3);            
            sesTuiles.get(9).setY(2);
            
            sesTuiles.get(10).setX(4);            
            sesTuiles.get(10).setY(2);
            
            sesTuiles.get(11).setX(5);            
            sesTuiles.get(11).setY(2);
            
            //
            sesTuiles.get(12).setX(0);            
            sesTuiles.get(12).setY(3);
            
            sesTuiles.get(13).setX(1);            
            sesTuiles.get(13).setY(3);
            
            sesTuiles.get(14).setX(2);            
            sesTuiles.get(14).setY(3);
            
            sesTuiles.get(15).setX(3);            
            sesTuiles.get(15).setY(3);
            
            sesTuiles.get(16).setX(4);            
            sesTuiles.get(16).setY(3);
            
            sesTuiles.get(17).setX(5);            
            sesTuiles.get(17).setY(3);
            
            //
            sesTuiles.get(18).setX(1);            
            sesTuiles.get(18).setY(4);
            
            sesTuiles.get(19).setX(2);            
            sesTuiles.get(19).setY(4);
            
            sesTuiles.get(20).setX(3);            
            sesTuiles.get(20).setY(4);
            
            sesTuiles.get(21).setX(4);            
            sesTuiles.get(21).setY(4);
            
            //
            sesTuiles.get(22).setX(2);
            sesTuiles.get(22).setY(5);
            
            sesTuiles.get(23).setX(3);
            sesTuiles.get(23).setY(5);
        }
}
