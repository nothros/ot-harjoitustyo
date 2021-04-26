/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.controller;

import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
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

    private AppService appservice;
    @FXML
    private ChoiceBox studyChoiceBox;
    @FXML Label nameLabel;
    @Override
    public void initService(AppService appservice) {
        this.appservice = appservice;
        setChoices();
        setName();
    }
    
    @FXML
    public void setChoices(){
        studyChoiceBox.getItems().add(0, "Osaamispiste");
        studyChoiceBox.getItems().add(1, "Opintopiste");
        
        
    }
    @FXML
    public void setName(){
        nameLabel.setText(appservice.getLoggedName()+"!");
        
        
    }

}
