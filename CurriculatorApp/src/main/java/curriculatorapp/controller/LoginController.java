/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.controller;

import curriculatorapp.domain.User;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
    public Pane loginPane;
    @FXML
    private Label registerErrorLabel;
    @FXML
    private TextField registerUsername;
    @FXML
    private TextField registerName;
    @FXML
    private PasswordField registerPassword;
    @FXML
    public Pane registerPane;

    @FXML
    public void onNewUserButtonClick() throws IOException {
        registerPane = FXMLLoader.load(getClass().getResource("RegisterUI.fxml"));
        loginPane.getChildren().add(registerPane);

    }

    @FXML
    public void onLoginButtonClick() throws SQLException, IOException {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();
        User u;
        if (username.isEmpty() || password.isEmpty()) {
            loginErrorLabel.setText("Täytä kaikki kentät!");
        } else {
            Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
            Statement s = db.createStatement();
            PreparedStatement stmt = db.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            u = new User(rs.getString("name"), rs.getString("username"), rs.getString("password"));

            stmt.close();

            System.out.println(u.getPassword());
            boolean onko = u.checkPassword(password, u.getPassword());
            if (onko) {
                System.out.println("Salasanamatch");
                CurriculatorUi.setRoot("MainUI");

            }

        }
    }

    @FXML
    public void onRegisterButtonClick() throws SQLException {
        String name = registerName.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();

        if ((name.trim().isEmpty()) || (username.trim().isEmpty()) || (password.trim().isEmpty())) {
            registerErrorLabel.setText("Täytä kaikki kentät!");

        } else {
            User newUser = new User(name, username);
            newUser.setPassword(password);
            String hashedPassword = newUser.getPassword();
            /*  * * *
        NÄMÄ EI JÄÄ TÄNNE    
            * * *  */
            Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
            Statement s = db.createStatement();
            PreparedStatement p = db.prepareStatement("INSERT INTO users(name,username,password) VALUES (?,?,?)");
            p.setString(1, name);
            p.setString(2, username);
            p.setString(3, hashedPassword);

            p.executeUpdate();
            System.out.println("Käyttäjä lisätty");
            p.close();

            ResultSet r = s.executeQuery("SELECT * FROM Users");
            while (r.next()) {
                System.out.println(r.getInt("id") + " " + r.getString("name") + " " + r.getString("password"));

            }
            r.close();
            s.close();

        }
    }

    public void onReturnbuttonClick() throws IOException {
        System.out.println("Tämätoimii");
        CurriculatorUi.setRoot("LoginUI");

    }
}