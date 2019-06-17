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
    public HashMap<Tuile, Integer> TuilesAccessibles(Grille grille) {
		// TODO - implement Aventurier.deplacement
		int x = this.getTuile().getX();
                int y = this.getTuile().getY();
                
                ArrayList<Tuile> tuilesAccess1 = new ArrayList<>();
                for (Tuile tuile : grille.getTuiles()) {
                    if (TuileAccessible(x, y, tuile.getX(), tuile.getY(), tuile.getEtat())) {
                        tuilesAccess1.add(tuile);
                    }
                }
                
                HashMap<Tuile, Integer> tuilesAccess = new HashMap<>();
                for (Tuile tuile : tuilesAccess1) {
                    tuilesAccess.putIfAbsent(tuile, 1);
                }

                return tuilesAccess;
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
            
            //On ajoute aussi la tuile sur laquelle il est car on a le droit d'assécher sa propre tuile
            tuilesAssech.add(this.getTuile());
            
            return tuilesAssech;
	}
    
        @Override
        public boolean TuileAccessible(int x, int y, int x2, int y2, String etat) {
           // x,y de l'aventurier et x2,y2 de la tuile
           
           //Rappelle de la fonction qui renvoie les tuiles adjacentes non coulées, + les tuile en diagonales
            if (!etat.equals("coulé")) {
                    return (super.TuileAccessible(x, y, x2, y2, etat)) || ((y2 == y+1 && (x2 == x-1 || x2 == x+1)) || (y2 == y-1 && (x2 == x-1 || x2 == x+1)));
                } else {
                    return false;
                }
        }
        
        @Override
        public boolean TuileAssechable(int x, int y, int x2, int y2, String etat) {
            // x,y de l'aventurier et x2,y2 de la tuile
            
            //Rappelle de la fonction qui renvoie les tuiles inondées, + les tuile en diagonales
            if (etat.equals("inondé")) {
                return (super.TuileAssechable(x, y, x2, y2, etat)) || ((y2 == y+1 && (x2 == x-1 || x2 == x+1)) || (y2 == y-1 && (x2 == x-1 || x2 == x+1)));
            } else {
                return false;
            }
        }
        
}