package car_parking_system;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class Token_GenetatorController implements Initializable {

    @FXML
    private Button g_t1; // Button to generate token
    @FXML
    private Button d_p_r1; // Button to display receipt
    @FXML
    private TextField car_no1; // TextField for car number
    @FXML
    private Button btnBack; // Button to go back

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code can go here if needed
    }    

    @FXML
    private void SubmitAction(ActionEvent event) {
        String carNumber = car_no1.getText().trim(); // Get the car number from the text field

        if (carNumber.isEmpty()) {
            showAlert("Error", "Please enter a car number."); // Show error if empty
            return;
        }

        // Generate a unique token
        String token = generateToken(carNumber);
        showAlert("Token Generated", "Your token for car " + carNumber + " is: " + token);
    }

    @FXML
    private void ReceiptActionButton(ActionEvent event) {
        String carNumber = car_no1.getText().trim(); // Get the car number from the text field

        if (carNumber.isEmpty()) {
            showAlert("Error", "Please enter a car number to display the receipt."); // Show error if empty
            return;
        }

        // Generate a sample receipt
        String receipt = generateReceipt(carNumber);
        showAlert("Receipt", receipt);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close(); // Returns to the still-open Dashboard window
    }

    // Method to generate a token (using a random number with car number prefix)
    private String generateToken(String carNumber) {
        int randomNum = (int) (Math.random() * 9000) + 1000; // 4-digit random number
        return "TOK-" + carNumber + "-" + randomNum; // e.g., TOK-ABC123-5678
    }

    // Method to generate a receipt
    private String generateReceipt(String carNumber) {
        return "=== Parking Receipt ===\n" +
               "Date: " + LocalDate.now() + "\n" + // e.g., 2025-07-27
               "Time: " + LocalTime.now() + "\n" + // e.g., 03:44 PM +06 on July 27, 2025
               "Car Number: " + carNumber + "\n" +
               "Token: " + generateToken(carNumber) + "\n" +
               "======================";
    }

    // Method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}