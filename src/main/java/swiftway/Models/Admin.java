package swiftway.Models;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Admin {
       // Les attributs de la classe :

    private SimpleStringProperty nomUtilisateur;
    private SimpleStringProperty motDePasse;
 
       // Le constructeur de la classe :

    public Admin(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = new SimpleStringProperty(nomUtilisateur);
        this.motDePasse = new SimpleStringProperty(motDePasse);
    }

       // La Depandence avec la classe Offre :

    public ArrayList<Offre> offres = new ArrayList<Offre>();
    
       // Les getters et les setters :

    public String getNomUtilisateur() {
        return nomUtilisateur.get();
    }
    public void setNomUtilisateur(SimpleStringProperty nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
    public String getMotDePasse() {
        return motDePasse.get();
    }
    public void setMotDePasse(SimpleStringProperty motDePasse) {
        this.motDePasse =motDePasse;
    }

       // Les methodes du classe :

    public void Authentification(String nomUtilisateur,String motDePasse){

    }
    
}
