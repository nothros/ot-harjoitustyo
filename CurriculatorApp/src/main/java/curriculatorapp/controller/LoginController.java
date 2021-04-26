package curriculatorapp.controller;

import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController implements Controller {

    private AppService appservice;
    @FXML
    public TextField loginUsername;
    @FXML
    public PasswordField loginPassword;
    @FXML
    public Label loginErrorLabel;

    @Override
    public void initService(AppService appservice) {
        this.appservice = appservice;
    }

    @FXML
    public void onNewUserButtonClick() throws IOException {
        CurriculatorUi.loadNewScene("RegisterUI", appservice);
    }

    @FXML
    public void onLoginButtonClick() throws SQLException, IOException {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            setNotifications("empty");
            emptyFields();

        } else {
            if (appservice.login(username, password)) {
                setNotifications("login");
                if (appservice.getLoggedUser().getCurriculum().isEmpty()) {
                    CurriculatorUi.loadNewScene("NewUserUI", appservice);
                } else {
                    CurriculatorUi.loadNewScene("MainUI", appservice);
                }
            } else {
                setNotifications("error");
                emptyFields();
            }
        }
    }

    public void emptyFields() {
        loginUsername.setText("");
        loginPassword.setText("");
    }

    public void setNotifications(String reason) {

        if (reason.equals("login")) {
            loginErrorLabel.setTextFill(Color.GREEN);
            loginErrorLabel.setText("Kirjaudutaan sisään!");

        }
        if (reason.equals("empty")) {
            loginErrorLabel.setTextFill(Color.RED);
            loginErrorLabel.setText("Täytä kaikki kentät!");

        }
        if (reason.equals("error")) {
            loginErrorLabel.setTextFill(Color.RED);
            loginErrorLabel.setText("Jokin ei täsmää!");

        }
    }

}
