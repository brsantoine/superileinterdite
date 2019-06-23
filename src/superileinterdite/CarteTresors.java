package superileinterdite;

public class CarteTresors extends CarteMain {
    Tresors tresor;
    
    public CarteTresors (String nom, Tresors tresor) {
        super(nom);
        this.tresor = tresor;
    }
    
    public Tresors getTresor(){
        return this.tresor;
    }
}