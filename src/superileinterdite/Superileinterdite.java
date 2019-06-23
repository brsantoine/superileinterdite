package superileinterdite;
import view.*;

public class Superileinterdite {

    public static void main(String[] args) {
        Controleur controleur = new Controleur();
        VueMenu ihm = new VueMenu();
        ihm.addObservateur(controleur);
        controleur.setIhmVueMenu(ihm);
        ihm.afficher();
    }
}
