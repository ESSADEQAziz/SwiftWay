package swiftway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import swiftway.DB_connection.DBconnection;

public class ModifierCompanieController {
    @FXML
    private TextField newNbrVehicules;

    @FXML
    private TextField newNomSociete;

    @FXML
    private TextField oldNomSociete;
//=========================================================================================================================================================

    @FXML
    void btnModifier(ActionEvent event) {
    if (newNomSociete.getText().equals("") || oldNomSociete.getText().equals("")) {
           ErreurEmptyField();
    }else{
        Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       Statement sqlStatement2 =cnx.createStatement();
       String query="select * from companie;";
       
       ResultSet results=sqlStatement.executeQuery(query);
       while (results.next()!= false) {
            if(results.getString(1).equals(oldNomSociete.getText())){
            String query2="update companie set nomDeSociete='"+newNomSociete.getText()+"',totalVehicules='"+ Integer.valueOf(newNbrVehicules.getText())+"'  where nomDeSociete='"+oldNomSociete.getText()+"';";
            sqlStatement2.execute(query2);
            succesDeModification();
            break;
        } 
    }
        if(results.next()==false){
          ErreurCompagnie();
       }
       
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        e.printStackTrace();
       }
    }
}
//=========================================================================================================================================================

private void ErreurCompagnie(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Cette compagnie n'existe pas dans la base de donnees.");
     oldNomSociete.setText("");
     newNomSociete.setText("");
     newNbrVehicules.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================

private void ErreurEmptyField(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Veuillez Remplir les zones de text.");
     oldNomSociete.setText("");
     newNomSociete.setText("");
     newNbrVehicules.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================

 private void succesDeModification(){
    Platform.runLater(()->{
     Alert succes=new Alert(Alert.AlertType.INFORMATION);
     succes.setTitle("Succes De Modification");
     succes.setHeaderText("Succes De Modification");
     succes.setContentText("La societe a bien ete Modifier dans la base de donnees.");
     oldNomSociete.setText("");
     newNomSociete.setText("");
     newNbrVehicules.setText("");
    succes.show();
    });
 }
}
