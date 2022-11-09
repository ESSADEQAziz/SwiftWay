package SwiftWay.Models;

import java.util.Date;

public class Paiment {
       // Les attributs de la classe :

    private String email;
    private double codeCarte;
    private int CCV;
    private Date dateDexpiration;
    private double idPaiment;

       // Le constructeur de la classe :

    public Paiment(double idPaiment, String email, double codeCarte, int cCV, Date dateDexpiration) {
        this.idPaiment = idPaiment;
        this.email = email;
        this.codeCarte = codeCarte;
        CCV = cCV;
        this.dateDexpiration = dateDexpiration;
    }
       // La depandence avec la classe Reservation a travert La classe d'association Ticket :
    
    public Ticket ticket;
    
       // Les getters et les setters :

    public double getIdPaiment() {
        return idPaiment;
    }
    public void setIdPaiment(double idPaiment) {
        this.idPaiment = idPaiment;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getCodeCarte() {
        return codeCarte;
    }
    public void setCodeCarte(double codeCarte) {
        this.codeCarte = codeCarte;
    }
    public int getCCV() {
        return CCV;
    }
    public void setCCV(int cCV) {
        CCV = cCV;
    }
    public Date getDateDexpiration() {
        return dateDexpiration;
    }
    public void setDateDexpiration(Date dateDexpiration) {
        this.dateDexpiration = dateDexpiration;
    }
  
       // Les methodes de la classe :

    public void traiterPaiment(){
    }
    
    
}
