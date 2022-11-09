package SwiftWay.Models;

public class Offre {
       // Les attributs De la classe :
    private double idOffre;
    private int nbrDePlacesDisponibles;
    private double prix;

       // Le constructeur de la classe :

    public Offre(double idOffre, int nbrDePlacesDisponibles, double prix, Companie companie) {
        this.idOffre = idOffre;
        this.nbrDePlacesDisponibles = nbrDePlacesDisponibles;
        this.prix = prix;
        this.companie = companie;
    }
       // La Depandence avec la classe Companie :

    public Companie companie;

       // La Depandence avec la classe Ville :

    public Ville villeDeDepart;
    public Ville villeDarrivee;

       // La Depandence avec la classe Autocar :

    public Autocar autocar;

       // Les Getters et les Setters :
    public double getIdOffre() {
        return idOffre;
    }
    public void setIdOffre(double idOffre) {
        this.idOffre = idOffre;
    }
    public int getNbrDePlacesDisponibles() {
        return nbrDePlacesDisponibles;
    }
    public void setNbrDePlacesDisponibles(int nbrDePlacesDisponibles) {
        this.nbrDePlacesDisponibles = nbrDePlacesDisponibles;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
}
