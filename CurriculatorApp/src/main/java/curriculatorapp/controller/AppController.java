/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.controller;

import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
import curriculatorapp.logic.Service;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author ehorrosw
 */
public class AppController implements Controller {

    private Service appservice;
    private Label courselabel;
   
    @Override
    @FXML
    public void initService(Service appservice) {
        this.appservice = appservice;
       setNull();
    }
    
    @FXML
    public void setNull(){
         courselabel = new Label();
             courselabel.setVisible(false);
         
    }
    
   
        
    
    
  

}
