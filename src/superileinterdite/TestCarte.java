/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superileinterdite;

import util.Observateur;
import model.*;
import aventuriers.*;
import java.util.*;
import util.*;
import view.*;
/**
 *
 * @author marquest
 */
public class TestCarte {
	
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            ArrayList<Tresors> lesTresors ;
            Grille laGrille; 
            ArrayList<Aventurier> lesJoueurs;
            ArrayList<Pile> sesPiles ;
            Aventurier plongeur = new Plongeur();
            Aventurier ingenieur = new Ingenieur();
            Aventurier messager = new Messager();
            Aventurier navigateur = new Navigateur();
            Aventurier pilote = new Pilote();
            Aventurier explorateur = new Explorateur();
            laGrille = new Grille();
            lesJoueurs = new ArrayList<Aventurier>();
            
            lesJoueurs.add(pilote);
            lesJoueurs.add(plongeur);
            lesJoueurs.add(ingenieur);
            lesJoueurs.add(messager);
            lesJoueurs.add(navigateur);
            lesJoueurs.add(explorateur);
            Collections.shuffle(lesJoueurs);
            // Creation des tuiles
            laGrille.addTuile(new Tuile("Le val du crépuscule"));
            laGrille.addTuile(new Tuile("Le marais brumeux"));
            laGrille.addTuile(new Tuile("Le pont des abimes"));
            laGrille.addTuile(new Tuile("Les dunes de l'illusion"));
            laGrille.addTuile(new Tuile("Les falaises de l'oubli"));
            laGrille.addTuile(new Tuile("L'observatoire"));
            laGrille.addTuile(new Tuile("Le rocher fantome"));
            laGrille.addTuile(new Tuile("La foret pourpre"));
            laGrille.addTuile(new Tuile("Le lagon perdu"));
            laGrille.addTuile(new Tuile("La tour de guet"));
            // Creation des Tuiles depart
            laGrille.addTuile(new TuilePion("Heliport",pilote));
            laGrille.addTuile(new TuilePion("La porte de bronze",ingenieur));
            laGrille.addTuile(new TuilePion("La porte de fer",plongeur));
            laGrille.addTuile(new TuilePion("La porte d'or",navigateur));
            laGrille.addTuile(new TuilePion("La porte d'argent",messager));
            laGrille.addTuile(new TuilePion("La porte de cuivre",explorateur));
            // Creation des trésors
            Tresors Pierre = new Tresors("La Pierre sacrée");
            Tresors Statue = new Tresors("La Pierre sacrée");            
            Tresors Cristal = new Tresors("La Pierre sacrée");            
            Tresors Calice = new Tresors("La Pierre sacrée");   
            // Creation des tuilesTresors
            laGrille.addTuile(new TuileTresor("La caverne du brasier",Cristal));
            laGrille.addTuile(new TuileTresor("La caverne des ombres",Cristal));
            laGrille.addTuile(new TuileTresor("Jardin des hurlements",Statue));
            laGrille.addTuile(new TuileTresor("Jardin des murmures",Statue));
            laGrille.addTuile(new TuileTresor("Le temple du soleil",Pierre));
            laGrille.addTuile(new TuileTresor("Le temple de la lune",Pierre));
            laGrille.addTuile(new TuileTresor("Le palais de corail",Calice));
            laGrille.addTuile(new TuileTresor("Le palais des marées",Calice));
            // Randomizer la grille du jeu
            laGrille.randomizeGrille();                                                                        
            Pile PileTresor = new Pile("Pile Trésor");
            PileTresor.addPile(new CarteTresors("Calice",null,Calice));
            PileTresor.addPile(new CarteTresors("Calice",null,Calice));
            PileTresor.addPile(new CarteTresors("Calice",null,Calice));
            PileTresor.addPile(new CarteTresors("Calice",null,Calice));
            PileTresor.addPile(new CarteTresors("Calice",null,Calice));
            //
            PileTresor.addPile(new CarteTresors("Statue",null,Statue));
            PileTresor.addPile(new CarteTresors("Statue",null,Statue));
            PileTresor.addPile(new CarteTresors("Statue",null,Statue));
            PileTresor.addPile(new CarteTresors("Statue",null,Statue));
            PileTresor.addPile(new CarteTresors("Statue",null,Statue));
            //
            PileTresor.addPile(new CarteTresors("Cristal",null,Cristal));
            PileTresor.addPile(new CarteTresors("Cristal",null,Cristal));
            PileTresor.addPile(new CarteTresors("Cristal",null,Cristal));
            PileTresor.addPile(new CarteTresors("Cristal",null,Cristal));
            PileTresor.addPile(new CarteTresors("Cristal",null,Cristal));
            //
            PileTresor.addPile(new CarteTresors("Pierre",null,Pierre));
            PileTresor.addPile(new CarteTresors("Pierre",null,Pierre));
            PileTresor.addPile(new CarteTresors("Pierre",null,Pierre));
            PileTresor.addPile(new CarteTresors("Pierre",null,Pierre));
            PileTresor.addPile(new CarteTresors("Pierre",null,Pierre));
            // Heli = Helicoptere
            PileTresor.addPile(new CarteActionSpeciale("Heli",null));
            PileTresor.addPile(new CarteActionSpeciale("Heli",null));
            PileTresor.addPile(new CarteActionSpeciale("Heli",null));
            // MDeaux = Montée des eaux
            PileTresor.addPile(new CarteActionSpeciale("MDeaux",null));
            PileTresor.addPile(new CarteActionSpeciale("MDeaux",null));
            PileTresor.addPile(new CarteActionSpeciale("MDeaux",null));
            // Sac = Sac de sables
            PileTresor.addPile(new CarteActionSpeciale("Sac",null));
            PileTresor.addPile(new CarteActionSpeciale("Sac",null));
            //
            PileTresor.randomizePile();

            
            Pile PileInondation = new Pile("Pile Inondation");
            for (Tuile tuile : laGrille.getTuiles()) {
                PileInondation.addPile(new CarteInondation(tuile));
            }
            PileInondation.randomizePile();
            
            System.out.println(PileTresor.getSesCartes().size());
            System.out.println(PileInondation.getSesCartes().size());
        
            
            
            for (Carte carte : PileTresor.getSesCartes()) {
                System.out.println(carte.getNom());
            }
    
            for (Carte carte : PileInondation.getSesCartes()) {
                System.out.println(carte.getNom());
            }
            
            for (int i = 0; i <= 1; i++) {
                if ( ((CarteMain)PileTresor.getSesCartes().get(i)).getNom() == "MDeaux") {
                    i--;
                    PileTresor.randomizePile();
                    System.out.println("MONTEE DES EAUX AAAAAAAAAAAAAAAAA");
                } else {
                    ((CarteMain)PileTresor.getSesCartes().get(i)).changerProprio(ingenieur.getRole());
                    ingenieur.addCarte((CarteMain) PileTresor.getSesCartes().get(i));
                    System.out.println(PileTresor.getSesCartes().get(i).getNom());
                    System.out.println(((CarteMain)PileTresor.getSesCartes().get(i)).getProprio());
                    PileTresor.RemoveCarte(PileTresor.getSesCartes().get(i));             
                }
            }
           
            System.out.println("--------------------------------------------------");
            System.out.println(ingenieur.getCartes().get(0).getNom());
            System.out.println(ingenieur.getCartes().get(1).getNom());
            System.out.println("--------------------------------------------------");
            for (Carte carte : PileTresor.getSesCartes()) {
                System.out.println(carte.getNom());
            }
            
            
            
            
    }
    
}
