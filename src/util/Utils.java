package util;

import java.awt.Color;

public class Utils {
    
    public static enum Commandes {
        
        COMMENCER_JEU("Commencer le jeu"),
        VALIDER_JOUEURS("Valider l'inscription des joueurs"),
        SE_DEPLACER("Déplacer son pion"),
        ASSECHER("Assécher une tuile"),
        DONNER("Donner une carte à un autre joueur"),
        RECUPERER_TRESOR("Récupérer le trésor de la tuile"),
        FIN_TOUR("Terminer son tour"),
        CHOISIR_CARTE("Utiliser une carte trésor"),
        CHOISIR_TUILE("Sélectionner une tuile"), 
        CHOISIR_JOUEUR("Choisir un joueur à qui donner une carte"),
        DEPLACER_AUTRE("Déplacer un autre joueur"),
        VOIR_DEFAUSSE("Un joueur souhaite voir la défausse de cartes Tirage"),
        CARTE_MAIN_PLEINE("Envoyé si le joueur pioche des cartes et que sa main est pleine"),
        DEFAUSSER_CARTE("Defausse une carte"),
        ACTION_SPECIALE("Quand l'utilisateur clique sur une carte AS"),
        HELICO("Déplacement du pilote avec l'hélicoptere"),
        RECOMMENCER("Recommence la partie"),
        REMPLACER("Pour remplacer une carte par une carte piochée"),
        DEFAUSSER_PIOCHE("Défausse toutes les cartes piochées");
        
        private final String libelle ;  

        Commandes(String libelle) {
            this.libelle = libelle ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }
    }

    public static enum Tresor {
        PIERRE("La Pierre Sacrée", new Color(141,79,9), new Color(255,242,0), Parameters.TRESORS + "pierre.png"),
        ZEPHYR("La statue du Zéphyr", new Color(255,215,0), new Color(208,26,136), Parameters.TRESORS + "zephyr.png"),
        CRISTAL("Le Cristal Ardent", new Color(219,56,154), new Color(99,187,242), Parameters.TRESORS + "cristal.png"),
        CALICE("Le Calice de l'Onde", new Color(27,188,245), new Color(141,79,9), Parameters.TRESORS + "calice.png") ;

        String libelle;
        Color bgColor ;
        Color textColor ;
        String pathPicture ;

        Tresor(String libelle, Color bgColor, Color textColor, String pathPicture) {
            this.libelle = libelle;
            this.bgColor = bgColor ;
            this.textColor = textColor ;
            this.pathPicture = pathPicture ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getBgColor() {
            return this.bgColor ;
        }

        public Color getTextColor() {
            return this.textColor ;
        }
        
        public String getPathPicture() {
            return this.pathPicture ;
        }

        public static Tresor getFromName(String name) {
            if (name.equals(PIERRE.name())) return PIERRE;
            if (name.equals(ZEPHYR.name())) return ZEPHYR;
            if (name.equals(CRISTAL.name())) return CRISTAL; 
            if (name.equals(CALICE.name())) return CALICE;
            return null;
        }
    }
}
