package swiftway;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class StatistiqueController {
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
    } @FXML
    void btnDeconnexion(ActionEvent event) {
       LoginController.fermerProgramme();
    }
}
