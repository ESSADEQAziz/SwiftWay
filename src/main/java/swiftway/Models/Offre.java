package swiftway.Models;
public class Offre {
       // Les attributs De la classe :
    private int idOffre;
    private int nbrDePlacesDisponibles;
    private double prix;
    private String nomSociete;

       // Le constructeur de la classe :

    public Offre(int idOffre, int nbrDePlacesDisponibles, double prix, String nomSociete, String villeDeDepart,
            String villeDarrivee) {
        this.idOffre = idOffre;
        this.nbrDePlacesDisponibles = nbrDePlacesDisponibles;
        this.prix = prix;
        this.nomSociete = nomSociete;
        this.villeDeDepart = villeDeDepart;
        this.villeDarrivee = villeDarrivee;
    }
    public Offre(int idOffre, int nbrDePlacesDisponibles, double prix, Companie companie) {
        this.idOffre = idOffre;
        this.nbrDePlacesDisponibles = nbrDePlacesDisponibles;
        this.prix = prix;
        this.companie = companie;
    }
       // La Depandence avec la classe Companie :

    

    public Companie companie;

       // La Depandence avec la classe Ville :

    private String villeDeDepart;
    private String villeDarrivee;

       // La Depandence avec la classe Autocar :
    public Autocar autocar;

       // Les Getters et les Setters :
    public int getIdOffre() {
        return idOffre;
    }
    public void setIdOffre(int idOffre) {
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
    public String getNomSociete() {
        return nomSociete;
    }
    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }
    public String getVilleDeDepart() {
        return villeDeDepart;
    }
    public void setVilleDeDepart(String villeDeDepart) {
        this.villeDeDepart = villeDeDepart;
    }
    public String getVilleDarrivee() {
        return villeDarrivee;
    }
    public void setVilleDarrivee(String villeDarrivee) {
        this.villeDarrivee = villeDarrivee;
    }
}
