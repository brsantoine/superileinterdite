package util;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Message implements Serializable {
    private final Utils.Commandes commande ;
    private final Integer idAventurier ;
    private final Integer idCarte ;
    private final Utils.Tresor tresor ;
    private final Integer idTuile ;
    private ArrayList<JTextField> noms;
    private ArrayList<JComboBox> couleurs;
    private JComboBox niveauEau;
    private boolean helico = false;
    
    public Message(Utils.Commandes commande, Integer idAventurier, Integer idCarte,  Utils.Tresor tresor, Integer idTuile) {
        this.commande = commande ;
        this.idAventurier = idAventurier ;
        this.idCarte = idCarte ;
        this.tresor = tresor ;
        this.idTuile = idTuile ;
    }
   
    public Boolean hasCommande() {
        return commande != null ;
    }
    public Utils.Commandes getCommande() {
        return commande;
    }

    public Boolean hasIdAventurier() {
        return idAventurier != null ;
    }
    public Integer getIdAventurier() {
        return idAventurier;
    }

    public Boolean hasIdCarte() {
        return idCarte != null ;
    }
    public Integer getIdCarte() {
        return idCarte;
    }

    public Boolean hasTresor() {
        return tresor != null ;
    }
    public  Utils.Tresor getTresor() {
        return tresor;
    }

    public Boolean hasIdTuile() {
        return idTuile != null;
    }
    public Integer getIdTuile() {
        return idTuile;
    }

    public ArrayList<JTextField> getNoms() {
        return noms;
    }
    
    public void setNoms(ArrayList<JTextField> noms) {
        this.noms = noms;
    }   

    public ArrayList<JComboBox> getCouleurs() {
        return couleurs;
    }

    public void setCouleurs(ArrayList<JComboBox> couleurs) {
        this.couleurs = couleurs;
    }

    public boolean getHelico() {
        return helico;
    }
    
    public void setHelico(boolean helico) {
        this.helico = helico;
    }   

    public JComboBox getNiveauEau() {
        return niveauEau;
    }

    public void setNiveauEau(JComboBox niveauEau) {
        this.niveauEau = niveauEau;
    }

}