package swiftway;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import swiftway.DB_connection.DBconnection;

public class ModifierInfosProfileController implements Initializable{
    @FXML
    private TextField ancienneValeur;

    @FXML
    private TextField nouvelleValeur;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    
    }

    @FXML
    void btnModifier(ActionEvent event) {
        if (ancienneValeur.getText().equals("") || nouvelleValeur.getText().equals("")) {
            ErreurEmptyField();
        }else{
            Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       String query="";
       int temp=0;
       switch (ProfileController.crayonSelectionnee) {
        case "crayonEmail":

            Statement sqlsStatement1=cnx.createStatement();
            ResultSet res1=sqlsStatement1.executeQuery("select email from admin;");
            while (res1.next()) {
                if (res1.getString(1).equals(ancienneValeur.getText())) {
                    temp++;
                }
            }
            if (temp == 0) {
                ErreurValeur();
            }else{
            query="update admin set email='"+nouvelleValeur.getText()+"' where email='"+ancienneValeur.getText()+"';";
            EnvoieMail("Email");
            }
            break;
        case "crayonNom":

        Statement sqlsStatement2=cnx.createStatement();
        ResultSet res2=sqlsStatement2.executeQuery("select nom from admin;");
        while (res2.next()) {
            if (res2.getString(1).equals(ancienneValeur.getText())) {
                temp++;
            }
        }
        if (temp == 0) {
            ErreurValeur();
        }else{
        query="update admin set nom='"+nouvelleValeur.getText()+"' where nom='"+ancienneValeur.getText()+"';";   
        EnvoieMail("Nom");
        }   
            break;
        case "crayonNomUtilisateur":

        Statement sqlsStatement3=cnx.createStatement();
        ResultSet res3=sqlsStatement3.executeQuery("select nomUtilisateur from admin;");
        while (res3.next()) {
            if (res3.getString(1).equals(ancienneValeur.getText())) {
            LoginController.user=nouvelleValeur.getText();
                temp++;
            }
        }
        if (temp == 0) {
            ErreurValeur();
        }else{
        query="update admin set nomUtilisateur='"+nouvelleValeur.getText()+"' where nomUtilisateur='"+ancienneValeur.getText()+"';";   
        EnvoieMail("NomUtilisateur");
        }
            break;
        case "crayonPassword":

        Statement sqlsStatement4=cnx.createStatement();
        ResultSet res4=sqlsStatement4.executeQuery("select motDepasse from admin;");
        while (res4.next()) {
            if (res4.getString(1).equals(ancienneValeur.getText())) {
                temp++;
            }
        }
        if (temp == 0) {
            ErreurValeur();
        }else{
        query="update admin set motDepasse='"+nouvelleValeur.getText()+"' where motDepasse='"+ancienneValeur.getText()+"';";   
        EnvoieMail("PassWord");
        }
            break;
        case "crayonPrenom":

        Statement sqlsStatement5=cnx.createStatement();
        ResultSet res5=sqlsStatement5.executeQuery("select prenom from admin;");
        while (res5.next()) {
            if (res5.getString(1).equals(ancienneValeur.getText())) {
                temp++;
            }
        }
        if (temp == 0) {
            ErreurValeur();
        }else{
        query="update admin set prenom='"+nouvelleValeur.getText()+"' where prenom='"+ancienneValeur.getText()+"';";   
        EnvoieMail("Prenom");
        }
            break;       
        default:
         System.out.println("Erreur !!");
            break;
       }   

       if (! query.equals("")) {
        System.out.println("00000");
        sqlStatement.execute(query);
        System.out.println("11111");
        succesDeModification();
       }
      
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        ErreurValeur();
        e.printStackTrace();
       }
        }
    }

//===============================================================================================================================
    private String userName="aziz.xfor@gmail.com"; // The sending email
    private String passWord="shteyftlvicuwuef";//generate a password for the application (16 char) after the activation of '2 step verification'.

    public void EnvoieMail(String field){
    //Etape 1 Creation de la session :
        Properties prop=new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        prop.put("mail.smtp.starttls.enable", "true");
        
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, passWord);
                    }
                });
                
    //Etape 2 La creation d'un message :
    Message message= new MimeMessage(session);
    try {
        Connection cnx =DBconnection.getConnection();
        Statement sqlStatement =cnx.createStatement();
     String query="select email from admin where nomUtilisateur='"+LoginController.user+"';";
     ResultSet results=sqlStatement.executeQuery(query);
     System.out.println("Execution du query avec succes !!");
      
        
     while (results.next()) {
        message.setFrom(new InternetAddress("aziz.xfor@gmail.com"));
       message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(results.getString("email")));
       message.setSubject(field+" a bien ete modifiee .");
       message.setText("Votre nouveau "+field+" est : "+nouvelleValeur.getText()+" .");
      }
        
       

    //Etape 3 Envoie de message :
    Transport.send(message);
    System.out.println("Message Envoiee avec succee.");
    } catch (AddressException e) {
        e.printStackTrace();
    } catch (MessagingException e) {
        e.printStackTrace();
    }catch (SQLException e) {
     System.out.println("Erreur D'execution du query !!");
     e.printStackTrace();
    }
    
    }

//===============================================================================================================================
    private void ErreurValeur(){
        Platform.runLater(()->{
         Alert erreur=new Alert(Alert.AlertType.ERROR);
         erreur.setTitle("Erreur");
         erreur.setContentText("L'encienne valeur est erronee. ");
         erreur.show();
         ancienneValeur.setText("");
         nouvelleValeur.setText("");
        });
     }

     private void ErreurEmptyField(){
        Platform.runLater(()->{
         Alert erreur=new Alert(Alert.AlertType.ERROR);
         erreur.setTitle("Erreur");
         erreur.setContentText("Veuillez Remplir les zones de text.");
         ancienneValeur.setText("");
         nouvelleValeur.setText("");
         erreur.show();
        });
     }

     private void succesDeModification(){
        Platform.runLater(()->{
         Alert succes=new Alert(Alert.AlertType.INFORMATION);
         succes.setTitle("Succes De Modification");
         succes.setHeaderText("Succes De Modification");
         succes.setContentText("La valeur a ete bien Modifiee.");
         succes.show();
         ancienneValeur.setText("");
         nouvelleValeur.setText("");
        });
     }

    
}
