package car_parking_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization if needed
    }

    @FXML
   private void handleLogin(ActionEvent event) {
    String admin = adminField.getText();
    String password = passwordField.getText();

    if (admin.equals("admin") && password.equals("1234")) {
        showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, Admin!");

        try {
            Parent dashboardRoot = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Dashboard");
            dashboardStage.setScene(new Scene(dashboardRoot));
            dashboardStage.show();

            // Close login window
            Stage loginStage = (Stage) login.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to load Dashboard.");
        }

    } else {
        showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid admin name or password.");
    }
}

    @FXML
    private void handleNewCarParking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("New_Car_Parking.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("New Car Parking");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to load New Car Parking screen.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 