/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RegisterController {

    @FXML
    private Label registerErrorLabel;
    @FXML
    private TextField registerUsername;
    @FXML
    private TextField registerName;
    @FXML
    private PasswordField registerPassword;

    @FXML
    public void onRegisterButtonClick() throws SQLException {
        String name = registerName.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();

        if ((name.trim().isEmpty()) || (username.trim().isEmpty()) || (password.trim().isEmpty())) {
            registerErrorLabel.setText("Täytä kaikki kentät!");

        } else {
            
            /*  * * *
        NÄMÄ EI JÄÄ TÄNNE    
            * * *  */
            Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");
            Statement s = db.createStatement();
            PreparedStatement p = db.prepareStatement("INSERT INTO users(name,username,password) VALUES (?,?,?)");
            p.setString(1, name);
            p.setString(2, username);
            p.setString(3, password);

            p.executeUpdate();
            System.out.println("Käyttäjä lisätty");
            p.close();

            ResultSet r = s.executeQuery("SELECT * FROM Users");
            while (r.next()) {
                System.out.println(r.getInt("id") + " " + r.getString("name") + " " + r.getString("username"));
            }

        }
    }

    public void onReturnbuttonClick() throws IOException {
        CurriculatorUi.setRoot("LoginUI");
    }
}
