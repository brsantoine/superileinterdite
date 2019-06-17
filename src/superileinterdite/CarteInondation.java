package superileinterdite;

import model.Carte;
import model.Tuile;

public class CarteInondation extends Carte {
    
    Tuile tuile;
    
    public CarteInondation(String nom, String proprio, Tuile tuile) {
        super(nom, proprio);
        this.tuile = tuile;
    }
    
    
}