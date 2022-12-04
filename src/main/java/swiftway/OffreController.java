package swiftway;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.sql.Statement;

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
import swiftway.Models.Offre;

public class OffreController implements Initializable{
    @FXML
    private ImageView imageAdmin;
    @FXML
    private TableColumn<Offre, String> arrivee;

    @FXML
    private TableColumn<Offre,String> depart;

    @FXML
    private TableColumn<Offre,Double> idOffre;

    @FXML
    private TableColumn<Offre, Integer> placesDispo;

    @FXML
    private TableColumn<Offre, Double> prix;

    @FXML
    private TableColumn<Offre,String> societe;

    @FXML
    private TableView<Offre> table;

    @FXML
    private TextField search;

@Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            AcceuilController.AdminImage(imageAdmin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        idOffre.setCellValueFactory(new PropertyValueFactory<>("idOffre"));
        placesDispo.setCellValueFactory(new PropertyValueFactory<>("nbrDePlacesDisponibles"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        depart.setCellValueFactory(new PropertyValueFactory<>("villeDeDepart"));
        arrivee.setCellValueFactory(new PropertyValueFactory<>("villeDarrivee"));
        societe.setCellValueFactory(new PropertyValueFactory<>("nomSociete"));
        ObservableList<Offre> list=FXCollections.observableArrayList(RemplireTable());
        table.setItems(list);
        
         //1.The filter for the Search TextField :
       FilteredList<Offre> filteredData = new FilteredList<>(list, p -> true);

       // 2. Set the filter Predicate whenever the filter changes.
       search.textProperty().addListener((observable, oldValue, newValue) -> {
           filteredData.setPredicate(Companie -> {
               // If filter text is empty, display all persons.
               if (newValue == null || newValue.isEmpty()) {
                   return true;
               }

               // Compare first name and last name field in your object with filter.
               String lowerCaseFilter = newValue.toLowerCase();

               if (String.valueOf(Companie.getNomSociete()).toLowerCase().contains(lowerCaseFilter)) {
                   return true;
                   // Filter matches first name.

               } else if (String.valueOf(Companie.getIdOffre()).toLowerCase().contains(lowerCaseFilter)) {
                   return true; // Filter matches last name.
               } 

               return false; // Does not match.
           });
       });

       // 3. Wrap the FilteredList in a SortedList. 
       SortedList<Offre> sortedData = new SortedList<>(filteredData);

       // 4. Bind the SortedList comparator to the TableView comparator.
       sortedData.comparatorProperty().bind(table.comparatorProperty());
       // 5. Add sorted (and filtered) data to the table.
       table.setItems(sortedData);
    }

    public ArrayList<Offre> RemplireTable(){
        ArrayList<Offre> liste = new ArrayList<>();
        Connection cnx=DBconnection.getConnection();
        try {
            Statement sqlStatement =cnx.createStatement();
            String query="select * from offre;";
            ResultSet results=sqlStatement.executeQuery(query);
            System.out.println("Execution du query avec succes !!");
            while (results.next()) {
                liste.add(new Offre(Integer.parseInt(results.getString(1)), Integer.parseInt(results.getString(2)),
                 Double.parseDouble(results.getString(3)),results.getString(4),
                 results.getString(5), results.getString(6)));
            }
            sqlStatement.close();
        } catch (SQLException e) {
            System.out.println("Erreur D'execution du query !!");
            e.printStackTrace();
           }
        return liste;
    }


//=========================================================================================================================================================

@FXML
    void btnAjouter(ActionEvent event) throws IOException {
        Stage stage1 = new Stage();
        Scene scene = new Scene(App.loadFXML("AjouterOffreView"), 534, 430);
        stage1.setTitle("Ajouter offre");
        stage1.setScene(scene);
        stage1.show();  
    }

    @FXML
    void btnModifier(ActionEvent event) throws IOException {
        Stage stage1 = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierOffreView"), 534, 430);
        stage1.setTitle("Modifier offre");
        stage1.setScene(scene);
        stage1.show();  
    }

    @FXML
    void btnSupprimer(ActionEvent event) throws IOException {
        Stage stage1 = new Stage();
        Scene scene = new Scene(App.loadFXML("SupprimerOffreView"), 534, 359);
        stage1.setTitle("Supprimer Offre");
        stage1.setScene(scene);
        stage1.show();  

    }
//=========================================================================================================================================================

@FXML
    void exportIcon(MouseEvent event) {
        ArrayList<Offre> liste2=(ArrayList<Offre>)RemplireTable().clone();
        HSSFWorkbook wb =new HSSFWorkbook();
        HSSFSheet sheet=wb.createSheet("SwiftWay_Offres");
        HSSFRow header =sheet.createRow(0);
        header.createCell(0).setCellValue("ID Offre");
        header.createCell(1).setCellValue("Places disponibles");
        header.createCell(2).setCellValue("Prix");
        header.createCell(3).setCellValue("Nom de Societe.");
        header.createCell(4).setCellValue("Ville Depart");
        header.createCell(5).setCellValue("Ville D'arrivee");

        for (int i = 1; i <= liste2.size(); i++) {
            HSSFRow row =sheet.createRow(i);
            row.createCell(0).setCellValue(liste2.get(i-1).getIdOffre());
            row.createCell(1).setCellValue(liste2.get(i-1).getNbrDePlacesDisponibles());
            row.createCell(2).setCellValue(liste2.get(i-1).getPrix());
            row.createCell(3).setCellValue(liste2.get(i-1).getNomSociete());
            row.createCell(4).setCellValue(liste2.get(i-1).getVilleDeDepart());
            row.createCell(5).setCellValue(liste2.get(i-1).getVilleDarrivee());
        }
        try {
            
            OutputStream fileOut=new FileOutputStream("SwiftWay_Offres.xls");
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
        App.setRoot("Offre");
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
