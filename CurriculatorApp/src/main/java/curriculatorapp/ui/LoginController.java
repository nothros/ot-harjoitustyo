/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.ui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author ehorrosw
 */
public class LoginController {

    @FXML
    public TextField loginUsername;
    public PasswordField loginPassword;
    public Label loginErrorLabel;

    @FXML
    public void onNewUserButtonClick() throws IOException {
        CurriculatorUi.setRoot("RegisterUI");
    }

    @FXML
    public void onLoginButtonClick() {
        if (loginUsername.getText().trim().isEmpty() || loginPassword.getText().trim().isEmpty()) {
            loginErrorLabel.setText("Täytä kaikki kentät!");
        }
    }
}
