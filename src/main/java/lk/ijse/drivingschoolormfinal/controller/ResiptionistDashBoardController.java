package lk.ijse.drivingschoolormfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.drivingschoolormfinal.bo.custom.LessonBO;
import lk.ijse.drivingschoolormfinal.bo.custom.PaymentBO;
import lk.ijse.drivingschoolormfinal.bo.custom.StudentBO;
import lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory;
import lk.ijse.drivingschoolormfinal.model.LessonDTO;
import lk.ijse.drivingschoolormfinal.model.PaymentDTO;
import lk.ijse.drivingschoolormfinal.model.StudentDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResiptionistDashBoardController implements Initializable {
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.STUDENT);
    LessonBO lessonBO = (LessonBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.LESSON);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOtypes.PAYMENT);

    @FXML
    private AnchorPane Ank1;

    @FXML
    private Button btnManageLessons;

    @FXML
    private Button btnManagePayments;

    @FXML
    private Button btnManageStudents;

    @FXML
    private Button btnSearch;

    @FXML
    private Label dSchool;

    @FXML
    private Label lblTotalLessons;

    @FXML
    private Label lblTotalPayments;

    @FXML
    private Label lblTotalStudents;

    @FXML
    private VBox lessonId;

    @FXML
    private VBox paymentId;

    @FXML
    private VBox studentId;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox v1;
    private void nevigateTo(String s) {
        try {
            Ank1.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(s));

            pane.prefWidthProperty().bind(Ank1.widthProperty());
            pane.prefHeightProperty().bind(Ank1.heightProperty());

            Ank1.getChildren().add(pane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Page Not Found!").show();
            e.printStackTrace();

        }
    }
    @FXML
    void handleManageLessons(ActionEvent event) {
        nevigateTo("/lk/ijse/drivingschoolormfinal/accests/LessonManage.fxml");

    }

    @FXML
    void handleManagePayments(ActionEvent event) {
        nevigateTo("/lk/ijse/drivingschoolormfinal/accests/Payment.fxml");

    }

    @FXML
    void handleManageStudents(ActionEvent event) {
        nevigateTo("/lk/ijse/drivingschoolormfinal/accests/studentManage.fxml");

    }

    @FXML
    void handleSearch(ActionEvent event) {

    }

    public void setStudent () throws Exception {
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
        lblTotalStudents.setText(String.valueOf(allStudent.size()));
    }
    public void setLesson () throws Exception {
        ArrayList<LessonDTO> allLesson = lessonBO.getAllLesson();
        lblTotalLessons.setText(String.valueOf(allLesson.size()));
    }
    public void setPayment () throws Exception {
        ArrayList<PaymentDTO> allPayment = paymentBO.getAllPayments();
        lblTotalPayments.setText(String.valueOf(allPayment.size()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setStudent();
            setLesson();
            setPayment();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
