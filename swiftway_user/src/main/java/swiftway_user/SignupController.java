package swiftway_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import swiftway_user.connectivity.ConnectionClass;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    public TextField textField;
    @FXML
    public PasswordField passwordField;

    @FXML
    public Button button;
    public TextField fname, lname, phone, age, state, city;
    public RadioButton male;
    public ToggleGroup gender;
    public RadioButton female;
    public Button login;
    public TextField email;
    // @FXML
    // public TextField textField1;

    @FXML
    private void signup(ActionEvent actionEvent) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        try {

            Statement statement = connection.createStatement();

            PreparedStatement stmt = connection.prepareStatement("insert into user values (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, textField.getText());
            stmt.setString(2, passwordField.getText());
            stmt.setString(3, fname.getText());
            stmt.setString(4, lname.getText());
            stmt.setString(5, phone.getText());
            stmt.setString(6, age.getText());
            stmt.setString(7, state.getText());
            stmt.setString(8, city.getText());
            if (this.male.isSelected()) {
                stmt.setString(9, "Male");
            } else {
                stmt.setString(9, "Female");
            }
            stmt.setString(10, email.getText());

            int status = stmt.executeUpdate();
            if (status > 0) {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Success");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Invalid ");
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cancel(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
