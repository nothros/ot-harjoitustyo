package curriculatorapp.controller;

import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.domain.User;
import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
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
 * Luokka Login-näkymän kontrollointiin. Login-näkymä on tarkoitettu
 * kirjautumista varten
 */
public class LoginController implements Controller {

    CurriculumDao curriculumdao;
    private AppService appService;
    private LoginService loginService;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginErrorLabel;

    /**
     * Metodi asettaa tälle luokalla logiikkaluokan.
     *
     * @param appservice Annetaan kontrollerin käyttämä logiikka sitä
     * kutsuttaessa.
     */
    @Override
    public void initService(Service appservice) {
        this.loginService = (LoginService) appservice;
    }

    /**
     * Metodi kutsuu CurriculatorUIn metodia. Vaihdetaan näkymä
     * rekisteröinti-näkymään
     */
    @FXML
    public void onNewUserButtonClick() throws IOException {
        CurriculatorUi.loadNewScene("RegisterUI", loginService);
    }

    /**
     * Metodi kontrolloi kirjaudu sisään- painikkeen toimintaa. Se tarkastaa
     * onko tarvittavat tekstikentät tyhjinä, mikäli eivät, luos tämä uuden
     * käyttäjän annetuilla tiedoilla, ja kutsuu metodia näkymän vaihtamiseen.
     */
    @FXML
    public void onLoginButtonClick() throws SQLException, IOException {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();
        if (username.isEmpty() || password.isEmpty()) {
            setNotifications("empty");
        } else {
            if (loginService.login(username, password)) {
                System.out.print("LOGGAAA");
                setNotifications("login");
                User loggedUser = loginService.getLoggedUser();
                appService = new AppService(loggedUser, loginService.getCurriculumDao(), loginService.getCoursesDao());

                if (appService.checkIfCurriculumExist()) {
                    CurriculatorUi.loadNewScene("MainUI", appService);
                } else {
                    CurriculatorUi.loadNewScene("NewUserUI", appService);
                }
            } else {
                setNotifications("error");
            }
        }
    }

    /**
     * Metodi asettaa ilmoituskenttään ilmoitukset. Mikäli tekstikentät ovat
     * tyhjiä kirjautuessa tai jos käyttäjätunnus/salasana on virheellinen
     *
     * @param reason Mahdollisen virheilmoituksen syy, kuten empty- tyhjä
     * kenttä.
     */
    public void setNotifications(String reason) {

        if (reason.equals("login")) {
            loginErrorLabel.setTextFill(Color.GREEN);
            loginErrorLabel.setText("Kirjaudutaan sisään!");
            emptyFields();

        }
        if (reason.equals("empty")) {
            loginErrorLabel.setTextFill(Color.RED);
            loginErrorLabel.setText("Täytä kaikki kentät!");
            emptyFields();

        }
        if (reason.equals("error")) {
            loginErrorLabel.setTextFill(Color.RED);
            loginErrorLabel.setText("Jokin ei täsmää!");
            emptyFields();

        }
    }

    /**
     * Metodi tyhjentää tekstikentät.
     */
    public void emptyFields() {
        loginUsername.setText("");
        loginPassword.setText("");
    }

}