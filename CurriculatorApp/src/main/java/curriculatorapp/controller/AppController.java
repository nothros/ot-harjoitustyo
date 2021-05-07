package curriculatorapp.controller;

import curriculatorapp.domain.Curriculum;
import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
import curriculatorapp.logic.Service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author ehorrosw
 */
public class AppController implements Controller {

    private Curriculum curriculum;
    private AppService appservice;
    @FXML
    private Label courselabel;
    @FXML
    private ScrollPane courselist;
    @FXML
    private Label nameLabel;
    @FXML
    private Label curriculumNameLabel;

    @Override
    @FXML
    public void initService(Service appservice) {
       
            this.appservice = (AppService) appservice;
            
        try {
            this.curriculum = (Curriculum) this.appservice.findCurriculum();
            System.out.print("ONnistui");
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNameLabels();

       
    }

    /**
     * Metodi asettaa sivupalkkiin käyttäjän syöttämän nimen.
     *
     */
    @FXML
    public void setNameLabels() {
        nameLabel.setText(appservice.getLoggedUser().getName() + "!");
        System.out.println(curriculum.getChoice());
        curriculumNameLabel.setText(curriculum.getCurriculumName());
    }

    @FXML
    public void setNull() {
        courselabel = new Label();
        courselabel.setText("ETETET");
        courselist.setContent(courselabel);

    }

}
