package car_parking_system;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class New_Car_ParkingController implements Initializable {

    @FXML private TextField carNumberField;
    @FXML private TextField fullNameField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private RadioButton othersRadio;
    @FXML private ToggleGroup genderGroup;
    @FXML private TextField positionField;
    @FXML private ComboBox<String> brandComboBox;
    @FXML private TextField lapNumberField;
    @FXML private TextField trackNumberField;
    @FXML private TextField mobileNumberField;

    @FXML private Button Rel_Parking;
    @FXML private Button tg1;
    @FXML private Button okButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Brand list
        brandComboBox.getItems().addAll("Toyota", "BMW", "Hyundai", "Honda", "Mercedes", "Other");
        brandComboBox.setPromptText("Select Brand");

        // Gender group
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        othersRadio.setToggleGroup(genderGroup);
    }

    @FXML
    private void handleOkButton(ActionEvent event) {
        // Print collected data
        System.out.println("==== New Car Parking Info ====");
        System.out.println("Car No: " + carNumberField.getText());
        System.out.println("Full Name: " + fullNameField.getText());
        System.out.println("Gender: " + getSelectedGender());
        System.out.println("Position: " + positionField.getText());
        System.out.println("Brand: " + brandComboBox.getValue());
        System.out.println("Lap No: " + lapNumberField.getText());
        System.out.println("Track No: " + trackNumberField.getText());
        System.out.println("Mobile No: " + mobileNumberField.getText());
    }

    @FXML
    private void RelesedActionMethod(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Released", "Parking Released Successfully!");
    }

    @FXML
    private void GenaratingActionButton(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF Token");
            fileChooser.setInitialFileName("Token_Receipt.pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
            Stage stage = (Stage) tg1.getScene().getWindow();
            java.io.File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(file));
                doc.open();

                Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, Color.RED);
                Paragraph title = new Paragraph("New Car Parking Token", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                doc.add(title);
                doc.add(new Paragraph(" ")); // spacing

                doc.add(new Paragraph("Car Number: " + carNumberField.getText()));
                doc.add(new Paragraph("Full Name: " + fullNameField.getText()));
                doc.add(new Paragraph("Gender: " + getSelectedGender()));
                doc.add(new Paragraph("Position: " + positionField.getText()));
                doc.add(new Paragraph("Brand: " + brandComboBox.getValue()));
                doc.add(new Paragraph("Lap No: " + lapNumberField.getText()));
                doc.add(new Paragraph("Track No: " + trackNumberField.getText()));
                doc.add(new Paragraph("Mobile No: " + mobileNumberField.getText()));

                doc.close();
                showAlert(Alert.AlertType.INFORMATION, "PDF Generated", "Token PDF has been saved!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to generate PDF.");
        }
    }

    private String getSelectedGender() {
        if (genderGroup.getSelectedToggle() != null) {
            return ((RadioButton) genderGroup.getSelectedToggle()).getText();
        }
        return "Not selected";
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
