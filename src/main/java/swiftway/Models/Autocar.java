package swiftway.Models;

public class Autocar {
       // Les attributs De la classe :
             
    private double idAutocar;
    private int numeroAutocar;
    
       // Le Constructeur De la classe :

    public Autocar(double idAutocar, int numeroAutocar, int placesTotal) {
        this.idAutocar = idAutocar;
        this.numeroAutocar = numeroAutocar;
        this.placesTotal = placesTotal;
    }        
       // Les Getters et les Setters :

    public double getIdAutocar() {
        return idAutocar;
    }
    public void setIdAutocar(double idAutocar) {
        this.idAutocar = idAutocar;
    }
    public int getNumeroAutocar() {
        return numeroAutocar;
    }
    public void setNumeroAutocar(int numeroAutocar) {
        this.numeroAutocar = numeroAutocar;
    }
    public int getPlacesTotal() {
        return placesTotal;
    }
    public void setPlacesTotal(int placesTotal) {
        this.placesTotal = placesTotal;
    }
    private int placesTotal;
}
