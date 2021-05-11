package curriculatorapp.controller;

import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.LoginService;
import curriculatorapp.logic.Service;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Luokka Register-näkymän kontrollointiin. Register-näkymä on tarkoitettu uuden
 * käyttäjän luomista varten
 */
public class RegisterController implements Controller {

    private LoginService appservice;
    @FXML
    private TextField registerName, registerUsername;
    @FXML
    private PasswordField registerPassword;
    @FXML
    private Label registerErrorLabel;

    /**
     * Metodi asettaa tälle luokalla logiikkaluokan.
     *
     * @param appservice Annetaan kontrollerin käyttämä logiikka sitä
     * kutsuttaessa.
     */
    @Override
    public void initService(Service appservice) {
        this.appservice = (LoginService) appservice;
    }

    /**
     * Metodi kontrolloi Rekisteröi-painikkeen toimintaa. Se tarkastaa onko
     * tarvittavat tekstikentät tyhjinä, mikäli eivät, luo tämä uuden käyttäjän
     * annetuilla tiedoilla, ja kutsuu metodia ilmoituskentän täyttämiseen
     */
    public void onRegisterButtonClick() throws SQLException {
        String name = registerName.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();

        if ((name.trim().isEmpty()) || (username.trim().isEmpty()) || (password.trim().isEmpty())) {
            setNotifications("empty");

        } else {
            if (appservice.createNewUser(name, username, password)) {
                registerErrorLabel.setTextFill(Color.GREEN);
                setNotifications("ok");
            } else {
                registerErrorLabel.setTextFill(Color.RED);
                setNotifications("username");

            }

        }
    }

    /**
     * Metodi kutsuu CurriculatorUI-luokan metodia. Vaihdetaan näkymä
     * rekisteröinti-näkymään
     */
    public void onReturnbuttonClick() throws IOException {
        CurriculatorUi.loadNewScene("LoginUI", appservice);
    }

    /**
     * Metodi asettaa ilmoituskenttään ilmoitukset. Mikäli tekstikentät ovat
     * tyhjiä luodessa käyttäjää tai jos käyttäjänimi on jo käytössä
     *
     * @param reason Mahdollisen virheilmoituksen syy, kuten empty- tyhjä
     * kenttä.
     */
    public void setNotifications(String reason) {

        if (reason.equals("ok")) {
            registerErrorLabel.setTextFill(Color.GREEN);
            registerErrorLabel.setText("Käyttäjä luotiin, palaa takaisin!");
            emptyFields();

        }
        if (reason.equals("empty")) {
            registerErrorLabel.setTextFill(Color.RED);
            registerErrorLabel.setText("Täytä kaikki kentät!");
            emptyFields();

        }
        if (reason.equals("username")) {
            registerErrorLabel.setTextFill(Color.RED);
            registerErrorLabel.setText("Käyttäjätunnus on jo käytössä!");
            emptyFields();

        }
    }

    /**
     * Metodi tyhjentää tekstikentät.
     */
    public void emptyFields() {
        registerName.setText("");
        registerUsername.setText("");
        registerPassword.setText("");
    }
}