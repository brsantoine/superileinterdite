package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author IUT2-Dept Info
 */
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
    public static final Integer CARD_HEIGHT = (int) CARD_WIDTH*7/5 -20; // Hauteur de chaque carte (ratio 7/5 - bordures)
    
    
    
    // ====================================================================================
    // Jeu
    public static final Integer NB_INONDATIONS_INITIALES = (SIMPLIFIED ? 6 : 6) ; // Nombre de tuiles inondées au démarrage du jeu
    public static final Integer NB_MONTEES_DES_EAUX = (SIMPLIFIED ? 2 : 3) ; // Nombre de cartes Montées des Eaux en jeu simplifié ou non
    public static final Integer NB_HELICOPTERES = 3 ; // Nombre de cartes Hélicoptère
    public static final Integer NB_SACS_DE_SABLE = 2 ; // Nombre de cartes Sacs de Sable

    // ====================================================================================
    // COULEURS
    public static Color PLATEAU_BG = new Color(67, 119, 204); // Couleur de fond du plateau 
    public static Color TUILE_ASSECHEE_BG = Color.WHITE; // Couleur de fond d'une tuile asséchée
    public static Color TUILE_INONDEE_BG = new Color(138, 169, 204); // Couleur de fond d'une tuile inondée
    public static Color COULEUR_TUILE_ASSECHEE_GRISEE = new Color(192, 192, 192); // Couleur de fond d'une tuile asséchée non cliquable
    public static Color COULEUR_TUILE_INONDEE_GRISEE = new Color(175, 192, 192); // Couleur de fond d'une tuile asséchée non cliquable
    public static Color COULEUR_TUILE_PAIRE = new Color(230, 180, 181) ; // Couleur de fond du titre d'une tuile (1/2)
    public static Color COULEUR_TUILE_IMPAIRE = new Color(184, 208, 138); // Couleur de fond du titre d'une tuile (2/2)
    public static Color COULEUR_JOUEUR_SELECTIONNABLE = new Color(190, 208, 255);
    public static Color COULEUR_JOUEUR_COURANT = new Color(150, 198, 137);

    // ====================================================================================
    // Chemins vers les images
//    public static final String ROOT = System.getProperty("user.dir") ;
    public static final String IMAGES = "images/";
    public static final String PIONS = "images/pions/";
    public static final String TRESORS = "images/tresors/";
    public static final String TUILES = "images/tuiles/";
    public static final String CARTES = "images/cartes/";
    public static final String ICONS = "images/icones/";

    // ====================================================================================
    // Icones pour les boutons en mode images
    public static final String ICON_MOVE = ICONS + "iconMove.png" ;
    public static final String ICON_MOVE_DISABLED = ICONS + "iconMove_disabled.png" ;
    public static final Integer ICON_MOVE_WIDTH = 24 ;

    public static final String ICON_DRY = ICONS + "iconDry.png" ;
    public static final String ICON_DRY_DISABLED = ICONS + "iconDry_disabled.png" ;
    public static final Integer ICON_DRY_WIDTH = 24 ;

    public static final String ICON_GIVE = ICONS + "iconGive.png" ;
    public static final String ICON_GIVE_DISABLED = ICONS + "iconGive_disabled.png" ;
    public static final Integer ICON_GIVE_WIDTH = 24 ;

    public static final String ICON_GET = ICONS + "iconGet.png" ;
    public static final String ICON_GET_DISABLED = ICONS + "iconGet_disabled.png" ;
    public static final Integer ICON_GET_WIDTH = 24 ;

    public static final String ICON_RECEIVE = ICONS + "iconTarget.png" ;
    public static final String ICON_RECEIVE_DISABLED = ICONS + "iconTarget_disabled.png" ;
    public static final Integer ICON_RECEIVE_WIDTH = 17 ;

    public static final String ICON_DONE = ICONS + "iconDone.png" ;
    public static final String ICON_DONE_DISABLED = ICONS + "iconDone_disabled.png" ;
    public static final Integer ICON_DONE_WIDTH = 26 ;

    public static final String ICON_SHIFT = ICONS + "iconShift.png" ;
    public static final String ICON_SHIFT_DISABLED = ICONS + "iconShift_disabled.png" ;
    public static final Integer ICON_SHIFT_WIDTH = 24 ;
    
}
