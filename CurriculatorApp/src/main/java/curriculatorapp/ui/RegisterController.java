/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    public void onRegisterButtonClick()  {
            if(registerUsername.getText().trim().isEmpty()){
            registerErrorLabel.setText("Täytä kaikki kentät!");
            } }
     
    public void onReturnbuttonClick() throws IOException{
        CurriculatorUi.setRoot("LoginUI");
    }
        
        
    }

