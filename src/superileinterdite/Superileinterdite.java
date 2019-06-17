/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superileinterdite;
import view.*;
        
/**
 *
 * @author gervraua
 */
public class Superileinterdite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controleur controleur = new Controleur();
        controleur.commencerJeu();
        VueMenu ihm = new VueMenu();
        ihm.addObservateur(controleur);
        controleur.setIhmVueMenu(ihm);
        ihm.afficher();
        
    }
    
}
