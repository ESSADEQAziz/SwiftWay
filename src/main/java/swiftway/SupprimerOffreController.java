package swiftway;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import swiftway.DB_connection.DBconnection;

public class SupprimerOffreController implements Initializable {
     
    @FXML
    private TextField idOffre;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        
    }
    @FXML
    void btnSupprimer(ActionEvent event) {
        if(idOffre.getText().equals("")){
            ErreurEmptyField();
          }else{
         Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       Statement sqlStatement2 =cnx.createStatement();
       String query="select idOffre from offre;";
       String query2="delete from offre where idOffre= "+Integer.parseInt(idOffre.getText())+" ;";
       ResultSet results=sqlStatement.executeQuery(query);
       System.out.println("Execution du query avec succes !!");
       int temp=0;
       while (results.next()) {
           if (results.getString(1).equals(idOffre.getText())) {
              sqlStatement2.execute(query2);
              succesDeSuppression();
              temp++;
              //break;
              //Car si on fait if(results.next()==false) malgre la derniere valeur dans la table verifie la condition du while au dessus le results.next()sera false malgre le break s'il existe;
           }
       }
       if (temp==0) {
        ErreurID();
       }
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        e.printStackTrace();
       }
    }
    }
//=========================================================================================================================================================

private void succesDeSuppression(){
    Platform.runLater(()->{
     Alert succes=new Alert(Alert.AlertType.INFORMATION);
     succes.setTitle("Succes De Suppression");
     succes.setHeaderText("Succes De Suppression");
     succes.setContentText("L'Offre a bien ete Supprimee.");
     idOffre.setText("");
     succes.show();
    });
 }
//=========================================================================================================================================================
private void ErreurID(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Cette ID n'existe pas dans la base de donnees.");
     erreur.show();
    });
 }
//=========================================================================================================================================================
private void ErreurEmptyField(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Veuillez Remplir les zones de text.");
     erreur.show();
    });
 }
//=========================================================================================================================================================

}
