package aventuriers;

import java.util.ArrayList;
import java.util.Iterator;
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
        
        // Comme pour les tuilesAccessibles, mais avec les coordonnées de l'aventurier qu'il souhaite déplacer
        // Fonction non testée pour le moment
        ArrayList<Tuile> tuilesAccess1 = new ArrayList<Tuile>();
        for (Tuile tuile : g.getTuiles()) {
            if (TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAccess1.add(tuile);
            }
        }

        ArrayList<Tuile> tuilesAccess2 = new ArrayList<Tuile>();
        for (Tuile tuile2 : g.getTuiles()) {
            for (Tuile tuile : tuilesAccess1) {
                if (TuileAccessible(tuile.getX(), tuile.getY(), tuile2.getX(), tuile2.getY(), tuile2.getEtat())) {
                    tuilesAccess2.add(tuile2);                 
                }
            }
        }
        tuilesAccess2.addAll(tuilesAccess1);
        tuilesAccess2.remove(a.getTuile());
        
        return tuilesAccess2;
    }
}