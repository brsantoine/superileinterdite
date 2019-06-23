package aventuriers;

import model.Aventurier;
import java.util.ArrayList;
import java.util.HashMap;
import superileinterdite.Grille;
import model.Tuile;

public class Explorateur extends Aventurier {
    
    public Explorateur() {
        super("explorateur");
    }
    
    //Fonction qui ne change pas beaucoup de l'aventurier de base, sauf la méthode tuileAccessible
    @Override
    public ArrayList<Tuile> TuilesAccessibles(Grille grille) {
        int x = this.getTuile().getX();
        int y = this.getTuile().getY();

        ArrayList<Tuile> tuilesAccess1 = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAccess1.add(tuile);
            }
        }
        return tuilesAccess1;
    }
    
    //Fonction qui ne change pas beaucoup de l'aventurier de base, sauf la méthode tuileAssechable
    public ArrayList<Tuile> TuilesAssechables(Grille grille) {
        int x = this.getTuile().getX();
        int y = this.getTuile().getY();

        ArrayList<Tuile> tuilesAssech = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (TuileAssechable(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                tuilesAssech.add(tuile);
            }
        }
        return tuilesAssech;
    }
    
    // x,y de l'aventurier et x2,y2 de la tuile
    @Override
    public boolean TuileAccessible(int x, int y, int x2, int y2, String etat) {
       //Rappelle de la fonction qui renvoie les tuiles adjacentes non coulées, + les tuile en diagonales
        if (!etat.equals("coulé")) {
            return (super.TuileAccessible(x, y, x2, y2, etat)) || ((y2 == y+1 && (x2 == x-1 || x2 == x+1)) || (y2 == y-1 && (x2 == x-1 || x2 == x+1)));
        } else {
            return false;
        }
    }
    
    // x,y de l'aventurier et x2,y2 de la tuile
    @Override
    public boolean TuileAssechable(int x, int y, int x2, int y2, String etat) {
        //Rappelle de la fonction qui renvoie les tuiles inondées, + les tuile en diagonales
        if (etat.equals("inondé")) {
            return (super.TuileAssechable(x, y, x2, y2, etat)) || ((y2 == y+1 && (x2 == x-1 || x2 == x+1)) || (y2 == y-1 && (x2 == x-1 || x2 == x+1)));
        } else {
            return false;
        }
    }
        
}