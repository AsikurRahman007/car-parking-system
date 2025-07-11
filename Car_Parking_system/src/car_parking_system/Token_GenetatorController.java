package car_parking_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

        // Logic to generate a token (for demonstration, we just show a message)
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

        // Logic to display receipt (for demonstration, we just show a message)
        showAlert("Receipt", "Receipt for car " + carNumber + " has been generated.");
    }

    // Method to generate a token (for demonstration purposes)
    private String generateToken(String carNumber) {
        // In a real application, you would generate a unique token
        return "TOKEN-" + carNumber.hashCode(); // Simple token generation using hash code
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
