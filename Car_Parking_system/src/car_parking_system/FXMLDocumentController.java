package car_parking_system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
    }

    @FXML
    private void handleLogin(ActionEvent event) throws SQLException {
        String username = adminField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all blank fields");
            return;
        }

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            prepare.setString(2, password);
            result = prepare.executeQuery();

            if (result.next()) {
                // Login successful - open Dashboard
                Parent dashboardRoot = FXMLLoader.load(getClass().getResource("Dasboard.fxml"));
                Stage dashboardStage = new Stage();
                dashboardStage.setTitle("Dashboard");
                dashboardStage.setScene(new Scene(dashboardRoot));
                dashboardStage.show();

                // Close login window
                Stage loginStage = (Stage) login.getScene().getWindow();
                loginStage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to process login.");
        } finally {
            // Clean up resources
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
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