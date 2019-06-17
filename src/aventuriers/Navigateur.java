package aventuriers;

import java.util.ArrayList;
import model.Aventurier;
import superileinterdite.Grille;
import model.Tuile;

public class Navigateur extends Aventurier {
    
    public Navigateur () {
        super("navigateur");
    }
    
    public ArrayList<Tuile> deplacerAutreJoueur(Aventurier a, Grille g) {
        int x = a.getTuile().getX();
        int y = a.getTuile().getY();
        
        //Comme pour les tuilesAccessibles, mais avec les coordonnées de l'aventurier qu'il souhaite déplacer
        // Fonction non testée pour le moment
        ArrayList<Tuile> tuilesAccess1 = new ArrayList<>();
        for (Tuile tuile : g.getTuiles()) {
            if (TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAccess1.add(tuile);
            }
        }

        ArrayList<Tuile> tuilesAccess2 = new ArrayList<>();
        tuilesAccess2 = tuilesAccess1;
        for (Tuile tuile : tuilesAccess1) {
            if (TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                if (!tuilesAccess2.contains(tuile)) {
                    tuilesAccess2.add(tuile);
                }
            }
        }
        
        return tuilesAccess2;
    }
}