/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.controller;

import curriculatorapp.logic.AppService;
import curriculatorapp.ui.CurriculatorUi;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 *
 * @author ehorrosw
 */
public class NewUserController implements Controller {
    
    private AppService appservice;
    @FXML
    private ChoiceBox studyChoiceBox;
    @FXML 
    private Label nameLabel;
   
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
    
    @FXML
    public void onStudiesButtonClick() throws IOException {
        System.out.println("Studiesnäppi toimii");
        CurriculatorUi.loadNewScene("MainUI", appservice);
    }
}
