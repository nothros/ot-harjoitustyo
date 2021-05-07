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
 * Luokka käynnistää sovelluksen ja vaihtaa sovelluksen näkymää
 */
public class CurriculatorUi extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, FileNotFoundException, SQLException {
        UserDao dao = new UserDao("curriculatorapp.db");
        CurriculumDao curriculumdao = new CurriculumDao("curriculatorapp.db");
        CoursesDao coursesdao = new CoursesDao();
        LoginService loginService = new LoginService(dao, curriculumdao, coursesdao);
        loginService.createNewTablesIfNotExists();

        Parent uiRoot = loadFXMLAndController("LoginUI", loginService);

        scene = new Scene(uiRoot, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    /**
     * Metodi kertoo mikä on onnistumistodennäköisyys syöteluvulla ottaen
     * huomioon olion konstruktorissa asetetun kalibrointiarvon.
     *
     *
     * @param fxml  käytettävän fxml-tiedoston nimi
     * @param service   käytettävä sovelluslogiikka
     * @return palauttaa ladatun näkymän
     */
    public static Parent loadFXMLAndController(String fxml, Service service) throws IOException {
        FXMLLoader loader = new FXMLLoader(CurriculatorUi.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.initService(service);

        return root;
    }

    public static void loadNewScene(String fxml, Service service) throws IOException {
        Parent newScene = loadFXMLAndController(fxml, service);
        scene.setRoot(newScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
