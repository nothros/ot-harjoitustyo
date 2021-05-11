package curriculatorapp.ui;

import curriculatorapp.controller.Controller;
import curriculatorapp.dao.UserDao;
import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.logic.LoginService;
import curriculatorapp.logic.Service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Luokka sovelluksen käynnistämiseen ja näkymän vaihtamiseen.
 */
public class CurriculatorUi extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, FileNotFoundException, SQLException {
        String databaseName = "curriculatorapp.db";
        LoginService service = initDatabaseAndService(databaseName);

        Parent uiRoot = loadFXMLAndController("LoginUI", service);
        scene = new Scene(uiRoot, 800, 600);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    /**
     * Metodi FXML-tiedoston ja kontrollin asettamiseen.
     *
     * @param fxml käytettävän fxml-tiedoston nimi
     * @param service käytettävä sovelluslogiikka
     * @return palauttaa ladatun näkymän
     * @throws java.io.IOException
     */
    public static Parent loadFXMLAndController(String fxml, Service service) throws IOException {
        FXMLLoader loader = new FXMLLoader(CurriculatorUi.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.initService(service);

        return root;
    }

    /**
     * Metodi uuden näkymän asettamiseen.
     *
     * @param fxml käytettävän fxml-tiedoston nimi
     * @param service käytettävä sovelluslogiikka
     * @throws java.io.IOException
     */
    public static void loadNewScene(String fxml, Service service) throws IOException {
        Parent newScene = loadFXMLAndController(fxml, service);
        scene.setRoot(newScene);
    }

    /**
     * Metodi asettaa tietokannan ja lataa servicen.
     *
     * @param db tietokannan nimi
     * @return palauttaa servicen
     * @throws java.sql.SQLException
     */
    public LoginService initDatabaseAndService(String db) throws SQLException {
        UserDao dao = new UserDao(db);
        CurriculumDao curriculumdao = new CurriculumDao(db);
        CoursesDao coursesdao = new CoursesDao(db);
        LoginService loginService = new LoginService(dao, curriculumdao, coursesdao);
        loginService.createNewTablesIfNotExists();

        return loginService;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
