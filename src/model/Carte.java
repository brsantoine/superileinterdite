package model;

public class Carte {

        private String nom;
	String propriétaire; //correspond au String unique d'une pile, ou d'une défausse, ou du nom d'un aventurier
        
        public Carte(String nom, String proprio) {
            this.nom = nom;
            changerProprio(proprio);
        }
        
        public void changerProprio(String proprio) {
            propriétaire = proprio;
        }
}