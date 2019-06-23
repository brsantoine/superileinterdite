package aventuriers;

import model.Aventurier;
import java.util.ArrayList;
import superileinterdite.Grille;
import model.Tuile;

public class Pilote extends Aventurier {
    
    private boolean helico = true; //Permet de vérifier si il a déjà utilisé son hélicoptère pendant le tour
            
    public Pilote () {
        super("pilote");
    }
    
    public ArrayList<Tuile> deplacementHelico(Grille grille) {
        //Comme pour les TuilesAccessibles, ajoute directement au vecteur final toutes les tuiles de la grille qui sont seche ou inondé
        ArrayList<Tuile> tuilesAccess = new ArrayList<>();
        for (Tuile tuile : grille.getTuiles()) {
            if (!tuile.getEtat().equals("coulé")) {
                tuilesAccess.add(tuile);
            }
        }
        tuilesAccess.remove(this.getTuile());
        return tuilesAccess;
  
    }
    
    public void activerHelico() {
        helico = true;
    }
    
    public void desactiverHelico() {
        helico = false;
    }
    
    public boolean getHelico(){
        return this.helico;
    }
}

