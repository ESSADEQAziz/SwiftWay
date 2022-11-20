package swiftway.Models;

import java.util.ArrayList;

public class Companie {
       // Les attributs De la classe :

    private String nomDeSociete;
    private int totalVehicules;

       // Le Constructeur De la classe :

    public Companie(String nomDeSociete) {
        this.nomDeSociete = nomDeSociete;
    }
    
       
      // Le Constructeur De la classe :
    public Companie(String nomDeSociete, int totalVehicules) {
        this.nomDeSociete = nomDeSociete;
        this.totalVehicules = totalVehicules;
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
}
