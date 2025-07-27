package car_parking_system;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class for All Records
 */
public class AllRecordsController implements Initializable {

    @FXML
    private TableView<ParkingRecord> recordsTable;
    @FXML
    private TableColumn<ParkingRecord, String> colOwnerName;
    @FXML
    private TableColumn<ParkingRecord, String> colCarName;
    @FXML
    private TableColumn<ParkingRecord, String> colCarNumber;
    @FXML
    private TableColumn<ParkingRecord, String> colEntryDate;
    @FXML
    private TableColumn<ParkingRecord, String> colExitDate;
    @FXML
    private Button btnBack;

    private ObservableList<ParkingRecord> recordList;

    private static final String URL = "jdbc:mysql://localhost:3306/parking_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recordList = FXCollections.observableArrayList();
        colOwnerName.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
        colCarName.setCellValueFactory(new PropertyValueFactory<>("carName"));
        colCarNumber.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        colEntryDate.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        colExitDate.setCellValueFactory(new PropertyValueFactory<>("exitDate"));
        recordsTable.setItems(recordList);
        loadRecords();
    }

    private void loadRecords() {
        recordList.clear();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM parking_records");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String ownerName = rs.getString("owner_name");
                String carName = rs.getString("car_name");
                String carNumber = rs.getString("car_number");
                String entryDate = rs.getString("entry_date");
                String exitDate = rs.getString("exit_date");
                recordList.add(new ParkingRecord(ownerName, carName, carNumber, entryDate, exitDate));
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load records: " + e.getMessage() + 
                               "\nEnsure MySQL is running and the parking_db database exists.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close(); // This returns to the previous open window (Dashboard)
    }

    public static class ParkingRecord {
        private final String ownerName;
        private final String carName;
        private final String carNumber;
        private final String entryDate;
        private final String exitDate;

        public ParkingRecord(String ownerName, String carName, String carNumber, String entryDate, String exitDate) {
            this.ownerName = (ownerName != null) ? ownerName : "N/A";
            this.carName = (carName != null) ? carName : "N/A";
            this.carNumber = (carNumber != null) ? carNumber : "N/A";
            this.entryDate = (entryDate != null) ? entryDate : "N/A";
            this.exitDate = (exitDate != null) ? exitDate : "N/A";
        }

        public String getOwnerName() { return ownerName; }
        public String getCarName() { return carName; }
        public String getCarNumber() { return carNumber; }
        public String getEntryDate() { return entryDate; }
        public String getExitDate() { return exitDate; }
    }
}