package util;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author IUT2-Dept Info
 */
public class Message implements Serializable {
    private final Utils.Commandes commande ;
    private final Integer idAventurier ;
    private final Integer idCarte ;
    private final Utils.Tresor tresor ;
    private final Integer idTuile ;
    private ArrayList<JTextField> noms;
    private boolean helico = false;
    
    public Message(Utils.Commandes commande, Integer idAventurier, Integer idCarte,  Utils.Tresor tresor, Integer idTuile) {
        this.commande = commande ;
        this.idAventurier = idAventurier ;
        this.idCarte = idCarte ;
        this.tresor = tresor ;
        this.idTuile = idTuile ;
    }
   
    /**
     * @return the commande
     */
    public Boolean hasCommande() {
        return commande != null ;
    }
    public Utils.Commandes getCommande() {
        return commande;
    }

    /**
     * @return the idAventurier`
     */
    public Boolean hasIdAventurier() {
        return idAventurier != null ;
    }
    public Integer getIdAventurier() {
        return idAventurier;
    }

    /**
     * @return the idCarte
     */
    public Boolean hasIdCarte() {
        return idCarte != null ;
    }
    public Integer getIdCarte() {
        return idCarte;
    }

    /**
     * @return the tresor
     */
    public Boolean hasTresor() {
        return tresor != null ;
    }
    public  Utils.Tresor getTresor() {
        return tresor;
    }

    /**
     * @return the idTuile
     */
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

    public boolean getHelico() {
        return helico;
    }
    
    public void setHelico(boolean helico) {
        this.helico = helico;
    }   
    
    @Override
    public String toString() {
        String txt = "" ;
        txt += commande.toString() + " " ;
        if (hasIdAventurier()) {
            txt += " aventurier=" + idAventurier ;
        }
        if (hasIdCarte()) {
            txt += " carte=" + idCarte ;
        }
        if (hasIdTuile()) {
            txt += " tuile=" + idTuile ;
        }
        if (hasTresor()) {
            txt += " tresor=" + tresor.toString() ;
        }
        return txt ;
    }
}
