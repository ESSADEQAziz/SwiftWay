package swiftway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import swiftway.DB_connection.DBconnection;

public class SupprimerCompanieController {
    @FXML
    private TextField nomDeSociete;

    @FXML
    void btnSupprimer(ActionEvent event) {
        if (!nomDeSociete.getText().equals("")) {
            Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       Statement sqlStatement2 =cnx.createStatement();
       String query="select * from companie;";
       ResultSet results=sqlStatement.executeQuery(query);
       System.out.println("Execution du query avec succes !!");
       while (results.next()!= false) {
        if (results.getString(1).equals(nomDeSociete.getText())) {
            String query2 ="delete from companie where nomDeSociete='"+nomDeSociete.getText()+"';";
            sqlStatement2.execute(query2);
            succesDeSuppression();
            break;
        }
       }
       if (results.next()==false) {
        ErreurCompagnie();
       }
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        e.printStackTrace();
       } 
        }else{
            ErreurEmptyField();
        }
}
private void ErreurEmptyField(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Veuillez Remplir la zone de text.");
     erreur.show();
    });
 }

 private void ErreurCompagnie(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Cette compagnie n'existe pas dans la base de donnees.");
     nomDeSociete.setText("");
     erreur.show();
    });
 }

 private void succesDeSuppression(){
    Platform.runLater(()->{
     Alert succes=new Alert(Alert.AlertType.INFORMATION);
     succes.setTitle("Succes De Suppression");
     succes.setHeaderText("Succes De Suppression");
     succes.setContentText("La societe a bien ete supprimee dans la base de donnees.");
     nomDeSociete.setText("");
    succes.show();
    });
 }
}
