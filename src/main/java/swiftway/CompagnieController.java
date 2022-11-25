package swiftway;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import swiftway.DB_connection.DBconnection;
import swiftway.Models.Companie;
import swiftway.Models.Offre;

public class CompagnieController implements Initializable{
    
    @FXML
    private TableColumn<Companie,String> nomDeSociete;

    @FXML
    private TableColumn<Companie,Integer> nombreDoffres;

    @FXML
    private TableView<Companie> table;

    @FXML
    private TableColumn<Companie,Integer> totalVehicules;
   
//=========================================================================================================================================================
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nomDeSociete.setCellValueFactory(new PropertyValueFactory<>("nomDeSociete"));
        totalVehicules.setCellValueFactory(new PropertyValueFactory<>("totalVehicules"));
        nombreDoffres.setCellValueFactory(new PropertyValueFactory<>("nombreDoffres"));
        ObservableList<Companie> list=FXCollections.observableArrayList(RemplireTable());
       table.setItems(list);
       
    }
//=========================================================================================================================================================
    public ArrayList<Companie> RemplireTable(){
        ArrayList<Companie> liste = new ArrayList<>();
        Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement1 =cnx.createStatement();
       Statement sqlStatement2 =cnx.createStatement();
       String query1="select * from offre;";
       String query2="select * from companie;";
       ResultSet results1=sqlStatement1.executeQuery(query1);
       ResultSet results2=sqlStatement2.executeQuery(query2);
       System.out.println("Execution du query avec succes !!");
       
       ArrayList<Offre> offres = new ArrayList<>();
       while (results1.next() != false) {
        offres.add(new Offre(Double.parseDouble(results1.getString("idOffre")), 
                Integer.valueOf(results1.getString("nbrDePlacesDisponible")),
                Double.parseDouble(results1.getString("prix")),
                new Companie(results1.getString("nomDeSociete"))));
       }

       HashMap<String,Integer> map =new HashMap<String,Integer>();
       for(int i=0 ;i<offres.size();i++){
          for(int j=i ;j<offres.size();j++){
           if(offres.get(i).companie.getNomDeSociete().equals(offres.get(j).companie.getNomDeSociete())){
             if(map.containsKey(offres.get(i).companie.getNomDeSociete())){
               map.replace(offres.get(i).companie.getNomDeSociete(), map.get(offres.get(i).companie.getNomDeSociete()), map.get(offres.get(i).companie.getNomDeSociete())+1);  
               offres.remove(j);
            }else{
               map.put(offres.get(i).companie.getNomDeSociete(), 1);
             }
           }
          }
      }
     
      while (results2.next() != false) {
        if(map.containsKey(results2.getString(1))){
           liste.add(new Companie(results2.getString(1),Integer.valueOf(results2.getString(2)),map.get(results2.getString(1))));
        }else{
            liste.add(new Companie(results2.getString(1),Integer.valueOf(results2.getString(2)),0)); 
        }
      }
        
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        e.printStackTrace();
       }
        return liste;
    }

//=========================================================================================================================================================
    @FXML
    void btnAjouter(ActionEvent event) throws Exception {
        Stage stage1 = new Stage();
        Scene scene = new Scene(App.loadFXML("AjouterCompanieView"), 500, 345);
        stage1.setTitle("Ajouter Compagnie");
        stage1.setScene(scene);
        stage1.show();  
    }

    @FXML
    void btnModifier(ActionEvent event) throws IOException {
        Stage stage1 = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierCompanieView"), 500, 415);
        stage1.setTitle("Modifier Compagnie");
        stage1.setScene(scene);
        stage1.show();  
    }

    @FXML
    void btnSupprimer(ActionEvent event) throws IOException {
        Stage stage1 = new Stage();
        Scene scene = new Scene(App.loadFXML("SupprimerCompanieView"), 500, 300);
        stage1.setTitle("Supprimer Compagnie");
        stage1.setScene(scene);
        stage1.show();  
    }
    @FXML
    void setRoottoAcceuil(MouseEvent event) throws IOException {
        App.setRoot("Acceuil");
    }

    @FXML
    void setRoottoOffre(MouseEvent event) throws IOException {
        App.setRoot("Offre");
    }

    @FXML
    void setRoottoProfile(MouseEvent event) throws IOException {
        App.setRoot("Profile");
    }

    @FXML
    void setRoottoStatistique(MouseEvent event) throws IOException {
        App.setRoot("Statistique");
    }
    @FXML
    void btnDeconnexion(ActionEvent event) {
       LoginController.fermerProgramme();
    }
}
