/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.controller;

import curriculatorapp.logic.AppService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author ehorrosw
 */
public class RegisterController implements Controller {

    private AppService appservice;
    @FXML
    public TextField registerName, registerUsername;
    @FXML
    public PasswordField registerPassword;
    @FXML
    public Label registerErrorLabel;
    @FXML
    public Pane loginPane, registerPane;

    @Override
    public void initService(AppService appservice) {
        this.appservice = appservice;
    }

    public void onRegisterButtonClick() throws SQLException {
        String name = registerName.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();

        if ((name.trim().isEmpty()) || (username.trim().isEmpty()) || (password.trim().isEmpty())) {
            registerErrorLabel.setText("Täytä kaikki kentät!");

        } else {
            appservice.createNewUser(name, username, password);
        }
    }

    public void onReturnbuttonClick() throws IOException {
        System.out.println("Tämätoimii");
        /// TÄMÄ EI TOIMI; JA VAATII JONKIN VERRAN SÄÄTÖÄ ETTÄ SAA TAKAISIN OIKEAN ROOTIN.

    }
}
