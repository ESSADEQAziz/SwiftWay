package swiftway;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import swiftway.DB_connection.DBconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
      //  launch();
      Connection cnx=DBconnection.getConnection();
      try {
     Statement sqlStatement =cnx.createStatement();
     String query="insert into Admin values ('admin77','admin77');";
     sqlStatement.executeUpdate(query);
     System.out.println("Execution du query avec succes !!");
     sqlStatement.close();
      
 } catch (SQLException e) {
     System.out.println("Erreur D'execution du query !!");
     e.printStackTrace();
 } 
  }
    

}