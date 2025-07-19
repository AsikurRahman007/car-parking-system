package car_parking_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class New_Car_ParkingController implements Initializable {

    private TextField carNumberField;

    private TextField fullNameField;

    private RadioButton maleRadio;

    private RadioButton femaleRadio;

    private RadioButton othersRadio;

    private ToggleGroup genderGroup;

    private TextField positionField;

    private ComboBox<String> brandComboBox;

    private TextField lapNumberField;

    private TextField trackNumberField;

    private TextField mobileNumberField;

    @FXML
    private TextField cno1;
    @FXML
    private TextField fulln1;
    @FXML
    private RadioButton m11;
    @FXML
    private RadioButton f11;
    @FXML
    private RadioButton o11;
    @FXML
    private TextField p11;
    @FXML
    private ComboBox<String> b11;
    @FXML
    private TextField l11;
    @FXML
    private TextField t11;
    @FXML
    private Button ok1;
    @FXML
    private Button Rel_Parking1;
    @FXML
    private Button tg11;

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

    @FXML
    private void RelesedActionMethod(ActionEvent event) {
    }

    @FXML
    private void GenaratingActionButton(ActionEvent event) {
    }
}