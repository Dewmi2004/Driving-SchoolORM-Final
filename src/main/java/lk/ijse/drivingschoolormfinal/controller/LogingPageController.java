package lk.ijse.drivingschoolormfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.drivingschoolormfinal.config.FactoryConfiguration;
import lk.ijse.drivingschoolormfinal.entity.User;
import lk.ijse.drivingschoolormfinal.util.SessionManager;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
import java.net.URL;
import java.util.ResourceBundle;

public class LogingPageController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField usernameField;

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText() == null ? "" : usernameField.getText().trim();
        String password = passwordField.getText() == null ? "" : passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter username and password.");
            return;
        }

        loginButton.setDisable(true);
        statusLabel.setText("Authenticating...");

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            session.beginTransaction();

            User user = session.createQuery("FROM User WHERE userName = :uname", User.class)
                    .setParameter("uname", username)
                    .uniqueResult();

            session.getTransaction().commit();

            if (user != null) {
                String storedHash = user.getUserPassword();

                if (storedHash != null && storedHash.startsWith("$2")) {
                    if (BCrypt.checkpw(password, storedHash)) {
                        statusLabel.setStyle("-fx-text-fill: green;");
                        statusLabel.setText("Login successful — welcome!");
                        // store user in session
                        SessionManager.setLoggedUser(user);
                        String fxmlPath = switch (user.getUserRole()) {
                            case "Admin" -> "/lk/ijse/drivingschoolormfinal/accests/dashBoardAdmin.fxml";
                            case "Receptionist" -> "/lk/ijse/drivingschoolormfinal/accests/dashBoardResiptionist.fxml";
                            default -> "";
                        };

                        if (!fxmlPath.isEmpty()) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                            Parent root = loader.load();

                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.setTitle(user.getUserRole() + " Dashboard");
                            stage.show();

                            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
                        } else {
                            statusLabel.setText("Unknown user role.");
                            usernameField.clear();
                            passwordField.clear();
                        }

                    } else {
                        statusLabel.setStyle("-fx-text-fill: #b00020;");
                        statusLabel.setText("Invalid username or password.");
                        usernameField.clear();
                        passwordField.clear();
                    }
                } else {
                    statusLabel.setStyle("-fx-text-fill: #b00020;");
                    statusLabel.setText("User password is invalid. Please reset password.");
                    usernameField.clear();
                    passwordField.clear();
                }

            } else {
                statusLabel.setStyle("-fx-text-fill: #b00020;");
                statusLabel.setText("Invalid username or password.");
                usernameField.clear();
                passwordField.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setStyle("-fx-text-fill: #b00020;");
            statusLabel.setText("Authentication error. Try again later.");
            usernameField.clear();
            passwordField.clear();
        } finally {
            loginButton.setDisable(false);
        }
    }



    @FXML
    void onForgot(ActionEvent event) {
        statusLabel.setText("Forgot password: open recovery flow.");
    }

    @FXML
    void onSignUp(ActionEvent event) {
        statusLabel.setText("Sign up flow — open registration form.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
