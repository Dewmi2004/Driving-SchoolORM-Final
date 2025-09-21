package lk.ijse.drivingschoolormfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.drivingschoolormfinal.bo.custom.InstructorBO;
import lk.ijse.drivingschoolormfinal.bo.custom.LessonBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;
import lk.ijse.drivingschoolormfinal.model.LessonDTO;
import lk.ijse.drivingschoolormfinal.view.tdm.LessonTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LessonPageController implements Initializable {
    private final LessonBO lessonBO = (LessonBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.LESSON);
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
    private ComboBox<String> cmbCourseId;

    @FXML
    private ComboBox<String> cmbInstructorId;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colInstructorId;

    @FXML
    private TableColumn<?, ?> colLessonId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private DatePicker dateLesson;

    @FXML
    private TableView<LessonTM> tblLessons;

    @FXML
    private TextField txtLessonId;

    @FXML
    private TextField txtTime;

    @FXML
    void clickOnAction(MouseEvent event) {
        LessonTM selected = tblLessons.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtLessonId.setText(String.valueOf(selected.getLessonID()));
            dateLesson.setValue(selected.getDate().toLocalDate());
            txtTime.setText(selected.getTime());
            cmbStatus.getSelectionModel().select(Integer.parseInt(selected.getStatus()));////see hear integer
            cmbStudentId.getSelectionModel().select(Integer.parseInt(String.valueOf(selected.getStudentID())));////see hear integer
            cmbCourseId.getSelectionModel().select(Integer.parseInt(String.valueOf(selected.getCourseID())));////see hear integer
            cmbInstructorId.getSelectionModel().select(Integer.parseInt(String.valueOf(selected.getInstructorID())));////see hear integer
        }
    }

    @FXML
    void handleAddLesson(ActionEvent event) {
        try {
            LessonDTO dto = new LessonDTO(
                    Date.valueOf(dateLesson.getValue()),
                    txtTime.getText(),
                     cmbStatus.getSelectionModel().getSelectedItem(),
                    Long.parseLong( cmbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbCourseId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbInstructorId.getSelectionModel().getSelectedItem())
            );
            if (lessonBO.saveLesson(dto)) {
                showInfo("Lesson added successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving Lesson: " + e.getMessage());
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void handleDeleteLesson(ActionEvent event) {
        try {
            if (lessonBO.deleteLesson(txtLessonId.getText())) {
                showInfo("Lesson deleted successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting Lesson: " + e.getMessage());
        }
    }

    @FXML
    void handleUpdateLesson(ActionEvent event) {
        try {
            long id = Long.parseLong(txtLessonId.getText());
            LessonDTO dto = new LessonDTO(
                    id,
                    Date.valueOf(dateLesson.getValue()),
                    txtTime.getText(),
                     cmbStatus.getSelectionModel().getSelectedItem(),
                    Long.parseLong( cmbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbCourseId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmbInstructorId.getSelectionModel().getSelectedItem())
            );
            if (lessonBO.updateLesson(dto)) {
                showInfo("Lesson updated successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating Lesson: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colLessonId.setCellValueFactory(new PropertyValueFactory<>("lessonID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorID"));

        loadAllLessons();

        cmbStatus.setItems(FXCollections.observableArrayList("Scheduled", "Completed", "Cancelled").sorted());

        // TODO: Load student IDs, course IDs, instructor IDs into combo boxes from DB
    }

    private void loadAllLessons() {
        try {
            List<LessonDTO> all = lessonBO.findAll();
            ObservableList<LessonTM> list = FXCollections.observableArrayList();
            for (LessonDTO dto : all) {
                list.add(new LessonTM(
                        dto.getLessonID(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getStatus(),
                        dto.getStudentID(),
                        dto.getCourseID(),
                        dto.getInstructorID()
                ));
            }
            tblLessons.setItems(list);
        } catch (Exception e) {
            showError("Error loading Lessons: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtLessonId.clear();
        dateLesson.setValue(null);
        txtTime.clear();
        cmbStatus.getSelectionModel().clearSelection();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbCourseId.getSelectionModel().clearSelection();
        cmbInstructorId.getSelectionModel().clearSelection();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }




}

