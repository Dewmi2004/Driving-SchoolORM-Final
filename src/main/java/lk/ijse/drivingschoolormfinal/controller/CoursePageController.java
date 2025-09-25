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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lk.ijse.drivingschoolormfinal.bo.custom.CourseBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.model.CourseDTO;
import lk.ijse.drivingschoolormfinal.model.InstructorDTO;
import lk.ijse.drivingschoolormfinal.util.RegexValidator;
import lk.ijse.drivingschoolormfinal.view.tdm.CourseTM;
import lk.ijse.drivingschoolormfinal.view.tdm.InstructorTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CoursePageController implements Initializable {
    public AnchorPane Ank1;
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
public TableColumn<?,?> colViewDetails;

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
            if (!RegexValidator.isValidName(txtCourseName.getText())) {
                showError("Invalid name! Only letters and spaces, 3–50 characters.");
                return;
            }
            if (!RegexValidator.isValidDuration(txtDuration.getText())) {
                showError("Duration must be in format like '3 months' or '1 month'.");
                return;
            }
            if (!RegexValidator.isValidFee(txtFees.getText())) {
                showError("Invalid Course fee (must be a number).");
                return;
            }

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
                Button btn = new Button("View");
                btn.setOnAction(e -> openCourseDetailsForm(dto.getCourseId()));
                list.add(new CourseTM(
                       dto.getCourseId(),
                        dto.getCourseName(),
                        dto.getCourseDuration(),
                        dto.getCourseFee(),
                        btn
                ));
            }
            tblCourses.setItems(list);
        } catch (Exception e) {
            showError("Error loading Courses: " + e.getMessage());
        }
    }

    private void openCourseDetailsForm(long courseId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/drivingschoolormfinal/accests/course-details.fxml"));
            AnchorPane pane = loader.load();

            CourseDetailsController controller = loader.getController();
            controller.loadCourseDetails(courseId);

            Ank1.getChildren().clear();
            pane.prefWidthProperty().bind(Ank1.widthProperty());
            pane.prefHeightProperty().bind(Ank1.heightProperty());
            Ank1.getChildren().add(pane);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Page Not Found!").show();
            e.printStackTrace();
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
            if (!RegexValidator.isValidName(txtCourseName.getText())) {
                showError("Invalid name! Only letters and spaces, 3–50 characters.");
                return;
            }
            if (!RegexValidator.isValidDuration(txtDuration.getText())) {
                showError("Duration must be in format like '3 months' or '1 month'.");
                return;
            }
            if (!RegexValidator.isValidFee(txtFees.getText())) {
                showError("Invalid Course fee (must be a number).");
                return;
            }

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
        colViewDetails.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCourses();
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

