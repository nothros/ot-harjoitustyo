/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curriculatorapp.controller;

import curriculatorapp.logic.AppService;
import curriculatorapp.logic.Service;
import curriculatorapp.ui.CurriculatorUi;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author ehorrosw
 */
public class NewUserController implements Controller {

    private AppService appservice;
    @FXML
    private ChoiceBox studyChoiceBox;
    @FXML
    private TextField studyTextfield;
    @FXML
    private Label nameLabel;

    @Override
    public void initService(Service appservice) {
        this.appservice = (AppService) appservice;
        setChoices();
        setName();
    }

    @FXML
    public void setChoices() {
        studyChoiceBox.getItems().add(0, "");
        studyChoiceBox.getItems().add(1, "Osaamispiste");
        studyChoiceBox.getItems().add(2, "Opintopiste");
        studyChoiceBox.getSelectionModel().select(0);
    }

    @FXML
    public void setName() {
        nameLabel.setText(appservice.getLoggedName() + "!");
    }

    @FXML
    public void onStudiesButtonClick() throws IOException {
        String choice = String.valueOf(studyChoiceBox.getValue());
        String curriculumName=studyTextfield.getText();

        System.out.println(choice);
    
        if(choice.isEmpty()){
            System.out.println("TYHJÄON");
        }
        
        CurriculatorUi.loadNewScene("MainUI", appservice);
    }
}
