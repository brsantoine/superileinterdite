package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Parameters {
    
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    // ====================================================================================
    // Paramètres NF
    public static final Boolean LOGS = false ; // Afficher des traces par System.out.println()
    public static final Boolean ALEAS = true ; // Attribuer les aventuriers aléatoirement ou non, mélanger les défausses et les pioches
    public static final Boolean SIMPLIFIED = true ; // Processus simplifié (pas d'interruption pendant les tours)
    
    // ====================================================================================
    // Paramètres IHM
    public static final Boolean UNDECORATED = true ; // Indique si la barre de fenêtre doit être affichée
    public static final Integer DECORATION_HEIGHT = 25 ; // Taille de la barre de fenêtre    
    public static final Boolean RESIZABLE = false ; // Indique si les fenêtres peuvent être redimensionnées    
    public static final Integer SWING_BORDERS_HEIGHT = 4 ; // Taille des bordures de côté d'une fenêtre
    public static final Integer TOP_VUE_AVENTURIER = UNDECORATED ? 30 : 0 ; // Position des fenêtres aventuriers
    public static final Integer TOP_AUTRES_VUES = 265 - (UNDECORATED ? 0 : DECORATION_HEIGHT) ; // Position des fenêtres Niveau, Plateau et Message en NoPic
    public static final Integer HAUTEUR_AUTRES_VUES = 491 + (UNDECORATED ? 0 : DECORATION_HEIGHT) ; // Hauteur des fenêtres Niveau, Plateau et Message    
    
    // RESPONSIVE 
    public static final Integer HORIZONTAL_BUFFER = (int) screenSize.getWidth()/100; // Espace de 1% entre les bords horizontaux de l'écran et les fenêtres
    public static final Integer VERTICAL_BUFFER = (int) screenSize.getHeight()/20; // Espace de 5% entre les bords horizontaux de l'écran et les fenêtres
    public static final Integer TASKBAR_BUFFER = (int) screenSize.getHeight()/50; // Espace de 2% pour prendre ee compte la barre des tâches
    
    public static final Integer LEFT_FRAME_WIDTH = (int) screenSize.getWidth()/5; // Largeur de la fenêtre gauche de VueJeu en fonction de la taille de l'écran
    public static final Integer LEFT_FRAME_HEIGHT = (int) screenSize.getHeight() - VERTICAL_BUFFER*2; // Hauteur de la fenêtre gauche de VueJeu en fonction de la taille de l'écran 
    
    public static final Integer GAME_FRAME_WIDTH = (int) screenSize.getWidth() - LEFT_FRAME_WIDTH - HORIZONTAL_BUFFER*2; // Largeur de la fenêtre de jeu de VueJeu en fonction de la taille de l'écran
    public static final Integer GAME_FRAME_HEIGHT = (int) screenSize.getHeight() - VERTICAL_BUFFER*2; // Hauteur de la fenêtre de jeu de VueJeu en fonction de la taille de l'écran
    
    public static final Integer VERTICAL_SPACE = GAME_FRAME_HEIGHT/50; // Hauteur entre chaque panel de VueJeu
    public static final Integer HORIZONTAL_SPACE = (int) screenSize.getWidth()/100; // Largeur entre chaque panel de VueJeu
    
    public static final Integer BUTTONS_PANEL_HEIGHT = GAME_FRAME_HEIGHT/10; // Hauteur des panels contenant les boutons
    
    public static final Integer GRID_WIDTH = (int) GAME_FRAME_WIDTH*3/5; // Largeur de la grille (3/5 de la fenêtre de jeu)
    public static final Integer GRID_HEIGHT = (int) GAME_FRAME_HEIGHT - BUTTONS_PANEL_HEIGHT - VERTICAL_SPACE*2; // Hauteur de la grille
    
    public static final Integer PIOCHER_FRAME_WIDTH = (int) GRID_WIDTH/2; // Largeur de la grille (3/5 de la fenêtre de jeu)
    public static final Integer PIOCHER_FRAME_HEIGHT = (int) GRID_HEIGHT/3; // Hauteur de la grille
    
    public static final Integer VUE_AVENTURIER_WIDTH = (int) GAME_FRAME_WIDTH - GRID_WIDTH; // Largeur des fenêtres Aventuriers
    public static final Integer VUE_AVENTURIER_HEIGHT = (int) (GAME_FRAME_HEIGHT/4) - BUTTONS_PANEL_HEIGHT - VERTICAL_SPACE*2; // Hauteur des fenêtres Aventuriers
    
    public static final Integer TUILE_BOUNDS = (int) GRID_WIDTH/6; // Limites de longueur de chaque coté de chaque tuile
    public static final Integer TUILE_SIZE = (int) TUILE_BOUNDS - TUILE_BOUNDS*15/100; // Longueur de chaque coté de chaque tuile
    
    public static final Integer PION_BOUNDS = (int) TUILE_BOUNDS/2; // Limites de longueur de chaque coté de chaque pion 
    public static final Integer PION_SIZE = (int) PION_BOUNDS/2; // Longueur de chaque coté de chaque pion
    public static final Integer PION_VERTICAL_SPACE = (int) TUILE_BOUNDS/6; // Ecart vertical entre chaque pion 
    public static final Integer PION_HORIZONTAL_SPACE = (int) TUILE_BOUNDS/3; // Ecart horizontal entre chaque pion  
    
    public static final Integer CARD_VERTICAL_SPACE = 5; // Ecart vertical entre une carte et le nom du joueur
    public static final Integer CARD_HORIZONTAL_SPACE = 10; // Ecart horizontal entre chaque cartes
    public static final Integer CARD_WIDTH = (int) (VUE_AVENTURIER_WIDTH - HORIZONTAL_SPACE*2 - CARD_HORIZONTAL_SPACE*4)/5; // Longueur de chaque carte
    public static final Integer CARD_HEIGHT = (int) CARD_WIDTH*7/5 -12; // Hauteur de chaque carte (ratio 7/5 - bordures)
    
    // ====================================================================================
    // COULEURS
    public static Color COULEUR_JOUEUR_SELECTIONNABLE = new Color(190, 208, 255);
    public static Color COULEUR_JOUEUR_COURANT = new Color(150, 198, 137);

    // ====================================================================================
    // Chemins vers les images
    public static final String IMAGES = "images/";
    public static final String PIONS = "images/pions/";
    public static final String TRESORS = "images/tresors/";
    public static final String TUILES = "images/tuiles/";
    public static final String CARTES = "images/cartes/";
    public static final String ICONS = "images/icones/";

}