package swiftway;

import java.io.IOException;
<<<<<<< HEAD
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import swiftway.DB_connection.DBconnection;
import swiftway.Models.Companie;
import swiftway.Models.Offre;

public class AcceuilController  implements Initializable{
   
    @FXML
    private PieChart CompagniesChart;

    @FXML
    private Label nbrOffre;

    private ObservableList<PieChart.Data> data;

    @FXML
    private NumberAxis AxeY;

    @FXML
    private CategoryAxis axeX;

    @FXML
    private BarChart barchart;

   @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        RemplirPieChart_etLabel();
        RemplirBarChart();
    }

         // La Methode qui Remplie le Barchart avec des donnees existants dans une base de donnees:
    
     public void RemplirBarChart(){
        XYChart.Series xyMinPrix = new XYChart.Series<>();
        XYChart.Series xyMaxPrix = new XYChart.Series<>();
        xyMinPrix.setName("PrixMin");
        xyMaxPrix.setName("PrixMax");
        
        HashMap<String,ArrayList<Double>> mapprix =new HashMap<String,ArrayList<Double>>();

        for (Offre temp : offres) {
                   if(mapprix.containsKey(temp.companie.getNomDeSociete())){
                    mapprix.get(temp.companie.getNomDeSociete()).add(temp.getPrix());
            }else{
                ArrayList<Double> array=new ArrayList<>();
                array.add(temp.getPrix());
                mapprix.put(temp.companie.getNomDeSociete(),array);
            }
        }

        for (Map.Entry<String,ArrayList<Double>> mapentry : mapprix.entrySet()) {
            xyMinPrix.getData().add(new XYChart.Data<>(mapentry.getKey(),Collections.min(mapentry.getValue())));
            xyMaxPrix.getData().add(new XYChart.Data<>(mapentry.getKey(),Collections.max(mapentry.getValue())));
          } 
          barchart.getData().addAll(xyMaxPrix,xyMinPrix);
    }

         //La Methode qui Remplie le PieChart avec des donnees existants dans une Base de Donnees et Le Label qui represent le nombre d'Offre:
               ArrayList<Offre> offres=new ArrayList<>();
     public void RemplirPieChart_etLabel(){
        data=FXCollections.observableArrayList();
         //Connexion au Base de donnees pour remplir les offres dans une ArrayList 'Offres'
        Connection connection=DBconnection.getConnection();
        try {
            Statement statement=connection.createStatement();
            String query="select * from Offre;";
            ResultSet results=statement.executeQuery(query);
            while (results.next() != false) {
                offres.add(new Offre(Double.parseDouble(results.getString("idOffre")), 
                Integer.valueOf(results.getString("nbrDePlacesDisponible")),
                Double.parseDouble(results.getString("prix")),
                new Companie(results.getString("nomDeSociete"))));
            } 
        }catch (SQLException e) {
            System.out.println("Erreur D'execution du query !!");
            e.printStackTrace();
        }
          //Remplire le Label correspondant au Nombre d'Offre:
          nbrOffre.setText(offres.size()+"");
          //Apres avoir L'ArrayList Offres ,Construire le Piechart selon le nom de societe et le nombre des offres aui offert:  
        HashMap<String,Integer> map =new HashMap<String,Integer>();
        for(int i=0 ;i<offres.size();i++){
           for(int j=i ;j<offres.size();j++){
            if(offres.get(i).companie.getNomDeSociete().equals(offres.get(j).companie.getNomDeSociete())){
              if(map.containsKey(offres.get(i).companie.getNomDeSociete())){
                map.replace(offres.get(i).companie.getNomDeSociete(), map.get(offres.get(i).companie.getNomDeSociete()), map.get(offres.get(i).companie.getNomDeSociete())+1);  
              }else{
                map.put(offres.get(j).companie.getNomDeSociete(), 1);
              }
            }
           }
       }
       for (Map.Entry<String,Integer> mapentry : map.entrySet()) {
          data.add(new PieChart.Data(mapentry.getKey(),mapentry.getValue()));
        }
       CompagniesChart.setData(data);
    }


=======

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AcceuilController {
>>>>>>> f9aa716feb6ea6b1c33a7fd21533ab69a9360d82
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
    void setRoottoStatistique(MouseEvent event) throws IOException {
        App.setRoot("Statistique");
    }
    @FXML
    void btnDeconnexion(ActionEvent event) {
       LoginController.fermerProgramme();
    }
<<<<<<< HEAD
    
=======
>>>>>>> f9aa716feb6ea6b1c33a7fd21533ab69a9360d82
}
