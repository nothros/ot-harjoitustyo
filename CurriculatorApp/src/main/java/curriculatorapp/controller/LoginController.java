package curriculatorapp.controller;

import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
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
    public void onLoginButtonClick() throws SQLException, IOException, InterruptedException {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            loginErrorLabel.setTextFill(Color.RED);
            loginErrorLabel.setText("Täytä kaikki kentät!");
            emptyFields();

        } else {
            System.out.println("Loginbutton toimii");
            if (appservice.login(username, password)) {
                loginErrorLabel.setTextFill(Color.GREEN);
                loginErrorLabel.setText("Kirjaudutaan sisään!");
                CurriculatorUi.loadNewScene("MainUI", appservice);

            } else {
                loginErrorLabel.setTextFill(Color.RED);
                loginErrorLabel.setText("Jokin ei täsmää!");
                emptyFields();

            }

        }
    }

    public void emptyFields() {
        loginUsername.setText("");
        loginPassword.setText("");
    }

}
