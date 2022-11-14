package swiftway.Models;

import java.util.ArrayList;

public class Admin {
       // Les attributs de la classe :

    private String nomUtilisateur;
    private String motDePasse;
 
       // Le constructeur de la classe :

    public Admin(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

       // La Depandence avec la classe Offre :

    public ArrayList<Offre> offres = new ArrayList<Offre>();
    
       // Les getters et les setters :

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }
    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

       // Les methodes du classe :

    public void Authentification(String nomUtilisateur,String motDePasse){

    }
    
}
