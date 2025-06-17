package car_parking_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField adminField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button login;

    @FXML
    private Button newCarParking;

    @FXML
    private void handleLogin(ActionEvent event) {
        String admin = adminField.getText();
        String password = passwordField.getText();

        if (admin.equals("admin") && password.equals("1234")) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, Admin!");
            // Optionally, switch to a dashboard scene here
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid admin name or password.");
        }
    }

    @FXML
    private void handleNewCarParking(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Navigation", "Redirecting to New Car Parking screen...");
        // You can load a new scene here using FXMLLoader if needed
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
