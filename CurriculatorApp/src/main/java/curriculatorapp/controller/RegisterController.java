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

public class RegisterController implements Controller {

    private AppService appservice;
    @FXML
    public TextField registerName, registerUsername;
    @FXML
    public PasswordField registerPassword;
    @FXML
    public Label registerErrorLabel;

    @Override
    public void initService(AppService appservice) {
        this.appservice = appservice;
    }

    public void onRegisterButtonClick() throws SQLException {
        String name = registerName.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();
        if ((name.trim().isEmpty()) || (username.trim().isEmpty()) || (password.trim().isEmpty())) {
            emptyFields();
            registerErrorLabel.setTextFill(Color.RED);
            registerErrorLabel.setText("Täytä kaikki kentät!");

        } else {
            appservice.createNewUser(name, username, password);
            registerErrorLabel.setTextFill(Color.GREEN);
            registerErrorLabel.setText("Käyttäjä luotiin, palaa takaisin!");
            emptyFields();
        }
    }

    public void onReturnbuttonClick() throws IOException {
        CurriculatorUi.loadNewScene("LoginUI", appservice);
    }

    public void emptyFields() {
        registerName.setText("");
        registerUsername.setText("");
        registerPassword.setText("");
    }
}
