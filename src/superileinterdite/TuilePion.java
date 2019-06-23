package superileinterdite;

import model.Tuile;
import model.Aventurier;

public class TuilePion extends Tuile {

    Aventurier aventurier;

    public TuilePion(String nom, Aventurier aventurier) {
        super(nom);
        // Affecte un aventurier a la tuile et initialise la tuile de l'aventurier
        this.aventurier = aventurier;
        aventurier.setTuile(this);
    }
        
}