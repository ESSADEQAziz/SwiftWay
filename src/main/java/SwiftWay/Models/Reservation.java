package SwiftWay.Models;

public class Reservation {
       // Les attributs de la classe :

    private double idReservation;
    private int nbrDePassagers;

       // Le constructeur de la classe :

    public Reservation(double idReservation, int nbrDePassagers) {
        this.idReservation = idReservation;
        this.nbrDePassagers = nbrDePassagers;
    }
       // La depandence avec la classe Voyageur :
    public Voyajeur voyajeur;

       // La depandence avec la classe Paiment a travert La classe d'association Ticket :

       public Ticket ticket;
       
       // Les getters et les setters :

    public double getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(double idReservation) {
        this.idReservation = idReservation;
    }
    public int getNbrDePassagers() {
        return nbrDePassagers;
    }
    public void setNbrDePassagers(int nbrDePassagers) {
        this.nbrDePassagers = nbrDePassagers;
    }
       // Les methodes de la classe :

    public void choisirOffre(){
    }
    public void annulerOffre(){

    }
    
    
}
