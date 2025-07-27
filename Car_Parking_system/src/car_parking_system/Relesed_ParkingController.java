package car_parking_system;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class for Released Parking
 */
public class Relesed_ParkingController implements Initializable {

    @FXML
    private TableView<ParkingData> tableview;
    @FXML
    private TableColumn<ParkingData, String> colt1;
    @FXML
    private TableColumn<ParkingData, String> colt2;
    @FXML
    private TableColumn<ParkingData, Integer> colt3;
    @FXML
    private TableColumn<ParkingData, Integer> colt4; // For payment

    @FXML
    private TextField tfcarno;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfdays;
    @FXML
    private TextField tfpaid;
    @FXML
    private Button btndone;
    @FXML
    private Button btnback;

    private ObservableList<ParkingData> parkingList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parkingList = FXCollections.observableArrayList();

        // Set cell value factories for each column
        colt1.setCellValueFactory(new PropertyValueFactory<>("carNo"));
        colt2.setCellValueFactory(new PropertyValueFactory<>("name"));
        colt3.setCellValueFactory(new PropertyValueFactory<>("days"));
        colt4.setCellValueFactory(new PropertyValueFactory<>("payment")); // For payment

        tableview.setItems(parkingList);
    }

    @FXML
    private void handledone(ActionEvent event) {
        String carNo = tfcarno.getText();
        String name = tfname.getText();
        int days;
        int payment;

        // Validate days
        try {
            days = Integer.parseInt(tfdays.getText());
            if (days <= 0) {
                showAlert("Days must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid number of days.");
            return;
        }

        // Validate payment
        try {
            payment = Integer.parseInt(tfpaid.getText());
            if (payment <= 0) {
                showAlert("Payment must be a positive amount.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid payment amount.");
            return;
        }

        // Add to table
        parkingList.add(new ParkingData(carNo, name, days, payment));

        // Generate receipt
        String receipt = generateReceipt(carNo, name, days, payment);

        // Show receipt in alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Parking Receipt");
        alert.setHeaderText(null);
        alert.setContentText(receipt);
        alert.showAndWait();

        // Clear input fields
        tfcarno.clear();
        tfname.clear();
        tfdays.clear();
        tfpaid.clear();
    }

    @FXML
    private void handleback(ActionEvent event) {
        Stage stage = (Stage) btnback.getScene().getWindow();
        stage.close(); // Returns to the still-open Dashboard window
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private String generateReceipt(String carNo, String name, int days, int payment) {
        return "=== Parking Receipt ===\n" +
               "Date: " + LocalDate.now() + "\n" + // e.g., 2025-07-27
               "Time: " + LocalTime.now() + "\n" + // e.g., 03:36 PM +06 on July 27, 2025
               "Car Number: " + (carNo != null ? carNo : "N/A") + "\n" +
               "Owner Name: " + (name != null ? name : "N/A") + "\n" +
               "Parking Days: " + days + "\n" +
               "Total Paid: $" + payment + "\n" +
               "======================";
    }

    // Inner class to hold data
    public static class ParkingData {
        private final String carNo;
        private final String name;
        private final int days;
        private final int payment;

        public ParkingData(String carNo, String name, int days, int payment) {
            this.carNo = (carNo != null) ? carNo : "N/A";
            this.name = (name != null) ? name : "N/A";
            this.days = days;
            this.payment = payment;
        }

        public String getCarNo() { return carNo; }
        public String getName() { return name; }
        public int getDays() { return days; }
        public int getPayment() { return payment; }
    }
}