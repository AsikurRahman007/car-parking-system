package car_parking_system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

        colt1.setCellValueFactory(new PropertyValueFactory<>("carNo"));
        colt2.setCellValueFactory(new PropertyValueFactory<>("name"));
        colt3.setCellValueFactory(new PropertyValueFactory<>("days"));

        tableview.setItems(parkingList);
    }

    @FXML
    private void handledone(ActionEvent event) {
        String carNo = tfcarno.getText();
        String name = tfname.getText();
        int days;

        try {
            days = Integer.parseInt(tfdays.getText());
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid number of days.");
            return;
        }

        // Calculate payment (Optional)
        int payment = days * 5;
        tfpaid.setText(String.valueOf(payment));

        // Add to table
        parkingList.add(new ParkingData(carNo, name, days));

        // Clear input fields
        tfcarno.clear();
        tfname.clear();
        tfdays.clear();
    }

    @FXML
    private void handleback(ActionEvent event) {
        Stage stage = (Stage) btnback.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Inner class to hold data
    public static class ParkingData {
        private final String carNo;
        private final String name;
        private final int days;

        public ParkingData(String carNo, String name, int days) {
            this.carNo = carNo;
            this.name = name;
            this.days = days;
        }

        public String getCarNo() {
            return carNo;
        }

        public String getName() {
            return name;
        }

        public int getDays() {
            return days;
        }
    }
}
