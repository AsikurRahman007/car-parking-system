
/*
 * Click nfs://netbeans/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nfs://netbeans/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package car_parking_system;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    // Database connection details (replace with your own)
    private static final String URL = "jdbc:mysql://localhost:3306/parking_db";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

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

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Successfully done!");
            alert.showAndWait();

        } catch (SQLException e) {
            // Show error alert if database operation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save data: " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}