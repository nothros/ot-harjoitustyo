package curriculatorapp.controller;

import curriculatorapp.logic.AppService;
import curriculatorapp.logic.Service;
import curriculatorapp.ui.CurriculatorUi;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Luokka NewUser-näkymän kontrollointiin.
 */
public class NewUserController implements Controller {

    private AppService appservice;
    @FXML
    private ChoiceBox studyChoiceBox;
    @FXML
    private TextField studyTextfield, studyScope;
    @FXML
    private Label nameLabel, newUsererrorlabel;

    /**
     * Metodi asettaa tälle luokalle logiikkaluokan.
     *
     * @param appservice Annetaan kontrollerin käyttämä logiikka sitä
     * kutsuttaessa.
     */
    @Override
    public void initService(Service appservice) {
        this.appservice = (AppService) appservice;
        setChoices();
        setName();
    }

    /**
     * Metodi asettaa Choicebox:lle valinnat.
     */
    @FXML
    public void setChoices() {
        studyChoiceBox.getItems().add(0, "");
        studyChoiceBox.getItems().add(1, "Osaamispiste");
        studyChoiceBox.getItems().add(2, "Opintopiste");
        studyChoiceBox.getSelectionModel().select(0);
    }

    /**
     * Metodi asettaa sivupalkkiin käyttäjän syöttämän nimen.
     *
     */
    @FXML
    public void setName() {
        nameLabel.setText(appservice.getLoggedUser().getName() + "!");
    }

    /**
     * Metodi kontrolloi Luo uusi opinto-painikkeen toimintaa.
     *
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @FXML
    public void onStudiesButtonClick() throws IOException, SQLException {
        String curriculumName = studyTextfield.getText();
        String scopeString = studyScope.getText();
        String choice = String.valueOf(studyChoiceBox.getValue());
        if ((curriculumName.trim().isEmpty()) || (scopeString.trim().isEmpty()) || (choice.trim().isEmpty())) {
            setNotifications("empty");
        } else {
            try {
                int scope = Integer.parseInt(scopeString);
                if (scope <= 0) {
                    setNotifications("zero");
                } else {
                    appservice.createCurriculum(curriculumName, scope, choice);
                    CurriculatorUi.loadNewScene("MainUI", appservice);
                }
            } catch (NumberFormatException e) {
                setNotifications("notNumber");
            }
        }
    }

    /**
     * Metodi asettaa ilmoituskenttään ilmoitukset. Mikäli tekstikentät ovat
     * tyhjiä luodessa uutta opintoa tai laajuus ei ole numero.
     *
     * @param reason Mahdollisen virheilmoituksen syy, kuten empty- tyhjä
     * kenttä.
     */
    public void setNotifications(String reason) {

        if (reason.equals("empty")) {
            newUsererrorlabel.setTextFill(Color.RED);
            newUsererrorlabel.setText("Täytä kaikki kentät!");

        }
        if (reason.equals("notNumber")) {
            newUsererrorlabel.setTextFill(Color.RED);
            newUsererrorlabel.setText("Laajuuden on oltava numero!");
            emptyFields();

        }
        if (reason.equals("zero")) {
            newUsererrorlabel.setTextFill(Color.RED);
            newUsererrorlabel.setText("Laajuuden on oltava suurempi kuin 0!");
            emptyFields();

        }

    }

    /**
     * Metodi tyhjentää tekstikentät.
     */
    public void emptyFields() {
        studyTextfield.setText("");
        studyScope.setText("");
    }
}
