

package superileinterdite;


import model.*;
import aventuriers.*;
import java.awt.Color;
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
        private MessageBox test;
        

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
            test = new MessageBox();
            test.displayMessage("<h1 style=\"text-align:center;\">Bienvenue dans<br>l'Île Interdite</h1>", Color.black, false, false);

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
            laGrille.addTuile(new Tuile("Le Val Du Crepuscule"));
            laGrille.addTuile(new Tuile("Le Marais Brumeux"));
            laGrille.addTuile(new Tuile("Le Pont Des Abimes"));
            laGrille.addTuile(new Tuile("Les Dunes De LIllusion"));
            laGrille.addTuile(new Tuile("Les Falaises De LOubli"));
            laGrille.addTuile(new Tuile("Observatoire"));
            laGrille.addTuile(new Tuile("Le Rocher Fantome"));
            laGrille.addTuile(new Tuile("La Foret Pourpre"));
            laGrille.addTuile(new Tuile("Le Lagon Perdu"));
            laGrille.addTuile(new Tuile("La Tour Du Guet"));
            // Creation des Tuiles depart
            laGrille.addTuile(new TuilePion("Heliport",pilote));
            laGrille.addTuile(new TuilePion("La Porte De Bronze",ingenieur));
            laGrille.addTuile(new TuilePion("La Porte De Fer",plongeur));
            laGrille.addTuile(new TuilePion("La Porte DOr",navigateur));
            laGrille.addTuile(new TuilePion("La Porte DArgent",messager));
            laGrille.addTuile(new TuilePion("La Porte De Cuivre",explorateur));
            // Creation des trésors
            Tresors Pierre = new Tresors("La Pierre sacrée");
            Tresors Statue = new Tresors("La Statue du zéphyr");            
            Tresors Cristal = new Tresors("Le Cristal ardent");            
            Tresors Calice = new Tresors("Le Calice de l’onde");   
            // Creation des tuilesTresors
            laGrille.addTuile(new TuileTresor("La Caverne Du Brasier",Cristal));
            laGrille.addTuile(new TuileTresor("La Caverne Des Ombres",Cristal));
            laGrille.addTuile(new TuileTresor("Le Jardin Des Hurlements",Statue));
            laGrille.addTuile(new TuileTresor("Le Jardin Des Murmures",Statue));
            laGrille.addTuile(new TuileTresor("Le Temple Du Soleil",Pierre));
            laGrille.addTuile(new TuileTresor("Le Temple De La Lune",Pierre));
            laGrille.addTuile(new TuileTresor("Le Palais De Corail",Calice));
            laGrille.addTuile(new TuileTresor("Le Palais Des Marees",Calice));
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
                switch (tresor.getNom()) {
                    case "La Statue du zéphyr":
                        test.setZephyrVisible();
                        break;
                    
                    case "La Pierre sacrée":
                        test.setPierreVisible();

                        break;
                    
                    case "Le Cristal ardent":
                        test.setCristalVisible();
  
                        break;
                    
                    case "Le Calice de l’onde":
                        test.setCaliceVisible();

                        break;
                        
                }                   
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
                if (PileTresor.getSesCartes().isEmpty()) {
                    defausseTresor.randomizePile();
                    PileTresor.setSesCartes(defausseTresor.getSesCartes());
                    defausseTresor.ViderPile();
                    i--;
              
                } else if (((CarteMain)PileTresor.getSesCartes().get(0)).getNom() == "MDeaux") {

                    if (i == 0 && ((CarteMain)PileTresor.getSesCartes().get(1)).getNom() == "MDeaux") {
                         i++;
                        this.montéeDesEaux();
                        // Mettre dans la défausse
                        defausseTresor.addPile(PileTresor.getSesCartes().get(1));
                        PileTresor.RemoveCarte(PileTresor.getSesCartes().get(1));   
                    }
                    this.montéeDesEaux();
                    defausseInondation.randomizePile();
                    ArrayList<Carte> temp = new ArrayList<Carte>();
                    temp = defausseInondation.getSesCartes();
                    for (Carte c : PileInondation.getSesCartes()) {
                        temp.add(c);                      
                    }
                    PileInondation.ViderPile();                    

                    PileInondation.setSesCartes(temp);
                    defausseInondation.ViderPile();
            
                    // Mettre dans la défausse
                    defausseTresor.addPile(PileTresor.getSesCartes().get(0));
                    PileTresor.RemoveCarte(PileTresor.getSesCartes().get(0));             
                         
                    
                } else if (a.getCartes().size() == 5) {                 
                    if (i == 0 && PileTresor.getSesCartes().size() >= 2) {
                        montrerCartes.add(PileTresor.getSesCartes().get(i+1));  
                        montrerCartes.add(PileTresor.getSesCartes().get(i));     
                    } else {
                        montrerCartes.add(PileTresor.getSesCartes().get(0));   
                    }                  
                    break;
                    
                } else {
                    ((CarteMain)PileTresor.getSesCartes().get(0)).changerProprio(a.getRole());
                    a.addCarte((CarteMain) PileTresor.getSesCartes().get(0));
                    PileTresor.RemoveCarte(PileTresor.getSesCartes().get(0));             
                }         
            }
            
            if (montrerCartes.size() > 0) {
                // Envoyer l arraylist a l ihm
            }
            
	}
        
        
        public void piocherInondation(){
            for (int i = 0; i < nbCarteInonAPiocher(); i++) {
                if (!PileInondation.getSesCartes().isEmpty()) {
                    defausseInondation.addPile(PileInondation.getSesCartes().get(0));
                    ((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().inonderTuile();     
                    test.displayMessage("La tuile "+PileInondation.getSesCartes().get(0).getNom()+ " est "+((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().getEtat(), Color.blue, false, false);
                    PileInondation.RemoveCarte(PileInondation.getSesCartes().get(0));    
               }         
            }
            //mettre a jours l'affichage des tuilles
        }
        
        public void piocherInondationDebut(){
            for (int i = 0; i <= 5; i++) {
//                if (!PileInondation.getSesCartes().isEmpty()) {
                    defausseInondation.addPile(PileInondation.getSesCartes().get(0));
                    ((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().inonderTuile();
                    test.displayMessage("La tuile "+PileInondation.getSesCartes().get(0).getNom()+ " est "+((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().getEtat(), Color.blue, false, false);
                    PileInondation.RemoveCarte(PileInondation.getSesCartes().get(0));                 
//                }         
            }
            //mettre a jours l'affichage des tuilles
        }
                
                

        // Augmente le niveau de l'eau
	public void montéeDesEaux() {                                           
            this.niveauEau++;
            this.ihmJeu.getVueNiveau().setNiveau(niveauEau); 
	}
        
        // Definit le nombre de carte à piocher en fonction du niveau de l'eau
        public int nbCarteInonAPiocher(){                                  
            if(niveauEau<3){
                return 2;
            }
            else if(3<=niveauEau && niveauEau<6){
                return 3;
            }
            else if(6<=niveauEau && niveauEau<8){
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
            test.displayMessage("<h2>C'est à " + aQuiLeTour().getRole() + " de jouer.</h2>", Color.black, true, false);
            switch(this.aQuiLeTour().getRole()){
                case "explorateur":
                    test.displayMessage("<h3>Action spéciale :</h3> se déplacer et assecher en diagonale.<br><br>", Color.black, false, false);
                    break;
                
                case "messager":
                    test.displayMessage("<h3>Action spéciale :</h3> peut donner une carte depuis n'importe quelle case.<br><br>", Color.black, false, false);
                    break;
                    
                case "ingenieur":
                    test.displayMessage("<h3>Action spéciale :</h3> peut assecher deux case pour une action.<br><br>", Color.black, false, false);
                    break;
                    
                case "pilote":
                    test.displayMessage("<h3>Action spéciale :</h3> peut se déplacer où il veut sur la carte une fois.<br><br>", Color.black, false, false);
                    break;
                    
                case "plongeur":
                    test.displayMessage("<h3>Action spéciale :</h3> peut se deplacer sur une case inondé ou coulé sans dépenser d'action.<br><br>", Color.black, false, false);
                    break;
                    
                case "navigateur":
                    test.displayMessage("<h3>Action spéciale :</h3> peut déplacer jusqu'à deux cases n'importe quel joueur pour une action.<br><br>", Color.black, false, false);
                    break;
            }
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

            // Regarde si le joueur peut gagner un trésor
            if(this.aQuiLeTour().getTuile() instanceof TuileTresor) {
                
            
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
            }
            
            tAssech = new ArrayList<>();
            
            tAssech = this.aQuiLeTour().TuilesAssechables(this.getGrille());
                             System.out.println(tAssech.size());                   
            if (tAssech.size()>1) {
                tm.add(Utils.Commandes.ASSECHER);  
            }

            // Regarde si le joueur peut donner une carte tresor
            
            if(!this.aQuiLeTour().getCartes().isEmpty()){                       
                for(Aventurier a : this.getJoueurs()){                               
                    if(a!=this.aQuiLeTour()){  
                        if(a.getCartes().size()<5  && this.aQuiLeTour().getTuile()==a.getTuile()){
                            tm.add(Utils.Commandes.DONNER);
                            break;
                        }
                        else if(a.getCartes().size()<5  && this.aQuiLeTour().getRole()=="messager"){
                            tm.add(Utils.Commandes.DONNER);
                            break;
                        }
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
                   if(!a.TuilesAccessibles(this.getGrille()).isEmpty() && this.aQuiLeTour() != a){
                   tm.add(Utils.Commandes.DEPLACER_AUTRE);
                   break;
                   }
                }
            }

            tm.add(Utils.Commandes.FIN_TOUR);
            //envoyer a l'ihm les actions possibles
            test.displayMessage("Voici vos actions possibles", Color.black, false, false);
            String actions = new String();
            for(Utils.Commandes u : tm){
                actions = actions + "<li>" + u.toString() + "</li>";
            }
            test.displayMessage("<ul>" + actions + "</ul>", Color.black, false, false);
        }
        
                  
        
        

        // Effectue toutes les actions nécessaires apres qu'un joueur ai fini ses actions  
        public void finTour(){                                                                                                 
            // Actualiser l'ihm   
            this.piocherTresor(aQuiLeTour());
            
            if (this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==false) {
               ((Pilote) this.aQuiLeTour()).resetHelico();
            }
            piocherInondation();
            // Detecte si la victoire est encore possible
            if (this.victoirePossible()==true) {                                  
                this.nvtour();
            } else {
                ihmJeu.afficherDefaite();
               test.displayMessage("<h1 style=\"text-align:center;\">Vous avez <br>Perdu</h1>", Color.red, true, false);
               test.displayMessage("", Color.black, true, false);

            }
            ihmJeu.finTour();
        }
        
      
        @Override
	public void traiterMessage(Message m) {
            switch (m.getCommande()) {
                case CHOISIR_TUILE:
                if(modeDeplacement){
                    // Enleve l'aventurier da la tuile ou il etait
                    this.aQuiLeTour().getTuile().removeAventurier(this.aQuiLeTour());
                    // Change la tuile de l'aventurier
                    this.aQuiLeTour().updateTuile(this.getGrille().getTuiles().get(m.getIdTuile()));
                    // Met un aventurier sur la nouvelle tuile
                    this.getGrille().getTuiles().get(m.getIdTuile()).addAventurier(this.aQuiLeTour());
                    ihmJeu.getGrille().updateDeplacement(lesJoueurs);

                    // Retire une action si les joueurs se déplacent sur une
                    if (!(this.aQuiLeTour().getRole() == "plongeur" && this.aQuiLeTour().getTuile().getEtat() != "seche")) {
                        this.actionFinie();
                    }

                    tAccess = this.aQuiLeTour().TuilesAccessibles(laGrille);
                    ihmJeu.getGrille().afficherTuilesDeplacer(tAccess);

                    // Si un plongeur est sur une case coulée il ne peut pas finir le tour
                    if (this.aQuiLeTour().getTuile().getEtat() == "coulé") {
                        ihmJeu.impossibleFinTour();

                        // S'il ne lui reste qu'une action, le bouton assécher est également désactivé
                        if (this.getActions() == 1) {
                            ihmJeu.impossibleAssecher();
                        }
                    } else {
                        ihmJeu.possibleFinTour();
                        ihmJeu.possibleAssecher();
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

                } else if (modeAssechement)  {

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
                        
                        if (this.getActions() == 1 && this.aQuiLeTour().getTuile().getEtat() == "coulé" ) {
                            modeDeplacement = false;
                            modeAssechement = false;
                            ihmJeu.impossibleAssecher();
                            ihmJeu.getGrille().resetGrille();
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
                    
                    // Si le plongeur a une action restante et est sur une tuile coulée, le bouton assécher se désactive
                    if (this.aQuiLeTour().getRole() == "plongeur" && this.getActions() == 1 && this.aQuiLeTour().getTuile().getEtat() == "coulé" ) {
                        ihmJeu.impossibleAssecher();
                    }
                      
                break;
                  
                case DEPLACER_AUTRE:
                    
                    for (Aventurier a : lesJoueurs) {
                        if (a.getId() == m.getIdAventurier()) {
                           tAccess = ((Navigateur)aQuiLeTour()).deplacerAutreJoueur(a,laGrille);
                        }  
                    }
                    
                    ihmJeu.getGrille().afficherTuilesDeplacer(tAccess);
                    
                    // Voir deplacement apres
                    
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

                    piocherInondationDebut();
                    piocherTresorDebut();
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
                
                
                case DEFAUSSER_CARTE:
                    
                    this.aQuiLeTour().getCartes().get(m.getIdCarte()).changerProprio(null);
                    defausseTresor.addPile(this.aQuiLeTour().getCartes().get(m.getIdCarte()));
                    this.aQuiLeTour().getCartes().remove(m.getIdCarte());
                    //mettre a jour ses cartes
                break;
                
                
                case ACTION_SPECIALE:
                    tAssech.removeAll(tAssech);
                    tAccess.removeAll(tAccess);
                    for (Aventurier a : lesJoueurs) {
                        if (a.getId() == m.getIdAventurier()) {
                            if (a.getCartes().get(m.getIdCarte()).getNom() == "Sac") {
                                for (Tuile t : getGrille().getTuiles()) {
                                    if (t.getEtat() == "inondé") {
                                        tAssech.add(t);
                                    }
                                }
                                modeAssechement = true;
                                modeDeplacement = false;
                                ihmJeu.getGrille().afficherTuilesAssecher(tAssech);
                            } else {
                                ArrayList<Tuile> temp = new ArrayList<Tuile>();
                                for(Tuile t : this.laGrille.getTuiles()){
                                    if(!t.getSesAventuriers().isEmpty()) {
                                        temp.add(t);
                                    }
                                }
                                for (Tuile t : laGrille.getTuiles()) {
                                    tAccess.add(t);
                                }
                                modeAssechement = false;
                                modeDeplacement = true;
                                //fonction 0 deplacement helico
                                
                            }
                        }  
                    }
                    
                    
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
            if(this.getNiveauEau()>=10){                                         
                return false;
            }
            // (manque si le heliport est coulé ou non)
            return true;
        }


}
