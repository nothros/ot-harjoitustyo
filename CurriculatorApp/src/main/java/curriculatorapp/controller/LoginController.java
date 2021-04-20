/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.controller;

import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.User;
import curriculatorapp.logic.AppService;
import curriculatorapp.logic.LoginService;
import curriculatorapp.ui.CurriculatorUi;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author ehorrosw
 */
public class LoginController implements Controller {

    private AppService appservice;
    @FXML
    public TextField loginUsername;
    @FXML
    public PasswordField loginPassword;
    @FXML
    public Label loginErrorLabel;
    @FXML
    public Pane loginPane, registerPane;

    public void initService(AppService appservice) {
        this.appservice = appservice;
    }

    @FXML
    public void onNewUserButtonClick() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterUI.fxml"));

        Parent uiRoot = loader.load();
        Controller controller = loader.getController();
        controller.initService(appservice);
        loginPane.getChildren().add(uiRoot);

    }

    @FXML
    public void onLoginButtonClick() throws SQLException, IOException {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();
        User u;
        if (username.isEmpty() || password.isEmpty()) {
            loginErrorLabel.setText("Täytä kaikki kentät!");
        } else {

        }
    }

}
