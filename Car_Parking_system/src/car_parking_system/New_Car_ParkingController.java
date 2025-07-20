package car_parking_system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class New_Car_ParkingController implements Initializable {

    @FXML
    private TextField cno1; // Car Number
    @FXML
    private TextField fulln1; // Full Name
    @FXML
    private RadioButton m11; // Male
    @FXML
    private RadioButton f11; // Female
    @FXML
    private RadioButton o11; // Others
    @FXML
    private TextField p11; // Position
    @FXML
    private ComboBox<String> b11; // Brand
    @FXML
    private TextField l11; // Lap Number
    @FXML
    private TextField t11; // Track Number
    @FXML
    private TextField mobileNumberField; // Mobile Number
    @FXML
    private Button ok1;
    @FXML
    private Button tg11; // Generate button
    private ToggleGroup genderGroup;
    @FXML
    private Button Rel_Parking1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate brands in the ComboBox
        b11.getItems().addAll("Toyota", "BMW", "Hyundai", "Honda", "Mercedes", "Other");
        b11.setPromptText("Select Brand");

        // Set ToggleGroup for gender selection
        m11.setToggleGroup(genderGroup);
        f11.setToggleGroup(genderGroup);
        o11.setToggleGroup(genderGroup);
    }

    private void handleOkButton(ActionEvent event) {
        // Retrieve input values
        String carNumber = cno1.getText();
        String fullName = fulln1.getText();
        String gender = genderGroup.getSelectedToggle() != null ?
                ((RadioButton) genderGroup.getSelectedToggle()).getText() : "Not selected";
        String position = p11.getText();
        String brand = b11.getValue();
        String lapNo = l11.getText();
        String trackNo = t11.getText();
        String mobile = mobileNumberField.getText();

        // Validate input
        if (carNumber.isEmpty() || fullName.isEmpty() || gender.equals("Not selected") ||
            position.isEmpty() || brand == null || lapNo.isEmpty() || trackNo.isEmpty() || mobile.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields");
            return;
        }

        // Save to database
        Connection connect = database.connectDb();
        if (connect == null) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to connect to the database.");
            return;
        }

        String sql = "INSERT INTO parking (car_number, full_name, gender, position, brand, lap_no, track_no, mobile) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prepare = connect.prepareStatement(sql)) {
            prepare.setString(1, carNumber);
            prepare.setString(2, fullName);
            prepare.setString(3, gender);
            prepare.setString(4, position);
            prepare.setString(5, brand);
            prepare.setString(6, lapNo);
            prepare.setString(7, trackNo);
            prepare.setString(8, mobile);
            prepare.executeUpdate();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Parking info saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error saving parking info: " + e.getMessage());
        } finally {
            try {
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Print to console for debugging
        System.out.println("==== New Car Parking Info ====");
        System.out.println("Car No: " + carNumber);
        System.out.println("Full Name: " + fullName);
        System.out.println("Gender: " + gender);
        System.out.println("Position: " + position);
        System.out.println("Brand: " + brand);
        System.out.println("Lap No: " + lapNo);
        System.out.println("Track No: " + trackNo);
        System.out.println("Mobile No: " + mobile);
    }

    @FXML
    private void RelesedActionMethod(ActionEvent event) {
        // Placeholder for releasing a parking slot
        showAlert(Alert.AlertType.INFORMATION, "Release Parking", "Parking slot released (placeholder).");
    }

    @FXML
    private void GenaratingActionButton(ActionEvent event) {
        // Placeholder for generating action (e.g., ticket or receipt)
        showAlert(Alert.AlertType.INFORMATION, "Generate", "Generating action triggered (placeholder).");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
