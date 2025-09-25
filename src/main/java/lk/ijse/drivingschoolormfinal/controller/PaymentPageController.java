package lk.ijse.drivingschoolormfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.drivingschoolormfinal.bo.custom.PaymentBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.model.PaymentDTO;
import lk.ijse.drivingschoolormfinal.util.RegexValidator;
import lk.ijse.drivingschoolormfinal.util.SessionManager;
import lk.ijse.drivingschoolormfinal.view.tdm.PaymentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PAYMENT);

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCourseId;

    @FXML
    private ComboBox<String> cmbMethod;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMethod;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private DatePicker datePayment;

    @FXML
    private TableView<PaymentTM> tblPayments;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtPaymentId;

    @FXML
    void clickOnAction(MouseEvent event) {
        PaymentTM selected = tblPayments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtPaymentId.setText(String.valueOf(selected.getPaymentId()));
            datePayment.setValue(selected.getDate().toLocalDate());
            txtAmount.setText(selected.getAmount());
            cmbMethod.getSelectionModel().select(selected.getMethod());
            cmbStudentId.getSelectionModel().select(String.valueOf(selected.getStudentID()));
            cmbCourseId.getSelectionModel().select(String.valueOf(selected.getCourseID()));
        }
    }

    @FXML
    void handleAddPayment(ActionEvent event) {
        try {
            if (!RegexValidator.isValidFee(txtAmount.getText())) {
                showError("Invalid Amount (must be a number).");
                return;
            }
            PaymentDTO dto = new PaymentDTO(
                    Date.valueOf(datePayment.getValue()),
                    cmbMethod.getSelectionModel().getSelectedItem(),
                    txtAmount.getText(),
                    Long.parseLong(cmbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong(cmbCourseId.getSelectionModel().getSelectedItem())
            );
            if (paymentBO.savePayment(dto)) {
                showInfo("Payment added successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving payment: " + e.getMessage());
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
    clearFields();
    }

    @FXML
    void handleDeletePayment(ActionEvent event) {
            try {
                String id = txtPaymentId.getText();
                if (id.isEmpty()) {
                    showError("Please select a payment to delete!");
                    return;
                }

                if (paymentBO.deletePayment(id)) {
                    showInfo("Payment deleted successfully!");
                    loadAllPayments();
                    clearFields();
                } else {
                    showError("Payment not found or cannot be deleted!");
                }
            } catch (Exception e) {
                showError("Error deleting payment: " + e.getMessage());
            }
        }


        @FXML
    void handleUpdatePayment(ActionEvent event) {
        try {
            if (!RegexValidator.isValidFee(txtAmount.getText())) {
                showError("Invalid Amount (must be a number).");
                return;
            }
            long id = Long.parseLong(txtPaymentId.getText());
            PaymentDTO dto = new PaymentDTO(
                    id,
                    Date.valueOf(datePayment.getValue()),
                    cmbMethod.getSelectionModel().getSelectedItem(),
                    txtAmount.getText(),
                    Long.parseLong(cmbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong(cmbCourseId.getSelectionModel().getSelectedItem())
            );
            if (paymentBO.updatePayment(dto)) {
                showInfo("Payment updated successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating payment: " + e.getMessage());
        }
    }

    private void loadAllPayments() {
        try {
            List<PaymentDTO> all = paymentBO.findAll();
            ObservableList<PaymentTM> list = FXCollections.observableArrayList();
            for (PaymentDTO dto : all) {
                list.add(new PaymentTM(
                        dto.getPaymentId(),
                        dto.getDate(),
                        dto.getMethod(),
                        dto.getAmount(),
                        dto.getStudentID(),
                        dto.getCourseID()
                ));
            }
            tblPayments.setItems(list);
        } catch (Exception e) {
            showError("Error loading payments: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        loadAllPayments();

        cmbMethod.setItems(FXCollections.observableArrayList("Cash", "Card").sorted());
        loadStudentIds();
        loadCourseIds();
    }

    private void clearFields() {
        txtPaymentId.clear();
        datePayment.setValue(null);
        txtAmount.clear();
        cmbMethod.getSelectionModel().clearSelection();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }

    private void loadStudentIds() {
        try {
            List<String> ids = paymentBO.getAllStudentIds();
            cmbStudentId.setItems(FXCollections.observableArrayList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourseIds() {
        try {
            List<String> ids = paymentBO.getAllCourseIds();
            cmbCourseId.setItems(FXCollections.observableArrayList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnbacktodashonaction(ActionEvent actionEvent) throws IOException {
        String role = SessionManager.getUserRole();

        String fxmlPath = switch (role) {
            case "Admin" -> "/lk/ijse/drivingschoolormfinal/accests/dashBoardAdmin.fxml";
            case "Receptionist" -> "/lk/ijse/drivingschoolormfinal/accests/dashBoardResiptionist.fxml";
            default -> "";
        };

        if (!fxmlPath.isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(role + " Dashboard");
            stage.show();

            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        }
    }
}
