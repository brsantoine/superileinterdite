package superileinterdite;


import model.*;
import aventuriers.*;
import java.util.*;
import util.*;
import view.*;

public class Controleur implements Observateur {

	private ArrayList<Tresors> lesTresors ;
	private Grille laGrille; 
	private ArrayList<Aventurier> lesJoueurs;
	private ArrayList<VueAventurier> ihmAventuriers;
  private VueMenu ihmMenu;
	private VueInscription ihmInscription;
	private VueJeu ihmJeu;
	private ArrayList<Pile> sesPiles ;
	private int niveauEau;
  private int nbAction;
  private int tour;
  private boolean doubleAssechement = false, modeDeplacement = false, modeAssechement = false;
  private ArrayList<Tuile> tAssech, tAccess;
  private Pile PileTresor,PileInondation,defausseTresor,defausseInondation;
        

        public Controleur() {
            lesJoueurs = new ArrayList();
            laGrille = new Grille();
            lesTresors = new ArrayList();
            sesPiles = new ArrayList();
        }
        
        public void setIhmVueJeu(VueJeu ihmJeu){
            this.ihmJeu = ihmJeu;
            this.ihmJeu.addObservateur(this);
        }
        
         public void setIhmVueInscription(VueInscription ihmInscription){
            this.ihmInscription = ihmInscription;
            this.ihmInscription.addObservateur(this);
        }
         
        public void setIhmVueMenu(VueMenu ihmMenu){
            this.ihmMenu = ihmMenu;
        }
        
        // Instanciation de la grille de toutes les tuiles et les aventuriers
        public void commencerJeu() {                                            
            // Creation des aventuriers
            Aventurier plongeur = new Plongeur();
            Aventurier ingenieur = new Ingenieur();
            Aventurier messager = new Messager();
            Aventurier navigateur = new Navigateur();
            Aventurier pilote = new Pilote();
            Aventurier explorateur = new Explorateur();
            this.lesJoueurs.add(pilote);
            this.lesJoueurs.add(plongeur);
            this.lesJoueurs.add(ingenieur);
            this.lesJoueurs.add(messager);
            this.lesJoueurs.add(navigateur);
            this.lesJoueurs.add(explorateur);
            Collections.shuffle(this.lesJoueurs);
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
            for (int i = 0; i < laGrille.getTuiles().size(); i++) {
                laGrille.getTuiles().get(i).setID(i);
                
            }
            this.remettreAJourAction();
            this.tour=1;
            this.niveauEau=1;                                                                         
            PileTresor = new Pile("Pile Trésor");
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

            
            PileInondation = new Pile("Pile Inondation");
            for (Tuile tuile : laGrille.getTuiles()) {
                PileInondation.addPile(new CarteInondation(tuile));
            }
            PileInondation.randomizePile();
            
            defausseTresor = new Pile("Defausse Tresor");
            defausseInondation = new Pile("Defausse Inondation");
       
        }
        
        
        
        
        // Met l'etat du tresor en récupéré
	public void recupererTresor(Tresors tresor) {                              
		tresor.setEtat(true);
	}
        
	public void piocherTresorDebut() {     
            for (Aventurier a : getJoueurs()) {
                for (int i = 0; i <= 1; i++) {
                    if ( ((CarteMain)PileTresor.getSesCartes().get(i)).getNom() == "MDeaux") {
                        i--;
                        PileTresor.randomizePile();
                    } else {
                        ((CarteMain)PileTresor.getSesCartes().get(i)).changerProprio(a.getRole());
                        a.addCarte((CarteMain) PileTresor.getSesCartes().get(i));
                        PileTresor.RemoveCarte(PileTresor.getSesCartes().get(i));             
                    }
                }
            }
            
	}
        
	public void piocherTresor(Aventurier a) {  
            
            ArrayList montrerCartes = new ArrayList<Carte>();
            
            for (int i = 0; i <= 1; i++) {
                if ( ((CarteMain)PileTresor.getSesCartes().get(i)).getNom() == "MDeaux") {
                    this.montéeDesEaux();
                    defausseInondation.randomizePile();
                    ArrayList temp = new ArrayList<Carte>();
                    temp = defausseInondation.getSesCartes();
                    for (Carte c : PileInondation.getSesCartes()) {
                        temp.add(c);                      
                    }            
                    PileInondation.ViderPile();
                    PileInondation.setSesCartes(temp);
                    
                } else if (a.getCartes().size() == 5) {
                    
                    if (i == 0) {
                        montrerCartes.add(PileTresor.getSesCartes().get(i));   
                        montrerCartes.add(PileTresor.getSesCartes().get(i+1));   
                    } else {
                        montrerCartes.add(PileTresor.getSesCartes().get(i));   
                    }                  
                    break;
                    
                } else if(PileTresor.getSesCartes().isEmpty()) {
                    defausseTresor.randomizePile();
                    PileTresor.setSesCartes(defausseTresor.getSesCartes());
                    defausseTresor.ViderPile();
                    i--;
                } else {
                    ((CarteMain)PileTresor.getSesCartes().get(i)).changerProprio(a.getRole());
                    a.addCarte((CarteMain) PileTresor.getSesCartes().get(i));
                    PileTresor.RemoveCarte(PileTresor.getSesCartes().get(i));             
                }         
            }
            
            if (montrerCartes.size() > 0) {
                // Envoyer l arraylist a l ihm
            }
            
	}
        
        public void defausserTresor() {
            
            
        }
                
                

        // Augmente le niveau de l'eau
	public void montéeDesEaux() {                                           
            this.niveauEau++;
            this.ihmJeu.getVueNiveau().setNiveau(niveauEau); 
	}
        
        // Definit le nombre de carte à piocher en fonction du niveau de l'eau
        public int nbCarteInonAPiocher(int i){                                  
            if(i<3){
                return 2;
            }
            else if(3<=i && i<6){
                return 3;
            }
            else if(6<=i && i<8){
                return 4;
            }
            else{
                return 5;
            }
        }
        
        // Remet le nombre d'action a 3 au debut d'un tour
        public void remettreAJourAction(){                                      
            this.nbAction=3;
        }
        
        // Enleve une action
        public void actionFinie(){                                              
            this.nbAction--;
        }
        
        // Renvoie le nombre d'actions restantes
        public int getActions(){                                                
            return nbAction;
        }
        
        // Renvoie le nombre de tours effectués
        public int getTour(){                                                   
            return this.tour;
        }
        
        // Permet de savoir le joueur qui doit jouer
        public Aventurier aQuiLeTour(){                                         
            int x=0;
            for(Aventurier a : this.lesJoueurs){
                if(this.getTour()%this.nbJoueurs()==x){
                    return a;
                }
                x=(x+1)%this.nbJoueurs();
            } 
            return this.lesJoueurs.get(x);
        }
        
        // Renvoie le nombre de joueurs dans la partie
        public int nbJoueurs(){                                                 
            return this.lesJoueurs.size();
        }
            
        // Effectue un nouveau tour
        public void nvtour(){
            this.tour++;
            System.out.println(this.aQuiLeTour().getRole());
            this.remettreAJourAction();
            this.tourDeJeu();
        }
        
        // Renvoie la grille contenant les tuiles 
        public Grille getGrille(){                                              
            return this.laGrille;
        }
        
        // Renvoie l'ensemble des tresors 
        public ArrayList<Tresors> getTresors(){                                 
            return this.lesTresors;
        }
        
        // Renvoie le niveau de l'eau 
        public int getNiveauEau(){                                              
            return this.niveauEau;
        }
        
        // Instancie le niveau de l'eau
        public void setNiveauEau(int niveau){                                    
            this.niveauEau = niveau;
        }
        
        // Renvoie la liste de joueurs jouant
        public ArrayList<Aventurier> getJoueurs(){                              
            return this.lesJoueurs;
        }
        
        // Renvoie l'ensemble des piles du jeu
        public ArrayList<Pile> getPiles(){                                      
            return this.sesPiles;
        }

        public Pile getPileTresor() {
            return PileTresor;
        }

        public Pile getPileInondation() {
            return PileInondation;
        }

        
        
       
        // Renvoie toutes les actions possibles en fonction des multiples parametres du joueur(tuile,carte, role ect..)
        public void tourDeJeu(){                       
            // Actions possibles
            ArrayList<Utils.Commandes> tm = new ArrayList<Utils.Commandes>(); 
            int i=0;
            ihmJeu.updateActions(getActions());
            
            if(!this.aQuiLeTour().getCartes().isEmpty()){                       
                tm.add(Utils.Commandes.DONNER);
            }

            // Regarde si le joueur peut gagner un trésor
            for(CarteMain c : this.aQuiLeTour().getCartes()){                   
                for(CarteMain a : this.aQuiLeTour().getCartes()){
                   if(a instanceof CarteTresors && c instanceof CarteTresors){

                   if(((CarteTresors) c).getTresor().equals(((CarteTresors) a).getTresor())){
                    i++;
                   }
                   if(i==4 && ((TuileTresor) this.aQuiLeTour().getTuile()).getTresor()==((CarteTresors) c).getTresor()){
                       tm.add(Utils.Commandes.RECUPERER_TRESOR);
                       break;
                   }
                 }
                }
               i=0;
            }

            tAssech = new ArrayList<>();
            
            tAssech = this.aQuiLeTour().TuilesAssechables(this.getGrille());
                                                
            if (tAssech.size() > 0) {
                tm.add(Utils.Commandes.ASSECHER);  
            }

            // Regarde si le joueur peut donner une carte tresor
            for(Aventurier a : this.getJoueurs()){                               
               if(a==this.aQuiLeTour()){  
               }
               else{
                   if(a.getCartes().size()!=5  && this.aQuiLeTour().getTuile()==a.getTuile()){
                       tm.add(Utils.Commandes.DONNER);
                       break;
                   }
                   else if(a.getCartes().size()!=5  && this.aQuiLeTour().getRole()=="messager"){
                       tm.add(Utils.Commandes.DONNER);
                       break;
                   }
               }
           }

           tAccess = new ArrayList<>();
           tAccess = this.aQuiLeTour().TuilesAccessibles(laGrille);
           
           
           
            // Regarde si le joueur peut se deplacer en fonction de son rôle
            if(this.aQuiLeTour().getRole()=="plongeur"){                         
                tm.add(Utils.Commandes.SE_DEPLACER);
            }
            else if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==true){
                tm.add(Utils.Commandes.SE_DEPLACER);
            }
            else if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==false && tAccess.size()>0){
                tm.add(Utils.Commandes.SE_DEPLACER);
            }
            else if(tAccess.size()>0){
                tm.add(Utils.Commandes.SE_DEPLACER);
            }

            // Regarde si le navigateur peut se déplacer
            if(this.aQuiLeTour().getRole()=="navigateur"){                       
               for(Aventurier a : this.getJoueurs()){
                   if(!a.TuilesAccessibles(this.getGrille()).isEmpty()){
                   tm.add(Utils.Commandes.DEPLACER_AUTRE);
                   break;
                   }
                }
            }

            tm.add(Utils.Commandes.FIN_TOUR);
        }
                  
        
        

        // Effectue toutes les actions nécessaires apres qu'un joueur ai fini ses actions  
        public void finTour(){                                                                                                 
            // Actualiser l'ihm   
            this.piocherTresor(aQuiLeTour());
            ihmJeu.finTour();
            
            if (this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==false) {
               ((Pilote) this.aQuiLeTour()).resetHelico();
            }
                   
            int i = 0;
            while (i < this.nbCarteInonAPiocher(this.getNiveauEau())) {
//              this.aQuiLeTour().piocherCarteInon().getTuile().inonderTuile();
                i++;
            }
//            ihm.changerpov();
            // Detecte si la victoire est encore possible
            if (this.victoirePossible()==true) {                                  
                this.nvtour();
            } else {
                ihmJeu.afficherDefaite();
            }
        }
        
      
        @Override
	public void traiterMessage(Message m) {
            switch (m.getCommande()) {
                case CHOISIR_TUILE:
                    if(modeDeplacement){
                       this.actionFinie();
                       // Enleve l'aventurier da la tuile ou il etait
                       this.aQuiLeTour().getTuile().removeAventurier(this.aQuiLeTour());   
                       // Change la tuile de l'aventurier
                       this.aQuiLeTour().updateTuile(this.getGrille().getTuiles().get(m.getIdTuile()));
                       // Met un aventurier sur la nouvelle tuile
                       this.getGrille().getTuiles().get(m.getIdTuile()).addAventurier(this.aQuiLeTour());

                       ihmJeu.getGrille().updateDeplacement(lesJoueurs);
                       tAccess = this.aQuiLeTour().TuilesAccessibles(laGrille);
                       ihmJeu.getGrille().afficherTuilesDeplacer(tAccess);

                       // Si un plongeur est sur une case coulée il ne peut pas finir le tour
                       if (this.aQuiLeTour().getTuile().getEtat() == "coulé") {   
                           ihmJeu.impossibleFinTour();
                       } else {
                           ihmJeu.possibleFinTour();
                       }

                       //if((this.aQuiLeTour()).getRole()=="pilote"){
                       //    this.actionFinie(); // pas encore fini ca fait -1 dans tout les cas
                       //}

                       // Détecte si le joueur peut encore jouer
                       if(this.getActions()>0){                                    
                           this.tourDeJeu();
                       }
                       else{
                           this.finTour();
                       }                   

                    } else if (modeAssechement) {

                        this.getGrille().getTuiles().get(m.getIdTuile()).assecherTuile(); 
//                        ihm.afficherTuilesAssecher(tAssech);
                        tAssech = this.aQuiLeTour().TuilesAssechables(laGrille);
                        ihmJeu.getGrille().afficherTuilesAssecher(tAssech);

                        // Gere le double assechement d'un ingenieur
                        if(this.aQuiLeTour().getRole()=="ingenieur" && !doubleAssechement){            
                            doubleAssechement=true;
                            this.actionFinie();
                        } 
                        else if(this.aQuiLeTour().getRole()=="ingenieur" && doubleAssechement){
                            doubleAssechement=false;
                        } else {
                            this.actionFinie();
                        }
                        ihmJeu.updateActions(this.getActions());

                        // Detecte si le joueur peut encore jouer
                        if(this.getActions()>0){                                   
                            this.tourDeJeu();
                        } 
                        else{
                            this.finTour();
                        }
                    }

                break;
  
                case SE_DEPLACER:

                    modeAssechement = false;
                    modeDeplacement = true;

                    if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==true) {

                        // On affiche déplacement normal

                        // On affiche le déplacement hélicoptère

                        tAccess = (((Pilote) this.aQuiLeTour()).deplacementHelico(this.getGrille()));
                    }

                    ihmJeu.getGrille().afficherTuilesDeplacer(tAccess);
                      
                break;
                  
                case DEPLACER_AUTRE:
                     
                    this.actionFinie(); 
                    // Détecte si le joueur peut encore jouer
                    if(this.getActions()>0){                                    
                        this.tourDeJeu();
                    }
                    else{
                        this.finTour();
                    }
                    
                break;  
 
                
                case ASSECHER: 
                    
                    modeAssechement = true;
                    modeDeplacement = false;
                    //ihm.afficherTuilesAssecher(this.aQuiLeTour().TuilesAssechables(this.getGrille()));
                    ihmJeu.getGrille().afficherTuilesAssecher(tAssech);
                    
                break;

                case COMMENCER_JEU:
                    
                    ihmMenu.cacher();
                     
                    this.commencerJeu();

                    System.out.println(m.getIdAventurier()); //nombre joueurs

                    // Créer la liste de joueur en fonction du nombre de joueur choisi
                    ArrayList<Aventurier> listeJoueurs = new ArrayList<>();               
                    for(int i=0; i<m.getIdAventurier();i++){
                        listeJoueurs.add(this.lesJoueurs.get(i));
                    }

                    // Toute la grille est inondée
                    for (Tuile tuile : laGrille.getTuiles()) {
                        tuile.inonderTuile();
                    }
                    // Tuile 10 coulé pour tester
                    laGrille.getTuiles().get(10).inonderTuile();


                    this.lesJoueurs=listeJoueurs;
                    this.setIhmVueJeu(new VueJeu(lesJoueurs));

                    this.ihmJeu.getGrille().intitialiserGrille(this.laGrille.getTuiles());
                    this.ihmJeu.getGrille().updateDeplacement(lesJoueurs);
                    this.ihmJeu.afficher();
                    
                    this.nvtour();

                break;
                // Pas commencé le code
                
                case DONNER:                       

                    // Detecte si le joueur peut encore jouer
                    this.actionFinie();                                         
                    if(this.getActions()>0){
                        this.tourDeJeu();
                    }
                    else{
                        this.finTour();
                    }
                break;
                
                
                // Pas commencé le code
                case RECUPERER_TRESOR:   
                    
                    // this.gagnerTresor(tresor);
                    this.actionFinie();
                    
                    // Detecte si le joueur peut encore jouer
                    if(this.getActions()>0){                                   
                        this.tourDeJeu();
                    }
                    else{
                        this.finTour();
                    }
                    
                break;
                 
                
                case FIN_TOUR:                            
                    this.finTour();
                break;
    
    
                case CARTE_MAIN_PLEINE:
                    // idAvent les cartes en haut
                    // idCarte les cartes en qu il a
                    aQuiLeTour().defausserCarte(aQuiLeTour().getCartes().get(m.getIdCarte()));
                    aQuiLeTour().addCarte((CarteMain) PileTresor.getSesCartes().get(m.getIdAventurier()));
                    ((CarteMain)PileTresor.getSesCartes().get(m.getIdAventurier())).changerProprio(aQuiLeTour().getRole());
                    defausseTresor.addPile(PileTresor.getSesCartes().get(m.getIdAventurier()));
                    PileTresor.RemoveCarte(PileTresor.getSesCartes().get(m.getIdAventurier()));   

                break;

            }
        }

        // Regarde si les joueurs peuvent encore gagner
	public boolean victoirePossible() {      
            
            int x=0;
            // Regarde si un aventurier est mort
            for(Aventurier a : this.getJoueurs()){
                if(a.getEtat()==false){                                     
                    return false;
                }
            }

            // Regarde si les deux tuileTresors d'un tresor sont coulées 
            for(Tresors t: this.getTresors()){
                for(TuileTresor tt : t.getSesTuiles()){                                                                                
                    if(tt.getEtat()=="coulé"){                              
                        x++;
                    }
                }

                // Return false si le tresor n'a pas été pris  (pas commencer le code et que ses deux tuiles tresors ont été coulées).
                if(t.getEtat()==false && x==2){                             
                    return false;                                         
                }
            }
            // Regarde si le niveau d'eau est egal a 10
            if(this.getNiveauEau()==10){                                         
                return false;
            }
            // (manque si le heliport est coulé ou non)
            return true;
        }


}
