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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lk.ijse.drivingschoolormfinal.bo.custom.CourseBO;
import lk.ijse.drivingschoolormfinal.bo.custom.QuaryBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.entity.Student;
import lk.ijse.drivingschoolormfinal.view.tdm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseDetailsController implements Initializable {
    CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.COURSE);
    QuaryBO quaryBO = (QuaryBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.QUARY);
    @FXML
    public TableView<StudentTM> tblCoursrDetail;
    @FXML
    private AnchorPane Ank1;

    @FXML
    private TableColumn<?, ?> colStudentEmail;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private HBox imagehbox;

    @FXML
    private TextField txtCourseDuration;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
    }

    public void btnbacktodashonaction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/drivingschoolormfinal/accests/courseManage.fxml"));
        Parent root = loader.load();

        Stage stage = ((Stage)((Node)actionEvent.getSource()).getScene().getWindow());

        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setTitle("Course Management System");
        stage.show();
    }

    public void loadCourseDetails(long courseId) {
        try {
            var course = courseBO.findById(courseId);
            if (course != null) {
                txtCourseId.setText(String.valueOf(course.getCourseId()));
                txtCourseName.setText(course.getCourseName());
                txtCourseDuration.setText(course.getCourseDuration());
                txtCourseFee.setText(String.valueOf(course.getCourseFee()));
            }

            List<Student> students = quaryBO.findStudentsInAllCourses();
            ObservableList<StudentTM> obList = FXCollections.observableArrayList();

            for (Student s : students) {
                obList.add(new StudentTM(
                        s.getStudentID(),
                        s.getStudentName(),
                        s.getStudentEmail()
                ));
            }

            tblCoursrDetail.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading course details: " + e.getMessage()).show();
        }
    }
}
