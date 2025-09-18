package lk.ijse.drivingschoolormfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.drivingschoolormfinal.bo.custom.CourseBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.model.CourseDTO;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;
import lk.ijse.drivingschoolormfinal.view.tdm.CourseTM;
import lk.ijse.drivingschoolormfinal.view.tdm.InstructorTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoursePageController implements Initializable {
private CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.COURSE);
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFees;

    @FXML
    private HBox imagehbox;

    @FXML
    private TableView<CourseTM> tblCourses;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFees;

    @FXML
    void ClickOnAction(MouseEvent event) {
        CourseTM selected = tblCourses.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtCourseId.setText(String.valueOf(selected.getCourseId()));
            txtCourseName.setText(selected.getCourseName());
            txtDuration.setText(selected.getCourseDuration());
            txtFees.setText(selected.getCourseFee());

        }
    }

    @FXML
    void handleAddCourse(ActionEvent event) {
        try {
            CourseDTO dto = new CourseDTO(
                   txtCourseName.getText(),
                    txtDuration.getText(),
                    txtFees.getText()
            );
            if (courseBO.saveCourse(dto)) {
                showInfo("Course added successfully!");
                loadAllCourses();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving Course: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();

    }

    private void clearFields() {
        txtCourseId.clear();
        txtCourseName.clear();
        txtDuration.clear();
        txtFees.clear();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();

    }

    private void loadAllCourses() {
        try {
            List<CourseDTO> all = courseBO.findAll();
            ObservableList<CourseTM> list = FXCollections.observableArrayList();
            for (CourseDTO dto : all) {
                list.add(new CourseTM(
                       dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getCourseDuration(),
                        dto.getCourseFee()
                ));
            }
            tblCourses.setItems(list);
        } catch (Exception e) {
            showError("Error loading Courses: " + e.getMessage());
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void handleDeleteCourse(ActionEvent event) {
        try {
            if (courseBO.deleteCourse(txtCourseId.getText())) {
                showInfo("Course deleted successfully!");
                loadAllCourses();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting Course: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdateCourse(ActionEvent event) {
        try {
            long id = Long.parseLong(txtCourseId.getText());
            CourseDTO dto = new CourseDTO(
                    id,
                    txtCourseName.getText(),
                    txtDuration.getText(),
                    txtFees.getText()
            );
            if (courseBO.updateCourse(dto)) {
                showInfo("Course updated successfully!");
                loadAllCourses();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating Course: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
        colFees.setCellValueFactory(new PropertyValueFactory<>("courseFee"));

        loadAllCourses();
    }
}

