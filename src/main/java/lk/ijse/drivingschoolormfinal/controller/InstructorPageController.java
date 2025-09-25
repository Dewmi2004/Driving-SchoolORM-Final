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
import lk.ijse.drivingschoolormfinal.bo.custom.InstructorBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;
import lk.ijse.drivingschoolormfinal.view.tdm.InstructorTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InstructorPageController implements Initializable {
private final InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.INSTRUCTOR);
    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbAvailability;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colInstructorId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView<InstructorTM> tblInstructors;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    void clickOnAction(MouseEvent event) {
        InstructorTM selected = tblInstructors.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtInstructorId.setText(String.valueOf(selected.getInstructorID()));
            txtName.setText(selected.getInstructorName());
            txtEmail.setText(selected.getInstructorEmail());
            txtPhone.setText(selected.getInstructorPhone());
            cmbAvailability.getSelectionModel().select(selected.getInstructorAvailability());

        }
    }

    @FXML
    void handleAddInstructor(ActionEvent event) {
        try {
            InstructorDTO dto = new InstructorDTO(
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    cmbAvailability.getSelectionModel().getSelectedItem()
            );
            if (instructorBO.saveInstructor(dto)) {
                showInfo("Instructor added successfully!");
                loadAllInstructers();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving Instructor: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtInstructorId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        cmbAvailability.getSelectionModel().clearSelection();
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void handleDeleteInstructor(ActionEvent event) {
        try {
            if (instructorBO.deleteInstructor(txtInstructorId.getText())) {
                showInfo("Instructor deleted successfully!");
                loadAllInstructers();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting Instructor: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdateInstructor(ActionEvent event) {
        try {
            long id = Long.parseLong(txtInstructorId.getText());
            InstructorDTO dto = new InstructorDTO(
                    id,
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    cmbAvailability.getSelectionModel().getSelectedItem()
            );
            if (instructorBO.updateInstructor(dto)) {
                showInfo("Instructor updated successfully!");
                loadAllInstructers();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating Instructor: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("instructorName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("instructorEmail"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("instructorPhone"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("instructorAvailability"));
                loadAllInstructers();
                cmbAvailability.setItems(FXCollections.observableArrayList("Available", "Not Available").sorted());
    }

    private void loadAllInstructers() {
        try {
            List<InstructorDTO> all = instructorBO.findAll();
            ObservableList<InstructorTM> list = FXCollections.observableArrayList();
            for (InstructorDTO dto : all) {
                list.add(new InstructorTM(
                       dto.getInstructorID(),
                        dto.getInstructorName(),
                        dto.getInstructorEmail(),
                        dto.getInstructorPhone(),
                        dto.getInstructorAvailability()
                ));
            }
            tblInstructors.setItems(list);
        } catch (Exception e) {
            showError("Error loading Instructors: " + e.getMessage());
        }
    }
            private void showInfo (String msg){
                new Alert(Alert.AlertType.INFORMATION, msg).show();
            }

            private void showError (String msg){
                new Alert(Alert.AlertType.ERROR, msg).show();
            }

    public void btnbacktodashonaction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/drivingschoolormfinal/accests/dashBoardAdmin.fxml"));
        Parent root = loader.load();

        Stage stage = ((Stage)((Node)actionEvent.getSource()).getScene().getWindow());

        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Dashboard");
        stage.show();
    }
}

