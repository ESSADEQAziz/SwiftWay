package swiftway;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import swiftway.DB_connection.DBconnection;
import swiftway.Models.Companie;
import swiftway.Models.Offre;

public class CompagnieController implements Initializable{
    @FXML
    private ImageView imageAdmin;
    
    @FXML
    private TableColumn<Companie,String> nomDeSociete;

    @FXML
    private TableColumn<Companie,Integer> nombreDoffres;

    @FXML
    private TableView<Companie> table;

    @FXML
    private TableColumn<Companie,Integer> totalVehicules;
   
//=========================================================================================================================================================
@FXML
private TextField search;
@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            AcceuilController.AdminImage(imageAdmin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nomDeSociete.setCellValueFactory(new PropertyValueFactory<>("nomDeSociete"));
        totalVehicules.setCellValueFactory(new PropertyValueFactory<>("totalVehicules"));
        nombreDoffres.setCellValueFactory(new PropertyValueFactory<>("nombreDoffres"));
        ObservableList<Companie> list=FXCollections.observableArrayList(RemplireTable());
       table.setItems(list);

         //1.The filter for the Search TextField :
       FilteredList<Companie> filteredData = new FilteredList<>(list, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Companie -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Companie.getNomDeSociete()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                } else if (String.valueOf(Companie.getNombreDoffres()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Companie> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

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
        offres.add(new Offre(Integer.parseInt(results1.getString("idOffre")), 
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
        Stage stage2 = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierCompanieView"), 500, 415);
        stage2.setTitle("Modifier Compagnie");
        stage2.setScene(scene);
        stage2.show();  
    }

    @FXML
    void btnSupprimer(ActionEvent event) throws IOException {
        Stage stage3 = new Stage();
        Scene scene = new Scene(App.loadFXML("SupprimerCompanieView"), 500, 340);
        stage3.setTitle("Supprimer Compagnie");
        stage3.setScene(scene);
        stage3.show();  
    }
    //=========================================================================================================================================================
    @FXML
    void exportIcon(MouseEvent event) {
        ArrayList<Companie> liste2=(ArrayList<Companie>)RemplireTable().clone();
        HSSFWorkbook wb =new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("SwiftWay_Compagnies");
        HSSFRow header =sheet.createRow(0);
        header.createCell(0).setCellValue("Nom De Societe");
        header.createCell(1).setCellValue("Totale Vehicules");
        header.createCell(2).setCellValue("Nombre D'Offres");

        for (int i = 1; i <= liste2.size(); i++) {
            HSSFRow row =sheet.createRow(i);
            row.createCell(0).setCellValue(liste2.get(i-1).getNomDeSociete());
            row.createCell(1).setCellValue(liste2.get(i-1).getTotalVehicules());
            row.createCell(2).setCellValue(liste2.get(i-1).getNombreDoffres());
        }
        try {
            
            OutputStream fileOut=new FileOutputStream("SwiftWay_Compagnies.xls");
                       // The name of the file can have an '.xls' or '.xlsx' extention depend on version.
            wb.write(fileOut);
            succesDexportation();
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("1111");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("222");
            e.printStackTrace();
        }
    }
    //=========================================================================================================================================================

    @FXML
    void refreshIcon(MouseEvent event) throws IOException {
        App.setRoot("Compagnie");
    }
    //=========================================================================================================================================================
   
    private void succesDexportation(){
        Platform.runLater(()->{
         Alert succes=new Alert(Alert.AlertType.INFORMATION);
         succes.setTitle("Exportation");
         succes.setHeaderText("Succes D'Exportation");
         succes.setContentText("Les informations ont ete bien exportee en fichier Excel.");
        succes.show();
        });
     }
    //=========================================================================================================================================================
   
    @FXML
    void setRoottoAcceuil(MouseEvent event) throws IOException {
        App.setRoot("Acceuil");
    }

    @FXML
    void setRoottoCompagnie(MouseEvent event) throws IOException {
        App.setRoot("Compagnie");
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
    void btnDeconnexion(ActionEvent event) {
       LoginController.fermerProgramme();
    }
}
