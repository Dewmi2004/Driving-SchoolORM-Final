package lk.ijse.drivingschoolormfinal.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lk.ijse.drivingschoolormfinal.bo.custom.StudentBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.model.StudentDTO;
import lk.ijse.drivingschoolormfinal.view.tdm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {

    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.STUDENT);

    public HBox imagehbox;
    public TextField txtStudentId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPhone;
    public TextField txtAddress;
    public DatePicker dateRegistration;
    public TextField txtRegisterFee;
    public Button btnAdd;
    public TableView<StudentTM> tblStudents;
    public TableColumn<?,?> colId;
    public TableColumn<?,?> colName;
    public TableColumn<?,?> colEmail;
    public TableColumn<?,?> colPhone;
    public TableColumn<?,?> colAddress;
    public TableColumn<?,?> colDate;
    public Button btnClear;
    public Button btnDelete;
    public Button btnUpdate;
    public TableColumn<?,?> colregisterfee;

    private void loadNextId() throws Exception {
        String nextId = studentBO.getLastID();
        txtStudentId.setText(nextId);
    }

    private void loadAllStudents() {
        try {
            List<StudentDTO> all = studentBO.findAll();
            ObservableList<StudentTM> list = FXCollections.observableArrayList();
            for (StudentDTO dto : all) {
                list.add(new StudentTM(
                        dto.getStudentID(),
                        dto.getStudentName(),
                        dto.getStudentEmail(),
                        dto.getStudentPhone(),
                        dto.getStudentAddress(),
                        dto.getRegisterFee(),
                        dto.getRegisterDate()
                ));
            }
            tblStudents.setItems(list);
        } catch (Exception e) {
            showError("Error loading students: " + e.getMessage());
        }
    }

    public void handleAddStudent(ActionEvent actionEvent) {
        try {
            StudentDTO dto = new StudentDTO(
                    //txtStudentId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtRegisterFee.getText(),
                    Date.valueOf(dateRegistration.getValue())
            );
            if (studentBO.saveStudent(dto)) {
                showInfo("Student added successfully!");
                loadAllStudents();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving student: " + e.getMessage());
        }
    }

    public void handleClear(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtStudentId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtRegisterFee.clear();
        dateRegistration.setValue(null);
    }

    public void handleUpdateStudent(ActionEvent actionEvent) {
        try {
            long id = Long.parseLong(txtStudentId.getText());
            StudentDTO dto = new StudentDTO(
                    id,
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtRegisterFee.getText(),
                    Date.valueOf(dateRegistration.getValue())
            );
            if (studentBO.updateStudent(dto)) {
                showInfo("Student updated successfully!");
                loadAllStudents();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating student: " + e.getMessage());
        }
    }

    public void handleDeleteStudent(ActionEvent actionEvent) {
        try {
            if (studentBO.deleteStudent(txtStudentId.getText())) {
                showInfo("Student deleted successfully!");
                loadAllStudents();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting student: " + e.getMessage());
        }
    }
    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        StudentTM selected = tblStudents.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtStudentId.setText(String.valueOf(selected.getStudentID()));
            txtName.setText(selected.getStudentName());
            txtEmail.setText(selected.getStudentEmail());
            txtPhone.setText(selected.getStudentPhone());
            txtAddress.setText(selected.getStudentAddress());
            txtRegisterFee.setText(selected.getRegisterFee());
            dateRegistration.setValue(selected.getRegisterDate().toLocalDate());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studentID"));
        colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studentName"));
        colEmail.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studentEmail"));
        colPhone.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studentPhone"));
        colAddress.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("studentAddress"));
        colregisterfee.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("registerFee"));
        colDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("registerDate"));

        loadAllStudents();
       /* try {
            loadNextId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
}
