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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author ehorrosw
 */
public class LoginController {
@FXML
private Pane loginPane;

@FXML
public void onBtnRegClick() throws IOException{
CurriculatorUi.setRoot("RegisterUI");
}
}


