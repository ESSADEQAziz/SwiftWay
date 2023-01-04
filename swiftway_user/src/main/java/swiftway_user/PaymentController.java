package swiftway_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController extends BusController implements Initializable {
    public AnchorPane creditpane;
    public TextField cname;
    public TextField cnumber;
    public TextField cdate;
    public TextField camt;
    
    static boolean isVisible = true;
    public Button button;
    public TextField cvv;
    public ComboBox month;
    public ComboBox year;
    @FXML
    public ImageView credit;
    @FXML
    public ImageView h;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ToggleGroup radio = new ToggleGroup();
    
        button.setText("Proceed to pay");
        month.getItems().addAll("Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec");
        year.getItems().addAll(2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030);
        camt.setText(BusController.farex);

       

    }

    public void proceed(ActionEvent actionEvent) {

        if (this.cnumber.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter the Card Number ");
            alert.show();
        } else if (this.cname.getText().isEmpty()) {

            Alert alert1 = new Alert(Alert.AlertType.NONE);
            alert1.setAlertType(Alert.AlertType.ERROR);
            alert1.setContentText("Please Enter the CardHolder Name ");
            alert1.show();
        } else if (this.month.getItems().isEmpty()) {

            Alert alert2 = new Alert(Alert.AlertType.NONE);
            alert2.setAlertType(Alert.AlertType.ERROR);
            alert2.setContentText("Please Enter the Expiry Month ");
            alert2.show();
        } else if (this.year.getItems().isEmpty()) {

            Alert alert3 = new Alert(Alert.AlertType.NONE);
            alert3.setAlertType(Alert.AlertType.ERROR);
            alert3.setContentText("Please Enter the Expiry Year ");
            alert3.show();
        } else if (this.cvv.getText().isEmpty()) {

            Alert alert4 = new Alert(Alert.AlertType.NONE);
            alert4.setAlertType(Alert.AlertType.ERROR);
            alert4.setContentText("Please Enter the CVV ");
            alert4.show();

        } else if (this.camt.getText().isEmpty()) {

            Alert alert4 = new Alert(Alert.AlertType.NONE);
            alert4.setAlertType(Alert.AlertType.ERROR);
            alert4.setContentText("Please Enter the CVV ");
            alert4.show();

        }

        else {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Ticket Booked");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("book.fxml"));
                    Scene scene = new Scene(parent);
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void home(ActionEvent actionEvent) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("book.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logout1(ActionEvent actionEvent) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {

        }

    }

    public void cancel1(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("book.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
