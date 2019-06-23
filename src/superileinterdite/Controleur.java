

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
        private VueMenu ihmMenu;
	private VueJeu ihmJeu;
	private ArrayList<Pile> sesPiles ;
	private int niveauEau;
        private int nbAction;
        private int tour;
        private int cardOwnerId, cardUsedId;
        private int tuileInitialeId;
        private boolean doubleAssechement = false, modeDeplacement = false, modeAssechement = false, modeDefausser = false, 
                modeDonner = false, modeDeplacerAutre = false, modeActionSpeciale = false, modeChoixHelicoDestination = false;
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
            lesTresors.add(Pierre);
            lesTresors.add(Statue);
            lesTresors.add(Cristal);
            lesTresors.add(Calice);
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
            this.tour=-1;
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
                        ihmJeu.getMessageBox().setZephyrVisible();
                        break;
                    
                    case "La Pierre sacrée":
                        ihmJeu.getMessageBox().setPierreVisible();

                        break;
                    
                    case "Le Cristal ardent":
                        ihmJeu.getMessageBox().setCristalVisible();
  
                        break;
                    
                    case "Le Calice de l’onde":
                        ihmJeu.getMessageBox().setCaliceVisible();

                        break;
                        
                }                   
	}
        
	public void piocherTresorDebut() {     
            for (Aventurier a : getJoueurs()) {
                for (int i = 0; i <= 1; i++) {
                    if ( ((CarteMain)PileTresor.getSesCartes().get(0)).getNom() == "MDeaux") {
                        i--;
                        PileTresor.randomizePile();
                    } else {
                        ((CarteMain)PileTresor.getSesCartes().get(0)).changerProprio(a.getRole());
                        a.addCarte((CarteMain) PileTresor.getSesCartes().get(0));
                        PileTresor.RemoveCarte(PileTresor.getSesCartes().get(0));   
                    }

               }
            ihmJeu.updateCards(a.getId(), a.getCartes());

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
            ihmJeu.updateCards(a.getId(), a.getCartes());
           
            if (montrerCartes.size() > 0) {
                // Envoyer l arraylist a l ihm
                
            }
            
	}
        
        
        public void piocherInondation(){
            for (int i = 0; i < nbCarteInonAPiocher(); i++) {
                if (!PileInondation.getSesCartes().isEmpty()) {
                    defausseInondation.addPile(PileInondation.getSesCartes().get(0));
                    ((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().inonderTuile();     
                    ihmJeu.getMessageBox().displayMessage("La tuile "+PileInondation.getSesCartes().get(0).getNom()+ " est "+((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().getEtat(), Color.blue, false, true);
                    PileInondation.RemoveCarte(PileInondation.getSesCartes().get(0));    
               }         
            }
            //mettre à jour l'affichage des tuilles
        }
        
        public void piocherInondationDebut(){
            for (int i = 0; i <= 5; i++) {
//                if (!PileInondation.getSesCartes().isEmpty()) {
                    defausseInondation.addPile(PileInondation.getSesCartes().get(0));
                    ((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().inonderTuile();
                    ihmJeu.getMessageBox().displayMessage("La tuile "+PileInondation.getSesCartes().get(0).getNom()+ " est "+((CarteInondation)PileInondation.getSesCartes().get(0)).getTuile().getEtat(), Color.blue, false, true);
                    PileInondation.RemoveCarte(PileInondation.getSesCartes().get(0));                 
//                }         
            }
            //mettre à jour l'affichage des tuilles
        }
        
        
        public void donnerCarte(int joueurReceveur, int carteDonne){

                for(Aventurier aventurier : this.lesJoueurs){
                    if(aventurier.getId()==joueurReceveur){
                        
                        aventurier.addCarte(aQuiLeTour().getCartes().get(carteDonne));
                        aQuiLeTour().getCartes().get(carteDonne).changerProprio(aventurier.getRole());
                        aQuiLeTour().defausserCarte(aQuiLeTour().getCartes().get(carteDonne));
                       
                }
            }
        }
            
        public void enleverRecup(Tresors t){
            for (int i = 0; i < this.aQuiLeTour().getCartes().size(); i++) {             
                if(this.aQuiLeTour().getCartes().get(i) instanceof CarteTresors){
                    if(((CarteTresors) this.aQuiLeTour().getCartes().get(i)).getTresor() == t){
                        this.aQuiLeTour().getCartes().get(i).changerProprio(null);
                        this.defausseTresor.addPile(this.aQuiLeTour().getCartes().get(i));
                        this.aQuiLeTour().defausserCarte(this.aQuiLeTour().getCartes().get(i));
                        i--;
                    }
                }
            }
            ihmJeu.updateCards(this.aQuiLeTour().getId(), this.aQuiLeTour().getCartes());
        }
                
                

        // Augmente le niveau de l'eau
	public void montéeDesEaux() {                                           
            this.niveauEau++;
            this.ihmJeu.getVueNiveau().setNiveau(niveauEau); 
            this.ihmJeu.getMessageBox().displayMessage("Vous avez pioché une carte montée des eaux", Color.red, false, true);
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
            ihmJeu.afficherJoueurCourant(this.aQuiLeTour().getId());

            this.remettreAJourAction();            
            
            if(this.aQuiLeTour().getRole().equals("pilote")) {
                this.ihmJeu.activerHelico();
            } else {
                this.ihmJeu.desactiverHelico();
            }
            
            
            ihmJeu.getMessageBox().displayMessage("<h2>C'est à " + aQuiLeTour().getRole() + " de jouer.</h2>", Color.black, true, false);
            
            switch(this.aQuiLeTour().getRole()){
                case "explorateur":
                    ihmJeu.getMessageBox().displayMessage("<h3>Action spéciale :</h3> se déplacer et assecher en diagonale.<br><br>", Color.black, false, false);
                    break;
                
                case "messager":
                    ihmJeu.getMessageBox().displayMessage("<h3>Action spéciale :</h3> peut donner une carte depuis n'importe quelle case.<br><br>", Color.black, false, false);
                    break;
                    
                case "ingenieur":
                    ihmJeu.getMessageBox().displayMessage("<h3>Action spéciale :</h3> peut assecher deux case pour une action.<br><br>", Color.black, false, false);
                    break;
                    
                case "pilote":
                    ihmJeu.getMessageBox().displayMessage("<h3>Action spéciale :</h3> peut se déplacer où il veut sur la carte une fois.<br><br>", Color.black, false, false);
                    break;
                    
                case "plongeur":
                    ihmJeu.getMessageBox().displayMessage("<h3>Action spéciale :</h3> peut se deplacer sur une case inondé ou coulé sans dépenser d'action.<br><br>", Color.black, false, false);
                    break;
                    
                case "navigateur":
                    ihmJeu.getMessageBox().displayMessage("<h3>Action spéciale :</h3> peut déplacer jusqu'à deux cases n'importe quel joueur pour une action.<br><br>", Color.black, false, false);
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
            this.ihmJeu.getVueNiveau().setNiveau(niveauEau); 
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
        
        public boolean cestgagne(){
            int i=0;
            boolean b=false;
            for(Aventurier a : this.lesJoueurs){
                if(a.getTuile().getNom()=="Heliport"){
                    i++;
                }
                for(CarteMain c : a.getCartes()){
                    if(c.getNom()=="Heli"){
                    b=true;
                    }
                }
            }
            for(Tresors t : this.lesTresors){
                if(t.getEtat()){
                    i++;
                }
            }
            if(i==(lesJoueurs.size()+lesTresors.size()) && b==true){
                return true;
            }
            return false;
        }

        
        public void AfficherActionsPossibles() {
            int i=0;
            ihmJeu.updateActions(getActions());

            ihmJeu.impossibleDeplacer();
            ihmJeu.impossibleAssecher();
            ihmJeu.impossibleDeplacerAutre();
            ihmJeu.impossibleGiveCarte();
            ihmJeu.impossibleRecupererTresor();
            ihmJeu.impossibleDefausser();
            ihmJeu.impossibleActionSpeciale();
            
            // Regarde si le joueur peut gagner un trésor
            if(this.aQuiLeTour().getTuile() instanceof TuileTresor) {
                
                for(CarteMain c : this.aQuiLeTour().getCartes()){                   
                    for(CarteMain a : this.aQuiLeTour().getCartes()){
                       if(a instanceof CarteTresors && c instanceof CarteTresors){

                       if(((CarteTresors) c).getTresor().equals(((CarteTresors) a).getTresor())){
                        i++;
                       }
                       if(i==4 && ((TuileTresor) this.aQuiLeTour().getTuile()).getTresor()==((CarteTresors) c).getTresor()){
                           ihmJeu.possibleRecupererTresor();
                           break;
                       }
                     }
                   }
                   i=0;
                }
            }
            
            tAssech = new ArrayList<>();
            tAssech = this.aQuiLeTour().TuilesAssechables(this.getGrille());

            if (tAssech.size()>0) {

                ihmJeu.possibleAssecher();
            }
            
            // Regarde si le joueur peut donner une carte tresor
            
            if(!this.aQuiLeTour().getCartes().isEmpty()){ 
                for(Aventurier a : this.getJoueurs()){                               
                    if(a!=this.aQuiLeTour()){  
                        if(a.getCartes().size()<5  && this.aQuiLeTour().getTuile()==a.getTuile()){
                            ihmJeu.possibleGiveCarte();
                            break;
                        }
                        else if(a.getCartes().size()<5  && this.aQuiLeTour().getRole()=="messager"){
                            ihmJeu.possibleGiveCarte();
                            break;
                        }
                   }
                }       
           }   
            
            for (Aventurier aventurier : this.lesJoueurs) {
                if(!aventurier.getCartes().isEmpty()) {
                    ihmJeu.possibleDefausser();
                }
                for (Carte carte : aventurier.getCartes()) {
                    if (carte instanceof CarteActionSpeciale) {
                        ihmJeu.possibleActionSpeciale();
                    }
                }
            }
            
           tAccess = new ArrayList<>();
           tAccess = this.aQuiLeTour().TuilesAccessibles(laGrille);
           
            // Regarde si le joueur peut se deplacer en fonction de son rôle
            if(this.aQuiLeTour().getRole()=="plongeur"){                         
                ihmJeu.possibleDeplacer();
            }
            else if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==true){
                ihmJeu.possibleDeplacer();
            }
            else if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==false && tAccess.size()>0){
                ihmJeu.possibleDeplacer();
            }
            else if(tAccess.size()>0){
                ihmJeu.possibleDeplacer();
            }

            // Regarde si le navigateur peut se déplacer
            if(this.aQuiLeTour().getRole()=="navigateur"){                       
               for(Aventurier a : this.getJoueurs()){
                   if(!a.TuilesAccessibles(this.getGrille()).isEmpty() && this.aQuiLeTour() != a){
                   ihmJeu.possibleDeplacerAutre();
                   break;
                   }
                }
            }
        }
       
        // Renvoie toutes les actions possibles en fonction des multiples parametres du joueur(tuile,carte, role ect..)
        public void tourDeJeu(){                       
            // Actions possibles
            ArrayList<Utils.Commandes> tm = new ArrayList<Utils.Commandes>(); 
            int i=0;
            ihmJeu.updateActions(getActions());
            ihmJeu.impossibleDeplacer();
            ihmJeu.impossibleAssecher();
            ihmJeu.impossibleDeplacerAutre();
            ihmJeu.impossibleGiveCarte();
            ihmJeu.impossibleRecupererTresor();
            ihmJeu.impossibleDefausser();
            ihmJeu.impossibleActionSpeciale();
            
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
                           ihmJeu.possibleRecupererTresor();
                           break;
                       }
                     }
                   }
                    if (tm.contains(Utils.Commandes.RECUPERER_TRESOR)) {
                        break;
                    }
                   i=0;
                }
            }
            
            for (Aventurier aventurier : this.lesJoueurs) {
                if(!aventurier.getCartes().isEmpty()) {
                    ihmJeu.possibleDefausser();
                }
                for (Carte carte : aventurier.getCartes()) {
                    if (carte instanceof CarteActionSpeciale) {
                        ihmJeu.possibleActionSpeciale();
                    }
                }
            }
            
            tAssech = new ArrayList<>();
            tAssech = this.aQuiLeTour().TuilesAssechables(this.getGrille());

            if (tAssech.size()>0) {

                tm.add(Utils.Commandes.ASSECHER);  
                ihmJeu.possibleAssecher();
            }
            
            // Regarde si le joueur peut donner une carte tresor
            
            if(!this.aQuiLeTour().getCartes().isEmpty()){                       
                for(Aventurier a : this.getJoueurs()){                               
                    if(a!=this.aQuiLeTour()){  
                        if(a.getCartes().size()<5  && this.aQuiLeTour().getTuile()==a.getTuile()){
                            tm.add(Utils.Commandes.DONNER);
                            ihmJeu.possibleGiveCarte();
                            break;
                        }
                        else if(a.getCartes().size()<5  && this.aQuiLeTour().getRole()=="messager"){
                            ihmJeu.possibleGiveCarte();
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
                ihmJeu.possibleDeplacer();
            }
            else if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==true){
                ihmJeu.possibleDeplacer();
                tm.add(Utils.Commandes.SE_DEPLACER);
            }
            else if(this.aQuiLeTour().getRole()=="pilote" && ((Pilote) this.aQuiLeTour()).getHelico()==false && tAccess.size()>0){
                ihmJeu.possibleDeplacer();
                tm.add(Utils.Commandes.SE_DEPLACER);
            }
            else if(tAccess.size()>0){
                ihmJeu.possibleDeplacer();
                tm.add(Utils.Commandes.SE_DEPLACER);
            }

            // Regarde si le navigateur peut se déplacer
            if(this.aQuiLeTour().getRole()=="navigateur"){                       
               for(Aventurier a : this.getJoueurs()){
                   if(!a.TuilesAccessibles(this.getGrille()).isEmpty() && this.aQuiLeTour() != a){
                   tm.add(Utils.Commandes.DEPLACER_AUTRE);
                   ihmJeu.possibleDeplacerAutre();
                   break;
                   }
                }
            }

            tm.add(Utils.Commandes.FIN_TOUR);
            //envoyer a l'ihm les actions possibles
            ihmJeu.getMessageBox().displayMessage("Voici vos actions possibles", Color.black, false, false);
            String actions = new String();
            for(Utils.Commandes u : tm){
                actions = actions + "<li>" + u.toString() + "</li>";
            }
            ihmJeu.getMessageBox().displayMessage("<ul>" + actions + "</ul>", Color.black, false, false);
        }

        // Effectue toutes les actions nécessaires apres qu'un joueur ai fini ses actions  
        public void finTour(){            
            
            // Actualiser l'ihm   
            this.piocherTresor(aQuiLeTour());
            
            if (this.aQuiLeTour().getRole().equals("pilote") && ((Pilote) this.aQuiLeTour()).getHelico()==false) {
                ((Pilote) this.aQuiLeTour()).activerHelico();
            }
            piocherInondation();
            // Detecte si la victoire est encore possible
            if(cestgagne()){
                ihmJeu.afficherVictoire();
                ihmJeu.afficherBoutonRecommencer();
                ihmJeu.afficherDefaite();
                ihmJeu.afficherBoutonRecommencer();
                ihmJeu.impossibleAssecher();
                ihmJeu.impossibleDefausser();
                ihmJeu.impossibleDeplacer();
                ihmJeu.impossibleDeplacerAutre();
                ihmJeu.impossibleFinTour();
                ihmJeu.impossibleGiveCarte();
                ihmJeu.impossibleRecupererTresor(); 
                ihmJeu.desactiverHelico();
                ihmJeu.getMessageBox().displayMessage("<h1 style=\"text-align:center;\">Vous avez <br>Gagné</h1>", Color.green, true, true);              
                ihmJeu.getMessageBox().displayMessage("", Color.black, true, false);           
            } else {
                for(Aventurier a : this.lesJoueurs) {
                    if(a.getTuile().getEtat().equals("coulé") && a.TuilesAccessibles(laGrille).size()==0) {
                        a.setEtat(false);
                    }
                }
                if (this.victoirePossible()==true) {                                  
                    this.nvtour();
                } else {
                   ihmJeu.afficherDefaite();
                   ihmJeu.afficherBoutonRecommencer();
                   ihmJeu.impossibleAssecher();
                   ihmJeu.impossibleDefausser();
                   ihmJeu.impossibleDeplacer();
                   ihmJeu.impossibleDeplacerAutre();
                   ihmJeu.impossibleFinTour();
                   ihmJeu.impossibleGiveCarte();
                   ihmJeu.impossibleRecupererTresor(); 
                   ihmJeu.desactiverHelico();
                   ihmJeu.getMessageBox().displayMessage("<h1 style=\"text-align:center;\">Vous avez <br>Perdu</h1>", Color.red, true, true);
                   ihmJeu.getMessageBox().displayMessage("", Color.black, true, false);

                }

                ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                ihmJeu.getGrille().afficherPions(lesJoueurs);
            }
            
            for(Aventurier a : lesJoueurs) {
                if (a.getTuile().getEtat().equals("inondé")) {
                    a.getTuile().removeAventurier(a);
                    if (a.getRole().equals("explorateur")) {
                        a.updateTuile(a.TuilesAccessibles(laGrille).get(1));
                    } else if (a.getRole().equals("plongeur")) {
                        for(Tuile t : a.TuilesAccessibles(laGrille)) {
                            if (!t.getEtat().equals("coulé")){
                                a.updateTuile(t);
                            }
                        }
                    } else {
                        a.updateTuile(a.TuilesAccessibles(laGrille).get(0));
                    }
                }
                a.getTuile().addAventurier(a);             
            }
            ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
            ihmJeu.getGrille().afficherPions(lesJoueurs);
        }
        
      
        @Override
	public void traiterMessage(Message m) {
            switch (m.getCommande()) {
                
                case COMMENCER_JEU:
                    
                    ihmMenu.cacher();
                    this.commencerJeu();

                    // Créer la liste de joueur en fonction du nombre de joueur choisi
                    ArrayList<Aventurier> listeJoueurs = new ArrayList<>();               
                    for(int i=0; i<m.getIdAventurier();i++){
                        listeJoueurs.add(this.lesJoueurs.get(i));
                        listeJoueurs.get(i).setCouleur((String) (m.getCouleurs().get(i).getSelectedItem()));
                    }
                    
                    this.lesJoueurs=listeJoueurs;

                    this.setIhmVueJeu(new VueJeu(lesJoueurs, m.getNoms()));

                    ihmJeu.getMessageBox().displayMessage("<h1 style=\"text-align:center;\">Bienvenue dans<br>l'Île Interdite</h1>", Color.black, false, false);
                    ihmJeu.getMessageBox().displayMessage("", Color.black, true, false);   
                    
                    piocherInondationDebut();                    
                    piocherTresorDebut();
                    
                    this.ihmJeu.getGrille().initialiserGrille(this.laGrille.getTuiles(), lesJoueurs);
                    this.ihmJeu.getGrille().afficherPions(lesJoueurs);
                    this.ihmJeu.afficher();
//                    for(Tresors t : this.lesTresors){
//                        this.recupererTresor(t);
//                        this.enleverRecup(t);                               
//                    }
                    
                    this.setNiveauEau(m.getNiveauEau().getSelectedIndex()+1);
                    
                    
                    this.nvtour();

                break;
                
                
                case CHOISIR_TUILE:
                    AfficherActionsPossibles();
                    if(modeDeplacement){
                        
                        if (modeActionSpeciale) {
                            for (Tuile t : laGrille.getTuiles()) {
                                if (!t.getEtat().equals("coulé")) {
                                    tAccess.add(t);
                                }
                            }            
                            ihmJeu.getGrille().afficherTuilesDeplacer(tAccess);
                            modeChoixHelicoDestination = true;
                            modeActionSpeciale = false;
                            tuileInitialeId = m.getIdTuile();
                            
                        } else if (modeChoixHelicoDestination) {
                            ihmJeu.cacherCardsBorder();

                            for (Aventurier a : this.lesJoueurs) {
                                if (a.getTuile().getID() == tuileInitialeId) {
                                    a.getTuile().removeAventurier(a);
                                    // Change la tuile de l'aventurier
                                    a.updateTuile(this.getGrille().getTuiles().get(m.getIdTuile()));                 
                                    // Met un aventurier sur la nouvelle tuile
                                    this.getGrille().getTuiles().get(m.getIdTuile()).addAventurier(a);
                                }
                                
                                if(a.getId() == cardOwnerId) {
                                    a.getCartes().get(cardUsedId).changerProprio(null);                    
                                    defausseTresor.addPile(a.getCartes().get(cardUsedId));                    
                                    a.defausserCarte(a.getCartes().get(cardUsedId));
                                    
                                    ihmJeu.updateCards(a.getId(), a.getCartes());
                                    ihmJeu.defausseLastCard(a.getId());
                                    ihmJeu.cacherCardsBorder();
                                }
                            }
                            
                            modeChoixHelicoDestination = false;
                            ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                            ihmJeu.getGrille().afficherPions(lesJoueurs);   
                            
                        } else {

                            // Enleve l'aventurier da la tuile ou il etait
                            this.aQuiLeTour().getTuile().removeAventurier(this.aQuiLeTour());
                            // Change la tuile de l'aventurier
                            this.aQuiLeTour().updateTuile(this.getGrille().getTuiles().get(m.getIdTuile()));                 
                            // Met un aventurier sur la nouvelle tuile
                            this.getGrille().getTuiles().get(m.getIdTuile()).addAventurier(this.aQuiLeTour());

                            ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                            ihmJeu.getGrille().afficherPions(lesJoueurs);   
                            
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

                            if(this.aQuiLeTour().getRole().equals("pilote") && ((Pilote) this.aQuiLeTour()).getHelico()){
                                ((Pilote) this.aQuiLeTour()).desactiverHelico();                  
                            }

                        }
                        
                        // Détecte si le joueur peut encore jouer
                        if(this.getActions()>0){
                            this.tourDeJeu();
                            ihmJeu.impossibleDeplacer();
                        }
                        else{
                            this.finTour();
                        }
      
                    } else if (modeAssechement)  {

                        this.getGrille().getTuiles().get(m.getIdTuile()).assecherTuile(); 
                        ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                        ihmJeu.getGrille().afficherPions(lesJoueurs);

                        tAssech = this.aQuiLeTour().TuilesAssechables(laGrille);     

                        if (modeActionSpeciale) {
                            
                            ihmJeu.cacherCardsBorder();
                            for (Aventurier a : this.lesJoueurs) {
                                if(a.getId() == cardOwnerId) {
                                
                                    a.getCartes().get(cardUsedId).changerProprio(null);                    
                                    defausseTresor.addPile(a.getCartes().get(cardUsedId));                    
                                    a.defausserCarte(a.getCartes().get(cardUsedId));
                                    
                                    ihmJeu.updateCards(a.getId(), a.getCartes());
                                    ihmJeu.defausseLastCard(a.getId());
                                    ihmJeu.cacherCardsBorder();
                                }
                            }
                            modeActionSpeciale = false;
                            
                        } else {

                            ihmJeu.getGrille().afficherTuilesAssecher(tAssech);

                            // Gere le double assechement d'un ingenieur
                            if(this.aQuiLeTour().getRole()=="ingenieur" && !doubleAssechement){            
                                if(this.getActions()==1){
                                    //appeler fonction qui desactive tous les boutons sauf celui de assechement et fintour
                                    doubleAssechement=true;
                                }
                                else{
                                    this.actionFinie();
                                    doubleAssechement=true;
                                }
                            } 
                            else if(this.aQuiLeTour().getRole()=="ingenieur" && doubleAssechement){
                                if(doubleAssechement=false && this.getActions()==1){
                                    this.actionFinie();
                                    doubleAssechement=false;
                                }
                                else{
                                doubleAssechement=false;

                                }
                            } else {
                                this.actionFinie();
                            }

                            if (this.getActions() == 1 && this.aQuiLeTour().getTuile().getEtat() == "coulé" ) {
                                modeDeplacement = false;
                                modeAssechement = false;
                                ihmJeu.impossibleAssecher();
                                ihmJeu.getGrille().afficherPions(lesJoueurs);
                            }                     

                            ihmJeu.updateActions(this.getActions());

                        }
                        
                        // Detecte si le joueur peut encore jouer
                        if(this.getActions()>0){                                   
                            this.tourDeJeu();
                            ihmJeu.impossibleAssecher();

                        } else {
                            this.finTour();
                        }

                    } else if (modeDeplacerAutre) {
                        for (Aventurier a : lesJoueurs) {
                            if (a.getId() == m.getIdAventurier()) {

                                // Enleve l'aventurier da la tuile ou il etait
                                a.getTuile().removeAventurier(a);
                                // Change la tuile de l'aventurier
                                a.updateTuile(this.getGrille().getTuiles().get(m.getIdTuile()));                 
                                // Met un aventurier sur la nouvelle tuile
                                this.getGrille().getTuiles().get(m.getIdTuile()).addAventurier(a);                              
                                ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                                ihmJeu.getGrille().afficherPions(lesJoueurs);

                                tAccess = ((Navigateur)aQuiLeTour()).deplacerAutreJoueur(a,laGrille);
                                ihmJeu.getGrille().afficherTuilesDeplacer(tAccess,m.getIdAventurier());
                                this.actionFinie();
                            }
                        }



                        ihmJeu.updateActions(this.getActions());

                        // Detecte si le joueur peut encore jouer
                        if(this.getActions()>0){
                            this.tourDeJeu();
                            ihmJeu.impossibleDeplacerAutre();
                        } else {
                            this.finTour();
                        }
                    }

                break;
  
                case SE_DEPLACER:
                    AfficherActionsPossibles();
                    ihmJeu.cacherCardsBorder();
                    ihmJeu.cacherNameBackground();
                    modeDefausser = false;
                    modeAssechement = false;
                    modeDeplacerAutre = false;
                    
                    if (!m.getHelico()) {
                        ihmJeu.impossibleDeplacer();
                        modeDeplacement = true;
                    }
                    
                    ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                    ihmJeu.getGrille().afficherPions(lesJoueurs);
                                        
                    this.tAccess = this.aQuiLeTour().TuilesAccessibles(this.laGrille);

                    if(this.aQuiLeTour().getRole().equals("pilote")){

                        if (m.getHelico()) {
                            this.tAccess = (((Pilote) this.aQuiLeTour()).deplacementHelico(this.getGrille()));
                            ((Pilote)this.aQuiLeTour()).activerHelico();
                            ihmJeu.desactiverHelico();
                        } else if (((Pilote) this.aQuiLeTour()).getHelico()) {
                            ihmJeu.activerHelico();
                            ((Pilote)this.aQuiLeTour()).desactiverHelico();
                        }
                        
                    }
                    
                    ihmJeu.getGrille().afficherTuilesDeplacer(tAccess);
                    
                    // Si le plongeur a une action restante et est sur une tuile coulée, le bouton assécher se désactive
                    if (this.aQuiLeTour().getRole() == "plongeur" && this.getActions() == 1 && this.aQuiLeTour().getTuile().getEtat() == "coulé" ) {
                        ihmJeu.impossibleAssecher();
                    }
                      
                break;
                  
                case DEPLACER_AUTRE:
                    AfficherActionsPossibles();
                    ihmJeu.impossibleDeplacerAutre();
                    ihmJeu.cacherCardsBorder();
                    ihmJeu.cacherNameBackground();
                    modeDefausser = false;
                    modeAssechement = false;
                    modeDeplacement = false;
                    modeDonner = false;
                    modeDeplacerAutre = true;
                    ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                    ihmJeu.getGrille().afficherPions(lesJoueurs);
                    
                    ArrayList<Aventurier> tempAv = new ArrayList<Aventurier>();
                    
                    for (Aventurier a : lesJoueurs) {
                        if (!((Navigateur) this.aQuiLeTour()).deplacerAutreJoueur(a,laGrille).isEmpty() && !a.equals(this.aQuiLeTour())) {
                            tempAv.add(a);
                        }
                    }
                    
                    ihmJeu.choisirJoueur(tempAv);

                    
                    
                break;  
 
                
                case ASSECHER: 
                    AfficherActionsPossibles();
                    ihmJeu.impossibleAssecher();
                    ihmJeu.cacherCardsBorder();
                    ihmJeu.cacherNameBackground();
                    modeDefausser = false;
                    modeAssechement = true;
                    modeDeplacement = false;
                    modeDeplacerAutre = false;

                    
                    ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                    ihmJeu.getGrille().afficherPions(lesJoueurs);
                    tAssech = new ArrayList<>();
                    tAssech = this.aQuiLeTour().TuilesAssechables(this.getGrille());
                    
                    //ihm.afficherTuilesAssecher(this.aQuiLeTour().TuilesAssechables(this.getGrille()));
                    if(this.aQuiLeTour().getRole().equals("pilote") && ((Pilote) this.aQuiLeTour()).getHelico()){
                        ihmJeu.activerHelico();
                    }
                    
                    ihmJeu.getGrille().afficherTuilesAssecher(tAssech);
                    
                break;

                case RECUPERER_TRESOR:   
                    switch(((TuileTresor)this.aQuiLeTour().getTuile()).sonTresor.getNom()){
                        case "Le Calice de l’onde":
                            for(Tresors t : this.lesTresors){
                               if(t.getNom()=="Le Calice de l’onde"){
                                   this.recupererTresor(t);
                                   this.enleverRecup(t);
                               }
                            }
                            this.actionFinie();
                        break;
                        
                        case "La Pierre sacrée":
                            for(Tresors t : this.lesTresors){
                               if(t.getNom()=="La Pierre sacrée"){
                                   this.recupererTresor(t);
                                   this.enleverRecup(t);
                               }
                            }
                            this.actionFinie();
                        break;
                        
                        case "La Statue du zéphyr":
                            for(Tresors t : this.lesTresors){
                               if(t.getNom()=="La Statue du zéphyr"){
                                   this.recupererTresor(t);
                                   this.enleverRecup(t);
                               }
                            }
                            this.actionFinie();
                        break;
                        
                        case "Le Cristal ardent":
                            for(Tresors t : this.lesTresors){
                               if(t.getNom()=="Le Cristal ardent"){
                                   this.recupererTresor(t);
                                   this.enleverRecup(t);
                               }
                            }
                            this.actionFinie();
                        break;
                    }
                    // Detecte si le joueur peut encore jouer
                    if(this.getActions()>0){                                   
                        this.tourDeJeu();
                    }
                    else{
                        this.finTour();
                    }
                    AfficherActionsPossibles();
                break;
                 
    
                case CARTE_MAIN_PLEINE:
                    AfficherActionsPossibles();                   
                    // idAvent les cartes en haut
                    // idCarte les cartes en qu il a
                    aQuiLeTour().defausserCarte(aQuiLeTour().getCartes().get(m.getIdCarte()));
                    aQuiLeTour().addCarte((CarteMain) PileTresor.getSesCartes().get(m.getIdAventurier()));
                    ((CarteMain)PileTresor.getSesCartes().get(m.getIdAventurier())).changerProprio(aQuiLeTour().getRole());
                    defausseTresor.addPile(PileTresor.getSesCartes().get(m.getIdAventurier()));
                    PileTresor.RemoveCarte(PileTresor.getSesCartes().get(m.getIdAventurier()));   
                    

                break;
                
                case DEFAUSSER_CARTE:
                    
                    AfficherActionsPossibles();
                    ihmJeu.impossibleDefausser();
                    if(this.aQuiLeTour().getRole().equals("pilote") && ((Pilote) this.aQuiLeTour()).getHelico()){
                        ihmJeu.activerHelico();
                    }
                    modeDefausser = true;
                    modeDonner = false;    
                    ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                    ihmJeu.getGrille().afficherPions(lesJoueurs);
                    ihmJeu.afficherCardsBorder();
                    ihmJeu.cacherNameBackground();
                    
                break;
                
                case DONNER:     
                    AfficherActionsPossibles();
                    ihmJeu.impossibleGiveCarte();
                    
                    // Cacher toutes les bordures et les background (cas où Defausser sélectionné)
                    ihmJeu.cacherCardsBorder();
                    ihmJeu.cacherNameBackground();            
                    
                    // Raffiche la grille et les pions (enlève les bordures vertes/rouges des tuiles)
                    ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                    ihmJeu.getGrille().afficherPions(lesJoueurs);
                    
                    if(this.aQuiLeTour().getRole().equals("pilote") && ((Pilote) this.aQuiLeTour()).getHelico()){
                        ihmJeu.activerHelico();
                    }
                    
                    modeDonner = true;
                    modeActionSpeciale = false;
                    modeDefausser = false;
                    
                    ihmJeu.afficherCardsBorder(this.aQuiLeTour().getId());

                break;
                
                case ACTION_SPECIALE:
                    AfficherActionsPossibles();
                    ihmJeu.impossibleActionSpeciale();
                    
                    // Cacher toutes les bordures et les background (cas où Defausser sélectionné)
                    ihmJeu.cacherCardsBorder();
                    ihmJeu.cacherNameBackground();            
                    
                    // Raffiche la grille et les pions (enlève les bordures vertes/rouges des tuiles)
                    ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                    ihmJeu.getGrille().afficherPions(lesJoueurs);    
                    
                    if(this.aQuiLeTour().getRole().equals("pilote") && ((Pilote) this.aQuiLeTour()).getHelico()){
                        ihmJeu.activerHelico();
                    }     
                    
                    modeActionSpeciale = true;
                    modeDonner = false;
                    modeDefausser = false; 
                    
                    ihmJeu.afficherCardsActionSpecialeBorder();
                    
                break;
                
                case CHOISIR_CARTE:                    
                    if (modeDefausser) {
                        for (Aventurier a : this.lesJoueurs) {
                            if(a.getId() == m.getIdAventurier() ) {
                                a.getCartes().get(m.getIdCarte()).changerProprio(null);                    
                                defausseTresor.addPile(a.getCartes().get(m.getIdCarte()));                    
                                a.defausserCarte(a.getCartes().get(m.getIdCarte()));
                                
                                ihmJeu.updateCards(a.getId(), a.getCartes());
                                ihmJeu.defausseLastCard(a.getId());
                                ihmJeu.cacherCardsBorder();
                            }
                        }
                        
                    // le message contient l'id de l'aventurier donneur et le numCarte de la carte choisie
                    } else if (modeDonner) {
                        cardOwnerId = m.getIdAventurier();
                        
                        // Enleve la bordure de chaque carte sauf celle choisie
                        ihmJeu.cacherCardsBorder(m.getIdCarte(), m.getIdAventurier() );
                        
                        // ArrayList de joueurs auxquels on peut donner une carte
                        ArrayList<Aventurier> tempAv2 = new ArrayList<Aventurier>();
                        
                        // Vérifie les joueurs qui peuvent recevoir une carte
                        for (Aventurier a : this.getJoueurs()){                               
                            if (a != this.aQuiLeTour()){  
                                if (a.getCartes().size()<5 && this.aQuiLeTour().getTuile() == a.getTuile()){
                                    tempAv2.add(a);
                                }
                                else if(a.getCartes().size()<5  && (this.aQuiLeTour().getRole()).equals("messager")){
                                    tempAv2.add(a);
                                }
                            }
                        }   
                        ihmJeu.choisirJoueur(tempAv2,m.getIdCarte());

                    } else if (modeActionSpeciale) {

                        cardOwnerId = m.getIdAventurier();
                        cardUsedId = m.getIdCarte();
                        
                        tAssech.removeAll(tAssech);
                        tAccess.removeAll(tAccess);
                        for (Aventurier a : lesJoueurs) {
                            if (a.getId() == m.getIdAventurier()) {
                                // Si carte sac de sable
                                if (a.getCartes().get(m.getIdCarte()).getNom().equals("Sac")) {
                                    for (Tuile t : getGrille().getTuiles()) {
                                        if (t.getEtat().equals("inondé")) {
                                            tAssech.add(t);
                                        }
                                    }
                                    modeAssechement = true;
                                    modeDeplacement = false;
                                    ihmJeu.getGrille().afficherTuilesAssecher(tAssech);
                                    
                                // Si carte hélicoptère
                                } else {
                                    
                                    // Ajoute et affiche les tuiles contenant des aventuriers (pour sélectionner ceux à déplacer)
                                    ArrayList<Tuile> tuilesAvecAventuriers = new ArrayList<Tuile>();
                                    for (Aventurier aventurier : this.lesJoueurs) {
                                        if (!tuilesAvecAventuriers.contains(aventurier.getTuile())){
                                            tuilesAvecAventuriers.add(aventurier.getTuile());
                                        }
                                    }                                    
                                    ihmJeu.getGrille().afficherTuilesDeplacer(tuilesAvecAventuriers);
                                    
                                    modeAssechement = false;
                                    modeDeplacement = true;
                                }
                                ihmJeu.cacherCardsBorder(m.getIdCarte(), m.getIdAventurier());
                            }
                        }
                    }
                    
                    AfficherActionsPossibles();
                    
                break;
                
                
                case CHOISIR_JOUEUR:
                    if (this.modeDonner) {
                    
                    ihmJeu.cacherNameBackground();
 
                        this.donnerCarte(m.getIdAventurier(), m.getIdCarte());

                        for (Aventurier a : this.lesJoueurs) {

                            // Update l'affichage des cartes de l'aventurier receveur et du donneur
                            if(a.getId() == m.getIdAventurier()) {
                                ihmJeu.updateCards(a.getId(),a.getCartes());
                            } else if (a.getId() == this.cardOwnerId) {
                                ihmJeu.updateCards(a.getId(), a.getCartes());
                                ihmJeu.cacherCardsBorder();
                                ihmJeu.defausseLastCard(a.getId());
                            }

                        }
                        this.actionFinie();                  

                        if(this.getActions()>0){
                            this.tourDeJeu();
                        }
                        else{
                            this.finTour();
                        }
                    } else if (this.modeDeplacerAutre) {
                        
                        
                        for (Aventurier a : lesJoueurs) {
                            if (a.getId() == m.getIdAventurier()) {
                               tAccess = ((Navigateur)aQuiLeTour()).deplacerAutreJoueur(a,laGrille);
                            }  
                        }
                        ihmJeu.getGrille().resetGrille(laGrille.getTuiles());
                        ihmJeu.getGrille().afficherPions(lesJoueurs);
                        ihmJeu.getGrille().afficherTuilesDeplacer(tAccess,m.getIdAventurier());

                        // Voir deplacement apres
                        
                    }
                    
                break;
                

                                
                case FIN_TOUR:   
                    AfficherActionsPossibles();
                    this.finTour();
                break;
                
                case RECOMMENCER:
                    this.ihmMenu.afficher();
                    for(Tuile t : this.laGrille.getTuiles()){
                        t.setEtat("seche");
                    }
                    doubleAssechement = false;
                    modeDeplacement = false; 
                    modeAssechement = false; 
                    modeDefausser = false; 
                    modeDonner = false; 
                    modeDeplacerAutre = false; 
                    usingHelico = false;
                    tAssech.removeAll(tAssech);
                    tAccess.removeAll(tAccess);
                    for (Aventurier a : this.getJoueurs()) {
                        a.getCartes().removeAll(a.getCartes());
                    }
                    for (Tresors t : this.lesTresors) {
                        t.setEtat(false);
                    }
                    this.PileInondation.ViderPile();
                    this.PileTresor.ViderPile();
                    this.defausseInondation.ViderPile();
                    this.defausseTresor.ViderPile();
                    this.lesTresors.removeAll(this.lesTresors);
                    this.laGrille.getTuiles().removeAll(laGrille.getTuiles());
                    
                    
                    this.ihmJeu.cacher();
                break;

            }
        }

        // Regarde si les joueurs peuvent encore gagner
	public boolean victoirePossible() {      
            
            int x=0;
            // Regarde si un aventurier est mort
            for(Aventurier a : this.getJoueurs()){
                if(!a.getEtat()){                                     
                    return false;
                }
            }

            // Regarde si les deux tuileTresors d'un tresor sont coulées 
            for(Tresors t: this.getTresors()){
                for(TuileTresor tt : t.getSesTuiles()){                                                                                
                    if(tt.getEtat()=="coulé"){                              
                        x++;
                        if(t.getEtat()==false && x==2){                             
                            return false;                                         
                        }
                    }
                }

                // Return false si le tresor n'a pas été pris  (pas commencer le code et que ses deux tuiles tresors ont été coulées).
                x = 0;
            }
            
   
           
            
            
            // Regarde si le niveau d'eau est egal a 10
            if(this.getNiveauEau()>=10){                                         
                return false;
            }
            
            for(Tuile t : this.getGrille().sesTuiles) {
                if (t.getNom().equals("Heliport") && t.getEtat().equals("coulé")) {
                  return false;
                }
            }
            // (manque si le heliport est coulé ou non)
            return true;
        }


}