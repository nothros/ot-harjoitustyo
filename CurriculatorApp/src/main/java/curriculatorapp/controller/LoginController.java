package curriculatorapp.controller;

import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.domain.User;
import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
import curriculatorapp.logic.LoginService;
import curriculatorapp.logic.Service;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController implements Controller {

    CurriculumDao curriculumdao;
    private AppService appService;
    private LoginService loginService;
    @FXML
    public TextField loginUsername;
    @FXML
    public PasswordField loginPassword;
    @FXML
    public Label loginErrorLabel;

    @Override
    public void initService(Service appservice) {
        this.loginService = (LoginService) appservice;

    }

    @FXML
    public void onNewUserButtonClick() throws IOException {
        CurriculatorUi.loadNewScene("RegisterUI", loginService);
    }

    @FXML
    public void onLoginButtonClick() throws SQLException, IOException {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();
        if (username.isEmpty() || password.isEmpty()) {
            setNotifications("empty");
        } else {
            if (loginService.login(username, password)) {
                setNotifications("login");
                User loggedUser = loginService.getLoggedUser();
                appService = new AppService(loggedUser, loginService.getCurriculumDao(), loginService.getCoursesDao());
                if (loggedUser.getCurriculum().isEmpty()) {
                    CurriculatorUi.loadNewScene("NewUserUI", appService);
                } else {
                    CurriculatorUi.loadNewScene("MainUI", appService);
                }
            } else {
                setNotifications("error");
            }
        }
    }

    public void setNotifications(String reason) {

        if (reason.equals("login")) {
            loginErrorLabel.setTextFill(Color.GREEN);
            loginErrorLabel.setText("Kirjaudutaan sisään!");
            emptyFields();

        }
        if (reason.equals("empty")) {
            loginErrorLabel.setTextFill(Color.RED);
            loginErrorLabel.setText("Täytä kaikki kentät!");
            emptyFields();

        }
        if (reason.equals("error")) {
            loginErrorLabel.setTextFill(Color.RED);
            loginErrorLabel.setText("Jokin ei täsmää!");
            emptyFields();

        }
    }

    public void emptyFields() {
        loginUsername.setText("");
        loginPassword.setText("");
    }

}
