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

public class AjouterOffreController implements Initializable{
    @FXML
    private TextField nomDeSociete;

    @FXML
    private TextField placesDispo;

    @FXML
    private TextField prix;

    @FXML
    private TextField villeDarrivee;

    @FXML
    private TextField villeDepart;


 @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        
    }
//=========================================================================================================================================================

    @FXML
    void btnAjouter(ActionEvent event) {
        if (nomDeSociete.getText().equals("") || placesDispo.getText().equals("") || prix.getText().equals("")
        || villeDepart.getText().equals("") || villeDarrivee.getText().equals("")) {
            ErreurEmptyField();
        }else{
            Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       Statement sqlStatement2 =cnx.createStatement();
       Statement sqlStatement3 =cnx.createStatement();
       String query="select nomDeSociete from companie;";
       String query2="insert into offre (nbrDePlacesDisponible,prix,nomDeSociete,villeDepart,villeDarrivee) "+
       "values ("+Integer.parseInt(placesDispo.getText())+","+Double.parseDouble(prix.getText())+",'"+nomDeSociete.getText()+"','"+villeDepart.getText()+"','"+villeDarrivee.getText()+"');";
       String query3="select nomDeVille from ville;";
        
       int temp1=0;
       ResultSet results=sqlStatement.executeQuery(query);
       while (results.next()) {
        if (results.getString(1).equals(nomDeSociete.getText())) {
            temp1++;
            //break; 
            //Car si on fait if(results.next()==false) malgre la derniere valeur dans la table verifie la condition du while au dessus le results.next()sera false malgre le break s'il existe;
        }
       }
       if (temp1==0) {
           ErreurCompagnie(); 
       }else{
        ResultSet results3=sqlStatement3.executeQuery(query3);
        int tmp=0;
        while (results3.next()) {
            if (results3.getString(1).equals(villeDepart.getText()) || results3.getString(1).equals(villeDarrivee.getText())) {
                tmp++;
            }
        }
           if (tmp<2) {
               ErreurVille();
           }else{
            sqlStatement2.execute(query2);
            succesDajout();
           }
       }
       sqlStatement.close();
       sqlStatement2.close();
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        e.printStackTrace();
       }

        }

       
    }
//=========================================================================================================================================================
private void ErreurEmptyField(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Veuillez Remplir les zones de text.");
     nomDeSociete.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================

private void ErreurCompagnie(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Cette compagnie n'existe pas dans la base de donnees.");
      nomDeSociete.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================
private void ErreurVille(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Cette ville n'existe pas.");
      nomDeSociete.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================

   private void succesDajout(){
    Platform.runLater(()->{
     Alert succes=new Alert(Alert.AlertType.INFORMATION);
     succes.setTitle("Succes D'ajout");
     succes.setHeaderText("Succes D'ajout");
     succes.setContentText("L'Offre a bien ete ajouter dans la base de donnees.");
     nomDeSociete.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
    succes.show();
    });
 }
    
}
