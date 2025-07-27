package car_parking_system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DasboardController implements Initializable {

    @FXML
    private TextField on1; // Owner Name
    @FXML
    private TextField cn1; // Car Name
    @FXML
    private TextField cn11; // Car Number
    @FXML
    private DatePicker ed1; // Entry Date
    @FXML
    private DatePicker ed11; // Exit Date
    @FXML
    private Button sub1; // Submit Button
    @FXML
    private Button tg1; // Token Generator Button

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/parking_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    @FXML
    private Button rs1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Add initialization code if needed
    }    

    @FXML
    private void SubmitActionButton(ActionEvent event) {
        String ownerName = on1.getText();
        String carName = cn1.getText();
        String carNumber = cn11.getText();
        String entryDate = ed1.getValue() != null ? ed1.getValue().toString() : "Not set";
        String exitDate = ed11.getValue() != null ? ed11.getValue().toString() : "Not set";

        // Connect to database and insert data
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO parking_records (owner_name, car_name, car_number, entry_date, exit_date) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, ownerName);
            stmt.setString(2, carName);
            stmt.setString(3, carNumber);
            stmt.setString(4, entryDate);
            stmt.setString(5, exitDate);
            stmt.executeUpdate();

            // Open All Records page
            URL resource = getClass().getResource("AllRecords.fxml");
            if (resource == null) {
                throw new IOException("AllRecords.fxml not found in classpath");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("All Records");
            stage.setScene(new Scene(root));
            stage.show();

            // Do not close the current window
            // Stage currentStage = (Stage) sub1.getScene().getWindow();
            // currentStage.close();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save data: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load All Records page: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void GeneratingActionButton(ActionEvent event) {
        try {
            URL resource = getClass().getResource("Token_Genetator.fxml");
            if (resource == null) {
                throw new IOException("Token_Generator.fxml not found in classpath");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Token Generator");
            stage.setScene(new Scene(root));
            stage.show();

            // Do not close the current window
            // Stage currentStage = (Stage) tg1.getScene().getWindow();
            // currentStage.close();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Token Generator page: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void RelesedActionButton(ActionEvent event) {
        try {
            URL resource = getClass().getResource("Relesed_Parking.fxml");
            if (resource == null) {
                throw new IOException("Released_Parking.fxml not found in classpath");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Released Parking");
            stage.setScene(new Scene(root));
            stage.show();

            // Do not close the current window
            // Stage currentStage = (Stage) rs1.getScene().getWindow();
            // currentStage.close();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to open Released Parking page: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}