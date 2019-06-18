package superileinterdite;


import util.Observateur;
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
	private VueInscription ihmInscription;
	private VuePlateau ihmPlateau;
	private VueMenu ihmMenu;
	private ArrayList<Pile> sesPiles ;
	private int niveauEau;
        private int nbAction;
        private int tour;
        private boolean doubleAssechement = false;
        private ArrayList<Tuile> tAssech, tAccess;
        private Pile PileTresor,PileInondation,defausseTresor,defausseInondation;
        

        public Controleur() {
            lesJoueurs = new ArrayList();
            laGrille = new Grille();
            lesTresors = new ArrayList();
            sesPiles = new ArrayList();
        }
        
  
       
        
        public void setIhmVuePlateau(VuePlateau ihmPlateau){
            this.ihmPlateau = ihmPlateau;
        }
        
         public void setIhmVueInscription(VueInscription ihmInscription){
           this.ihmInscription = ihmInscription;
        }
         
        public void setIhmVueMenu(VueMenu ihmMenu){
            this.ihmMenu = ihmMenu;
        }
        

        public void commencerJeu() {                                            //instanciation de la grille de toutes les tuiles et les aventuriers
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
        
        
        
        
       
	public void gagnerTresor(Tresors tresor) {                              //met l'etat du tresor en récupéré
		// TODO - implement Controleur.gagnerTresor
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
                
                

	public void montéeDesEaux() {                                           //augmente le niveau de l'eau
		// TODO - implement Controleur.montéeDesEaux
		this.niveauEau++;
//                this.ihmPlateau.getVueNiveau().setNiveau(niveauEau);  troll
	}
        
        public int nbCarteInonAPiocher(int i){                                  // defini le nombre de carte a piocher en fonction du niveau de l'eau
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
        
        public void remettreAJourAction(){                                      // remet le nombre d'action a 3 au debut d'un tour
            this.nbAction=3;
        }
        
        public void actionFinie(){                                              // enleve une action
            this.nbAction--;
        }
        
        public int getActions(){                                                // revoie le nombre d'action restant
            return nbAction;
        }
        
        public int getTour(){                                                   // revoie le nombre de tour effectué
            return this.tour;
        }
        
        public Aventurier aQuiLeTour(){                                         // permet de savoir le joueur qui doit jouer
            int x=0;
            for(Aventurier a : this.lesJoueurs){
                if(this.getTour()%this.nbJoueurs()==x){
                    return a;
                }
                x=(x+1)%this.nbJoueurs();
            } 
            return this.lesJoueurs.get(x);
        }
        
        public int nbJoueurs(){                                                 // renvoie le nombre de joueur dans la partie
            return this.lesJoueurs.size();
        }
            
        public void nvtour(){                                                   // effectue un nouveau tour 
            Message m = new Message(Utils.Commandes.NOUVEAU_TOUR, 0,0,null,0);
            this.tour++;
            this.traiterMessage(m);
        }
        
        public Grille getGrille(){                                              // renvoie la grille contenant les tuiles 
            return this.laGrille;
        }
        
        public ArrayList<Tresors> getTresors(){                                 // revoie l'ensemble des tresors 
            return this.lesTresors;
        }
        
        public int getNiveauEau(){                                              // revoie le niveau de l'eau 
            return this.niveauEau;
        }
        
        public void setNiveauEau(int nveau){                                    //instancie le niveau de l'eau
            this.niveauEau=nveau;
        }
        
        public ArrayList<Aventurier> getJoueurs(){                              // renvoie la liste de joueur jouant
            return this.lesJoueurs;
        }
        
        public ArrayList<Pile> getPiles(){                                      // renvoie l'ensemble des piles du jeu
            return this.sesPiles;
        }

        public Pile getPileTresor() {
            return PileTresor;
        }

        public Pile getPileInondation() {
            return PileInondation;
        }

        
        
       
        
        public void tourDeJeu(){                                                // renvoie toutes les actions possibles en fonction des multiples parametres du joueur(tuile,carte, role ect..)
            ArrayList<Utils.Commandes> tm = new ArrayList<Utils.Commandes>(); //Actions possibles
            int i=0;
            ihmPlateau.updateActions(getActions());
            
            if(!this.aQuiLeTour().getCartes().isEmpty()){                       // regarde si le joueur peut se deplacer
                tm.add(Utils.Commandes.DONNER);
            }


            for(CarteMain c : this.aQuiLeTour().getCartes()){                   // regarde si le joueur peut gagner un trésor
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
            
            
            //test on met toute la grille en assechable
            tAssech = laGrille.getTuiles();                                     //regarde si le joueur peut assecher
            if (tAssech.size() > 0) {
                System.out.println(tAssech.size());
            tm.add(Utils.Commandes.ASSECHER);  
            }

           for(Aventurier a : this.getJoueurs()){                               // regarde si le joueur peut donner une carte tresor
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
           
           
           
           if(this.aQuiLeTour().getRole()=="plongeur"){                         // regarde si le joueur peut se deplacer en fonction de son role
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

           if(this.aQuiLeTour().getRole()=="navigateur"){                       // regarde si le navigateur peut se déplacer
               for(Aventurier a : this.getJoueurs()){
                   if(!a.TuilesAccessibles(this.getGrille()).isEmpty()){
                   tm.add(Utils.Commandes.DEPLACER_AUTRE);
                   break;
                   }
               }
           }

           tm.add(Utils.Commandes.FIN_TOUR);
        }
                  
        
        
        public void finTour(){                                                  // effectue toutes les actions nécessaires apres qu'un joueur ai fini ses actions                                                 //
           //piocher carte tresor
           this.piocherTresor(aQuiLeTour());
           
           ihmPlateau.getGrille().resetGrille();
           
           if (this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==false) {
               ((Pilote) this.aQuiLeTour()).resetHelico();
           }
                   
           int i=0;
          while(i<this.nbCarteInonAPiocher(this.getNiveauEau())){
//              this.aQuiLeTour().piocherCarteInon().getTuile().inonderTuile();
              i++;
          }
//            ihm.changerpov();
            if(this.victoirePossible()==true){                                  // detecte so la victoire est encore possible
                this.nvtour();
            }
            else{
                ihmPlateau.afficherDefaite();
            }
        }
        
        
	public void traiterMessage(Message m) {
		// TODO - implement Controleur.traiterMessage
            switch (m.getCommande()) {
                case CHOISIR_TUILE_DEPLACER:
                     //this.nbAction = this.nbAction-this.aQuiLeTour().TuilesAccessibles(this.getGrille()).get(this.getGrille().getTuiles().get(m.getNumTuile()));
                     this.actionFinie();
                     
                     //enleve l'aventurier da la tuile ou il etait
                     this.aQuiLeTour().getTuile().removeAventurier(this.aQuiLeTour());   
                     //change la tuile de l'aventurier
                     this.aQuiLeTour().updateTuile(this.getGrille().getTuiles().get(m.getIdTuile()));
                     // met un aventurier sur la nouvelle tuile
                     this.getGrille().getTuiles().get(m.getIdTuile()).addAventurier(this.aQuiLeTour());
                    
                     
                     
                     ihmPlateau.getGrille().afficherTuilesDeplacer(tAccess);
                     ihmPlateau.getGrille().updateDeplacement();
                     
                     if (this.aQuiLeTour().getTuile().getEtat() == "coulé") {   //si un plongeur est sur une case soulé il ne peut pas finir le tour
                         ihmPlateau.impossibleFinTour();
                     } else {
                         ihmPlateau.possibleFinTour();
                     }
                     
                    //if((this.aQuiLeTour()).getRole()=="pilote"){
                    //    this.actionFinie(); // pas encore fini ca fait -1 dans tout les cas
                    //}
                    
                    if(this.getActions()>0){                                    // detecte si le joueur peut encore jouer
                         this.tourDeJeu();
                     }
                     else{
                         this.finTour();
                     }
                    break;
                 
                    
                    
                    
                case SE_DEPLACER:
                    if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==true) {

                        //on affiche déplacement normal

                        //on affiche le déplacement hélicoptère

                        tAccess = (((Pilote) this.aQuiLeTour()).deplacementHelico(this.getGrille()));
                    }
                    ihmPlateau.getGrille().afficherTuilesDeplacer(tAccess);
                    break;
                    
                    
                    
                  
                case DEPLACER_AUTRE:
                     
                    this.actionFinie(); 
                    if(this.getActions()>0){                                    // detecte si le joueur peut encore jouer
                         this.tourDeJeu();
                     }
                     else{
                         this.finTour();
                     }
                    break;
                  
                    
                    
                    
                case ASSECHER: 
                   //ihm.afficherTuilesAssecher(this.aQuiLeTour().TuilesAssechables(this.getGrille()));
                   ihmPlateau.getGrille().afficherTuilesAssecher(tAssech);
                   break;
                   
                   
                   
                
                case CHOISIR_TUILE_ASSECHER:
                     this.getGrille().getTuiles().get(m.getIdTuile()).assecherTuile(); 
//                     ihm.afficherTuilesAssecher(tAssech);

                     if(this.aQuiLeTour().getRole()=="ingenieur" && !doubleAssechement){            //gere le double assechement d'un ingenieur
                         doubleAssechement=true;
                         this.actionFinie();
                     } 
                     else if(this.aQuiLeTour().getRole()=="ingenieur" && doubleAssechement){
                         doubleAssechement=false;
                     } else {
                         this.actionFinie();
                     }
                    ihmPlateau.updateActions(this.getActions());
                     
                     if(this.getActions()>0){                                   // detecte si le joueur peut encore jouer
                         this.tourDeJeu();
                     } 
                     else{
                         this.finTour();
                     }
                     break;
                     
                 
                     
                     
                 // a jeter
                case COMMENCER_JEU:
                     ihmMenu.cacher();
                     this.setIhmVuePlateau(new VuePlateau());
                     
                     this.commencerJeu();
                     
                     System.out.println(m.getIdAventurier()); //nombre joueurs
                     
                     ArrayList<Aventurier> a = new ArrayList<>();               //créer la liste de joueur en fonction du nombre de joueur choisi
                     for(int i=0; i<m.getIdAventurier();i++){
                         a.add(this.lesJoueurs.get(i));
                     }
                     
                  //   for(Aventurier av : a){
                      //   VueAventurier va = new VueAventurier(av.getId());
                     //    this.ihmAventuriers.add(va);
                   //  }
                     
                     //Toute la grille est inondée
                     for (Tuile tuile : laGrille.getTuiles()) {
                         tuile.inonderTuile();
                     }
                     //tuile 10 coulé pour tester
                     laGrille.getTuiles().get(10).inonderTuile();
                     
                     
                     this.lesJoueurs=a;
                     
                     this.piocherTresorDebut();
                     
                     this.ihmPlateau.getGrille().intitialiserGrille(this.laGrille.getTuiles());
                     this.nvtour();
                   
                     break;
                     
                     
                     
                 
                case DONNER:                     //pas commencé le code
                     
                    this.actionFinie();                                         // detecte si le joueur peut encore jouer
                     if(this.getActions()>0){
                         this.tourDeJeu();
                     }
                     else{
                         this.finTour();
                     }
                     break;
                     
                case RECUPERER_TRESOR:                     //pas commencé le code
                   //  this.gagnerTresor(tresor);
                     this.actionFinie();
                     if(this.getActions()>0){                                   // detecte si le joueur peut encore jouer
                         this.tourDeJeu();
                     }
                     else{
                         this.finTour();
                     }
                     break;
                
                 
                     
                     
                     
                case FIN_TOUR:                        //fini le tour 
                     this.finTour();                  
                break;
                 
                
                
                
                case NOUVEAU_TOUR:                      //effectue un nouveau tour
                     System.out.println(this.aQuiLeTour().getRole());
                     this.remettreAJourAction();
                     this.tourDeJeu();
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

        
        
        
        
        
	public boolean victoirePossible() {                                        //regarde si les joueurs peuvent encore gagner
		// TODO - implement Controleur.victoirePossible
                int x=0;
		for(Aventurier a : this.getJoueurs()){
                    if(a.getEtat()==false){                                     //regarde si un aventurier est mort
                        return false;
                    }
                }
                for(Tresors t: this.getTresors()){
                    for(TuileTresor tt : t.getSesTuiles()){                     //regarde si le deux tuilestresors d'un tresor                                                               
                        if(tt.getEtat()=="coulé"){                              // sont coulé
                            x++;
                        }
                    }
                    if(t.getEtat()==false && x==2){                             //return false si le tresor n'a pas été pris 
                        return false;                                         //pas commencer le code  // et que ses deux tuiles tresors ont été coulées.
                    }
                }
                if(this.getNiveauEau()==10){                                         //regarde si le niveau d'eau est egal a 10
                    return false;
                }
                                                                                //manque si le heliport  est coulé ou non
                return true;
        }


}
