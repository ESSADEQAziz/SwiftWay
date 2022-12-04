package swiftway;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import swiftway.DB_connection.DBconnection;

public class ProfileController implements Initializable{

    @FXML
    private ImageView imageAdmin;

    @FXML
    private ImageView imageProfile;

    @FXML
    private Label email;

    @FXML
    private Label nom;

    @FXML
    private Label nomUtilisateur;

    @FXML
    private Label password;

    @FXML
    private Label prenom;

    @FXML
    private ImageView crayonEmail;

    @FXML
    private ImageView crayonNom;

    @FXML
    private ImageView crayonNomUtilisateur;

    @FXML
    private ImageView crayonPassword;

    @FXML
    private ImageView crayonPrenom;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            AcceuilController.AdminImage(imageAdmin);
            AcceuilController.AdminImage(imageProfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        RemplirLabels();
    }



    @FXML
    public static String crayonSelectionnee;

    @FXML
    void ModifierInfosEmail(MouseEvent event) throws IOException {
        crayonSelectionnee="crayonEmail";
        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierInfosProfile"), 521, 394);
        stage.setTitle("Modifier Informations ");
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    void ModifierInfosNom(MouseEvent event) throws IOException {
        crayonSelectionnee="crayonNom";
        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierInfosProfile"), 521, 394);
        stage.setTitle("Modifier Informations ");
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    void ModifierInfosNomUtilisateur(MouseEvent event) throws IOException {
        crayonSelectionnee="crayonNomUtilisateur";
        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierInfosProfile"), 521, 394);
        stage.setTitle("Modifier Informations ");
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    void ModifierInfosPassword(MouseEvent event) throws IOException {
        crayonSelectionnee="crayonPassword";
        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierInfosProfile"), 521, 394);
        stage.setTitle("Modifier Informations ");
        stage.setScene(scene);
        stage.show();  
    }

    @FXML
    void ModifierInfosPrenom(MouseEvent event) throws IOException {
        crayonSelectionnee="crayonPrenom";
        Stage stage = new Stage();
        Scene scene = new Scene(App.loadFXML("ModifierInfosProfile"), 521, 394);
        stage.setTitle("Modifier Informations ");
        stage.setScene(scene);
        stage.show();  
    }

    public void RemplirLabels(){
        Connection cnx=DBconnection.getConnection();
        try {
       Statement sqlStatement =cnx.createStatement();
       String query="select * from admin where nomUtilisateur='"+LoginController.user+"';";
       ResultSet results=sqlStatement.executeQuery(query);
       System.out.println("Execution du query avec succes !!");
       while (results.next()) {
         nomUtilisateur.setText(results.getString(1));
       password.setText("XXXXXXXX");
       nom.setText(results.getString(3));
       prenom.setText(results.getString(4));
       email.setText(results.getString(6));
       }
    } catch (SQLException e) {
        System.out.println("Erreur D'execution du query !!");
        e.printStackTrace();
       }
    }   

    public void ModifierAdminImage() throws SQLException, FileNotFoundException{
        Connection cnx=DBconnection.getConnection();
        FileChooser fileChooser=new FileChooser();
       fileChooser.getExtensionFilters().add(new ExtensionFilter("Images", "*.png","*.PNG","*.jpg","*.JPG","*.jpeg"));
       File file = fileChooser.showOpenDialog(null);
       
       
       if(file != null){
        Image image=new Image(file.toURI().toString());
        imageAdmin.setImage(image);
        PreparedStatement ps = cnx.prepareStatement("Update admin set image= ?  where nomUtilisateur='"+LoginController.user+"';");
       FileInputStream fin = new FileInputStream(file);
       int len = (int)file.length();
       ps.setBinaryStream(1,fin,len);
       int i=ps.executeUpdate();
       }
        AcceuilController.AdminImage(imageProfile);
    
    }
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
