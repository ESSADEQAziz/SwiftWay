package SwiftWay.Models;

import java.util.ArrayList;

public class Voyajeur {
       // Les attributs de la classe :

    private String CIN;
    private String nom;
    private String prenom;

       // Le constructeur de la classe :

    public Voyajeur(String cIN, String nom, String prenom) {
        CIN = cIN;
        this.nom = nom;
        this.prenom = prenom;
    }
    // La depandence avec la classe Offre :

    public ArrayList<Offre> offres = new ArrayList<Offre>();

       // La dependance avec la classe Reservation :

       ArrayList<Reservation> reservations = new ArrayList<Reservation>();

       // Les getters et les settres :

    public String getCIN() {
        return CIN;
    }
    public void setCIN(String cIN) {
        CIN = cIN;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
       // Les methode de la classe : 

    public void Retirer(){
    }
    public void consulter(){
    }
}
