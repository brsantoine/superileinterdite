/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superileinterdite;

import model.Carte;




public abstract class CarteMain extends Carte{
    
    private String proprio;
    
    public CarteMain(String nom, String proprio) { 
        super(nom); 
        changerProprio(proprio);
    }
    
    public void changerProprio(String proprio) {
        this.proprio = proprio;
    }

    public String getProprio() {
        return proprio;
    }
    
    

}
