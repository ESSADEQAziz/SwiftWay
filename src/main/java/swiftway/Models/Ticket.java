package swiftway.Models;

public class Ticket {
       // Les attributs de la classe :
    private double idTicket;
    private int numeroDePlace;
    private double prixTTC;
    private int counterDePlacesDisponibles;
    private int numeroAutocar;

       // Le constructeur de la classe :

    public Ticket(double idTicket, int numeroDePlace, double prixTTC, int counterDePlacesDisponibles, int numeroAutocar) {
        this.idTicket = idTicket;
        this.numeroDePlace = numeroDePlace;
        this.prixTTC = prixTTC;
        this.counterDePlacesDisponibles = counterDePlacesDisponibles;
        this.numeroAutocar = numeroAutocar;
    }
       // La depandence avec la classe offre :

       public Offre offre;

       // La depandence avec la classe Reservation dans sa relation avec paiment :
    
    public Reservation reservation;

       // La depandence avec la classe Paiment dans sa relation avec Reservation :
    
    public Paiment paiment ;

       // Les getters et les setters :

    public double getIdTicket() {
        return idTicket;
    }
    public void setIdTicket(double idTicket) {
        this.idTicket = idTicket;
    }
    public int getNumeroDePlace() {
        return numeroDePlace;
    }
    public void setNumeroDePlace(int numeroDePlace) {
        this.numeroDePlace = numeroDePlace;
    }
    public double getPrixTTC() {
        return prixTTC;
    }
    public void setPrixTTC(double prixTTC) {
        this.prixTTC = prixTTC;
    }
    public int getCounterDePlacesDisponibles() {
        return counterDePlacesDisponibles;
    }
    public void setCounterDePlacesDisponibles(int counterDePlacesDisponibles) {
        this.counterDePlacesDisponibles = counterDePlacesDisponibles;
    }
    public int getNumeroAutocar() {
        return numeroAutocar;
    }
    public void setNumeroAutocar(int numeroAutocar) {
        this.numeroAutocar = numeroAutocar;
    }
    

}
