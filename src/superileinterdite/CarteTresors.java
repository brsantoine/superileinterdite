package superileinterdite;

public class CarteTresors extends CarteMain {
    Tresors tresor;
    
    public CarteTresors (String nom, String proprio, Tresors tresor) {
        super(nom, proprio);
        this.tresor = tresor;
    }
    
    public Tresors getTresor(){
        return this.tresor;
    }
}