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

public class ModifierOffreController implements Initializable {
    @FXML
    private TextField idOffre;

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
    @FXML
    void btnModifier(ActionEvent event) {
        if (idOffre.getText().equals("") || placesDispo.getText().equals("") || prix.getText().equals("")
        || villeDepart.getText().equals("") || villeDarrivee.getText().equals("")) {
            ErreurEmptyField();
        }else{
            Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       Statement sqlStatement2 =cnx.createStatement();
       Statement sqlStatement3 =cnx.createStatement();
       String query="select idOffre from offre;";
       String query2="select nomDeVille from ville;";
       String query3="update offre set nbrDePlacesDisponible= "+Integer.parseInt(placesDispo.getText())+" ,prix= "+Double.parseDouble(prix.getText())+" , villeDepart= '"+villeDepart.getText()+"' , villeDarrivee= '"+villeDarrivee.getText()
       +"'  where idOffre= "+Integer.parseInt(idOffre.getText())+";";
       ResultSet results=sqlStatement.executeQuery(query);
       int temp1=0; 
       while (results.next()) {
        if (results.getString(1).equals(idOffre.getText())) {
            temp1++;
            //break;
            //Car si on fait if(results.next()==false) malgre la derniere valeur dans la table verifie la condition du while au dessus le results.next()sera false malgre le break s'il existe;
        }
       }
       if (temp1 == 0) {  
        ErreurID();
       }else{
        ResultSet results2= sqlStatement2.executeQuery(query2);
        int temp=0;
         while (results2.next()) {
            if (results2.getString(1).equals(villeDepart.getText()) || results2.getString(1).equals(villeDarrivee.getText())  ) {
                temp++;
            }
         }
         if (temp<2) {
            ErreurVille();
         }else{
             sqlStatement3.execute(query3);
             succesDeModifier();
         }
       }

      }catch (SQLException e) {
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
     idOffre.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================
private void ErreurID(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Cette ID n'existe pas dans la base de donnees.");
      idOffre.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================

private void succesDeModifier(){
    Platform.runLater(()->{
     Alert succes=new Alert(Alert.AlertType.INFORMATION);
     succes.setTitle("Succes De Modification");
     succes.setHeaderText("Succes De Modification");
     succes.setContentText("L'Offre a bien ete Modifiee dans la base de donnees.");
     idOffre.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
    succes.show();
    });
 }
 //=========================================================================================================================================================
private void ErreurVille(){
    Platform.runLater(()->{
     Alert erreur=new Alert(Alert.AlertType.ERROR);
     erreur.setTitle("Erreur");
     erreur.setContentText("Cette ville n'existe pas.");
      idOffre.setText("");
     placesDispo.setText("");
     prix.setText("");
     villeDepart.setText("");
     villeDarrivee.setText("");
     erreur.show();
    });
 }
//=========================================================================================================================================================  
}
