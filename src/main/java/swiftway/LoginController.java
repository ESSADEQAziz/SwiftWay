package swiftway;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import swiftway.DB_connection.DBconnection;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void btnLogin() throws IOException{
      if(username.getText().equals("") || password.getText().equals("")){
          LoginController.Erreur();
        }else{
       Connection cnx=DBconnection.getConnection();
      try {
     Statement sqlStatement =cnx.createStatement();
     String query="select * from Admin;";
     ResultSet results=sqlStatement.executeQuery(query);
     System.out.println("Execution du query avec succes !!");

      while(results.next()){
        if(username.getText().equals(results.getString("nomUtilisateur")) &&
         password.getText().equals(results.getString("motDePasse"))){
            App.setRoot("Acceuil");
            break;
        }else{
            LoginController.Erreur();
            username.setText("");
            password.setText("");
        }
          
      }
    } catch (SQLException e) {
     System.out.println("Erreur D'execution du query !!");
     e.printStackTrace();
    }
  }
    }

    @FXML
    public void btnExit() throws IOException{
        LoginController.fermerProgramme();
    }

    public static void fermerProgramme(){
       Platform.runLater(()->{
        Alert confirmer = new Alert(Alert.AlertType.CONFIRMATION);
        confirmer.setTitle("Confirmation");
        confirmer.setContentText("Voulez vous vraiment quitter !");
        confirmer.getButtonTypes().removeAll(ButtonType.CANCEL,ButtonType.OK);
        ButtonType btnOui=new ButtonType("Oui");
        ButtonType btnNon=new ButtonType("Non");
        confirmer.getButtonTypes().addAll(btnOui,btnNon);
        Optional<ButtonType> resultat= confirmer.showAndWait();
        if(resultat.get() == btnOui)
          System.exit(0);
       });
    }
    public static void Erreur(){
       Platform.runLater(()->{
        Alert erreur=new Alert(Alert.AlertType.ERROR);
        erreur.setTitle("Erreur d'Authentification");
        erreur.setContentText("Username ou password incorrect.");
        erreur.show();
       });
    }
}
