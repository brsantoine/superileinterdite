package superileinterdite;

public class CarteActionSpeciale extends CarteMain {
    //Description de l'action de la carte
    String description; 
    
    public CarteActionSpeciale(String nom, String proprio, String description) {
        super(nom, proprio);
        this.description = description;
    }
    
}
