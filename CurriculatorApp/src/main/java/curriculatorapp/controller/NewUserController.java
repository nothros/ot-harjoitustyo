package curriculatorapp.controller;

import curriculatorapp.domain.Curriculum;
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

    private Curriculum curriculum;

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
     * Metodi asettaa Choicebox:lle valinnat. Näistä käyttäjä voi valita, millä
     * opintojen laajuutta mitataan
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
     * Metodi kontrolloi Luo uusi opinto-painikkeen toimintaa. Se tarkastaa onko
     * tarvittavat tekstikentät tyhjinä, mikäli eivät, luo tämä uuden opinnon
     * annetuilla tiedoilla, ja kutsuu metodia ilmoituskentän täyttämiseen
     * ehdoin
     */
    @FXML
    public void onStudiesButtonClick() throws IOException, SQLException {
        String curriculumName = studyTextfield.getText();
        String scopeString = studyScope.getText();
        String choice = String.valueOf(studyChoiceBox.getValue());

        if ((curriculumName.trim().isEmpty()) || (scopeString.trim().isEmpty()) || (choice.trim().isEmpty())) {
            System.out.println("TYHJÄ");
            setNotifications("empty");
        } else {

            try {
                Integer.parseInt(scopeString);
                int scope = Integer.valueOf(scopeString);
                appservice.createCurriculum(curriculumName, scope, choice);
                curriculum = new Curriculum(curriculumName, choice, scope, appservice.getLoggedUser());
                appservice.getLoggedUser().setCurriculum(curriculum);

                CurriculatorUi.loadNewScene("MainUI", appservice);

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

    }

    /**
     * Metodi tyhjentää tekstikentät.
     */
    public void emptyFields() {
        studyTextfield.setText("");
        studyScope.setText("");
    }
}
