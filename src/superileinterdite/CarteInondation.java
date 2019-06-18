package superileinterdite;

import model.Carte;
import model.Tuile;

public class CarteInondation extends Carte {
    
    Tuile tuile;
    
    public CarteInondation(Tuile tuile) {
        super(tuile.getNom());
        this.tuile = tuile;
    }

    public Tuile getTuile() {
        return tuile;
    }
    
    
    
    
}