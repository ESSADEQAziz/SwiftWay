package swiftway.Models;
import java.util.ArrayList;


public class Companie {
       // Les attributs De la classe :

    private String nomDeSociete;
    private int totalVehicules;
    private int nombreDoffres;
    
       // Le Constructeur De la classe :

    public Companie(String nomDeSociete) {
        this.nomDeSociete = nomDeSociete;
    }
    
      // Le Constructeur De la classe :

     public Companie(String nomDeSociete, int totalVehicules,int nombreDoffres) {
        this.nomDeSociete = nomDeSociete;
        this.totalVehicules = totalVehicules;
        this.nombreDoffres=nombreDoffres;
    }
       // La Depandence avec la classe Offre :
    public ArrayList<Offre> offres = new ArrayList<Offre>();

       // Les Getters et les Setters :

   
    public String getNomDeSociete() {
        return nomDeSociete;
    }
    public void setNomDeSociete(String nomDeSociete) {
        this.nomDeSociete = nomDeSociete;
    }
    public int getTotalVehicules() {
        return totalVehicules;
    }
    public void setTotalVehicules(int totalVehicules) {
        this.totalVehicules = totalVehicules;
    }
    public int getNombreDoffres() {
        return nombreDoffres;
    }

    public void setNombreDoffres(int nombreDoffres) {
        this.nombreDoffres = nombreDoffres;
    }
}
