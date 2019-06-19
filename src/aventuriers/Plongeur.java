package aventuriers;

import java.util.ArrayList;
import java.util.HashMap;
import model.Aventurier;
import superileinterdite.Grille;
import model.Tuile;

public class Plongeur extends Aventurier {
    
    public Plongeur () {
        super("plongeur");
    }
    
    public ArrayList<Tuile> TuilesAccessibles(Grille grille) {
        int x = this.getTuile().getX();
        int y = this.getTuile().getY();

        //Première boucle qui récupère uniquement les tuiles sèches
        ArrayList<Tuile> tuilesAccess = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (this.TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAccess.add(tuile);
            }
        }
      
        return tuilesAccess;
            
    }
               
    public boolean TuileAccessible(int x, int y, int x2, int y2, String etat) {
        // Renvoie vrai si la tuile est adjacente
        return((x2 == x && ((y2 == y-1) || (y2 == y+1))) || // x reste pareil, y + ou - 1
                y2 == y && ((x2 == x-1) || (x2 == x+1)));   // y reste pareil, x + ou - 1              

    }
    
}