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
    
    public HashMap<Tuile, Integer> TuilesAccessibles(Grille grille) {
        // TODO - implement Aventurier.deplacement
        int x = this.getTuile().getX();
        int y = this.getTuile().getY();

        
        //Comme pour les tuilesAccessibles, mais deux méthodes différentes en fonction de l'état de la tuile
        
        //Première boucle qui récupère uniquement les tuiles sèches
        ArrayList<Tuile> tuilesAccess1 = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (this.TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAccess1.add(tuile);
            }
        }
  
        
        //Ajout des tuiles sèches dans le vecteur final, avec un cout = 1
        HashMap<Tuile, Integer> tuilesAccess = new HashMap<>();
        for (Tuile tuile : tuilesAccess1) {
            tuilesAccess.put(tuile, 1);
        }
        
        
        //Deuxième boucle qui récupère les tuiles inondées
        ArrayList<Tuile> tuilesAccess2 = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (this.TuileSpecialAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAccess2.add(tuile);
            }
        }
        
        //Ajout des tuiles inondées qui ont pour coût 0
        for (Tuile tuile : tuilesAccess2) {
            tuilesAccess.put(tuile, 0);
        }
        
        return tuilesAccess;
            
    }
               
    public boolean TuileAccessible(int x, int y, int x2, int y2, String etat) {
        //Renvoie vrai si la tuile est sèche et adjacente
        if (etat.equals("seche")) {
            return((x2 == x && ((y2 == y-1) || (y2 == y+1))) || // x reste pareil, y + ou - 1
                    y2 == y && ((x2 == x-1) || (x2 == x+1)));   // y reste pareil, x + ou - 1              
        } else {
            return false;
        }
    }
    
    public boolean TuileSpecialAccessible(int x, int y, int x2, int y2, String etat) {
        //Renvoie vrai si la tuile est coulé ou inondé et adjacente
        if (etat.equals("coulé") || etat.equals("inondé")) {
            return((x2 == x && ((y2 == y-1) || (y2 == y+1))) || // x reste pareil, y + ou - 1
                    y2 == y && ((x2 == x-1) || (x2 == x+1)));   // y reste pareil, x + ou - 1              
        } else {
            return false;
        }
    }
}