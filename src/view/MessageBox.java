package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import util.Utils;

public class MessageBox extends JPanel{
    private final JEditorPane html;
    private final JScrollPane scrollPane;
    String texte;
    private final JPanel panelZephyr;
    private final JPanel panelCalice;
    private final JPanel panelCristal;
    private final JPanel panelPierre;
    
    public MessageBox() {
        
        this.setLayout(new BorderLayout());
        
        JPanel panelTresors = new JPanel(new GridLayout(1,4));
        this.add(panelTresors, BorderLayout.NORTH);
        
        this.panelZephyr = new JPanel();
        panelZephyr.setBackground(Utils.Tresor.ZEPHYR.getBgColor());
        panelZephyr.add(new JLabel(Utils.Tresor.ZEPHYR.name(), JLabel.CENTER));
        panelTresors.add(panelZephyr);
        panelZephyr.setVisible(false);
        
        this.panelCalice = new JPanel();
        panelCalice.setBackground(Utils.Tresor.CALICE.getBgColor());
        panelCalice.add(new JLabel(Utils.Tresor.CALICE.name(), JLabel.CENTER));
        panelTresors.add(panelCalice);
        panelCalice.setVisible(false);
        
        this.panelCristal = new JPanel();
        panelCristal.setBackground(Utils.Tresor.CRISTAL.getBgColor());
        panelCristal.add(new JLabel(Utils.Tresor.CRISTAL.name(), JLabel.CENTER));
        panelTresors.add(panelCristal);
        panelCristal.setVisible(false);
        
        this.panelPierre = new JPanel();
        panelPierre.setBackground(Utils.Tresor.PIERRE.getBgColor());
        panelPierre.add(new JLabel(Utils.Tresor.PIERRE.name(), JLabel.CENTER));
        panelTresors.add(panelPierre);
        panelPierre.setVisible(false);
        
        html = new JEditorPane();
        html.setEditable(false);
        html.setContentType("text/html");
        scrollPane = new JScrollPane(html);
        
        html.setMinimumSize(new Dimension(180, 280));
        html.setPreferredSize(new Dimension(180, 280));
        scrollPane.setPreferredSize(new Dimension(180, 280));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMinimumSize(new Dimension(180, 280));
        
        html.setText("<html><h1 style=\"text-align:center; color:black;\">Bienvenue dans<br>l'Île Interdite</h1></html>");
        this.add(scrollPane, BorderLayout.CENTER) ;
        
        this.texte = "" ;
    }
    
    public void displayMessage(String texte, Color couleur, Boolean precederDunTrait, Boolean garderCouleur) {
        html.setText("<html>" + (precederDunTrait ? "<hr>" : "") + this.texte + "<div style=\"color : " + toRGB(couleur) + "\">"+ texte + "</div></html>");
        if (garderCouleur)
            this.texte += "<div style=\"color : " + toRGB(couleur) + "\">" + (precederDunTrait ? "<hr>" : "") + texte + "</div>" ;
        else 
            this.texte += "<div>" + (precederDunTrait ? "<hr>" : "") + texte + "</div>" ;
    }
    
    public void displayAlerte(String texte) {
        this.texte += "<div style=\"color : " + toRGB(Color.RED) + "\"><b>" + texte + "</b></div>" ;
        html.setText("<html>" + this.texte +  "</html>");
    }
    
    public String toRGB(Color couleur) {
        return "#"+Integer.toHexString(couleur.getRGB()).substring(2);
    }

    public void setZephyrVisible() {
        this.panelZephyr.setVisible(true);
        this.displayMessage("Vous avez gagné le " + Utils.Tresor.ZEPHYR.toString(), Utils.Tresor.ZEPHYR.getBgColor(), false, true);
    }

    public void setPierreVisible() {
        this.panelPierre.setVisible(true);
        this.displayMessage("Vous avez gagné la " + Utils.Tresor.PIERRE.toString(), Utils.Tresor.PIERRE.getBgColor(), false, true);
    }

    public void setCaliceVisible() {
        this.panelCalice.setVisible(true);
        this.displayMessage("Vous avez gagné le " + Utils.Tresor.CALICE.toString(), Utils.Tresor.CALICE.getBgColor(), false, true);
    }

    public void setCristalVisible() {
        this.panelCristal.setVisible(true);
        this.displayMessage("Vous avez gagné le "  + Utils.Tresor.CRISTAL.toString(), Utils.Tresor.CRISTAL.getBgColor(), false, true);
    }

    public void displayTresor(Utils.Tresor tresor) {
        switch(tresor) {
            case ZEPHYR :
                setZephyrVisible();
                break ;
            case PIERRE :
                setPierreVisible();
                break;
            case CALICE :
                setCaliceVisible();
                break;
            case CRISTAL :
                setCristalVisible();
                break;
        }
    }
}
