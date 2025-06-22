package car_parking_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class New_Car_ParkingController implements Initializable {

    @FXML
    private TextField carNumberField;

    @FXML
    private TextField fullNameField;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private RadioButton othersRadio;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private TextField positionField;

    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private TextField lapNumberField;

    @FXML
    private TextField trackNumberField;

    @FXML
    private TextField mobileNumberField;

    @FXML
    private Button okButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate brands in the ComboBox
        if (brandComboBox != null) {
            brandComboBox.getItems().addAll("Toyota", "BMW", "Hyundai", "Honda", "Mercedes", "Other");
            brandComboBox.setPromptText("Select Brand");
        }

        // Set ToggleGroup for gender selection
        genderGroup = new ToggleGroup();
        if (maleRadio != null) maleRadio.setToggleGroup(genderGroup);
        if (femaleRadio != null) femaleRadio.setToggleGroup(genderGroup);
        if (othersRadio != null) othersRadio.setToggleGroup(genderGroup);
    }

    @FXML
    private void handleOkButton(ActionEvent event) {
        // Retrieve input values
        String carNumber = carNumberField.getText();
        String fullName = fullNameField.getText();
        String gender = genderGroup.getSelectedToggle() != null ?
                ((RadioButton) genderGroup.getSelectedToggle()).getText() : "Not selected";
        String position = positionField.getText();
        String brand = brandComboBox.getValue();
        String lapNo = lapNumberField.getText();
        String trackNo = trackNumberField.getText();
        String mobile = mobileNumberField.getText();

        // Print the collected information to the console
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
}
