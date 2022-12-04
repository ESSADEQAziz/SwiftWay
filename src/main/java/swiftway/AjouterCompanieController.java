package swiftway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import swiftway.DB_connection.DBconnection;

public class AjouterCompanieController {
    @FXML
    private TextField newSociete;

    @FXML
    private TextField totaleVehicules;

//=========================================================================================================================================================

    @FXML
    void btnAjouter(ActionEvent event) {
        Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       Statement sqlStatement2 =cnx.createStatement();
       String query="select * from companie;";
       
       ResultSet results=sqlStatement.executeQuery(query);
       
       System.out.println("Execution du query avec succes !!");
    if (newSociete.getText().equals("") || totaleVehicules.getText().equals("") ) {
        ErreurEmptyField();
        }else{
            int temp=0;
             while (results.next()!= false) {
         if(results.getString(1).equals(newSociete.getText())){
             ErreurCompagnie();
             System.out.println("la companie existe.");
            temp++;
            //break;
            //Car si on fait if(results.next()==false) malgre la derniere valeur dans la table verifie la condition du while au dessus le results.next()sera false malgre le break s'il existe;
         }
        System.out.println("eteration."); 
         }
         if (temp == 0) {
            String query2="insert into companie values ('"+newSociete.getText()+"','"+Integer.valueOf(totaleVehicules.getText())+"');";
            sqlStatement2.execute(query2);
            succesDajout();
            System.out.println("la societe abien ete ajoutee.");
            
             
        }
        }
      
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        e.printStackTrace();
       }

    }
//=========================================================================================================================================================

    private void ErreurCompagnie(){
        Platform.runLater(()->{
         Alert erreur=new Alert(Alert.AlertType.ERROR);
         erreur.setTitle("Erreur");
         erreur.setContentText("Cette compagnie et deja existe dans la base de donnees.");
         newSociete.setText("");
         totaleVehicules.setText("");
         erreur.show();
        });
     }
//=========================================================================================================================================================
     private void ErreurEmptyField(){
        Platform.runLater(()->{
         Alert erreur=new Alert(Alert.AlertType.ERROR);
         erreur.setTitle("Erreur");
         erreur.setContentText("Veuillez Remplir les zones de text.");
         newSociete.setText("");
         totaleVehicules.setText("");
         erreur.show();
        });
     }
//=========================================================================================================================================================

     private void succesDajout(){
        Platform.runLater(()->{
         Alert succes=new Alert(Alert.AlertType.INFORMATION);
         succes.setTitle("Succes D'ajout");
         succes.setHeaderText("Succes D'ajout");
         succes.setContentText("La societe a bien ete ajouter dans la base de donnees.");
         newSociete.setText("");
         totaleVehicules.setText("");
        succes.show();
        });
     }
}
