package curriculatorapp.ui;

import curriculatorapp.controller.Controller;
import curriculatorapp.dao.UserDao;
import curriculatorapp.logic.AppService;
import curriculatorapp.controller.LoginController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurriculatorUi extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, FileNotFoundException, SQLException {
        UserDao dao = new UserDao();
        AppService service = new AppService(dao);
        service.createNewUserTableIfNotExists();
        
        Parent uiRoot = loadFXMLAndController("LoginUI", service);

        scene = new Scene(uiRoot, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadFXMLAndController(String fxml, AppService service) throws IOException {
        FXMLLoader loader = new FXMLLoader(CurriculatorUi.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.initService(service);

        return root;
    }

    public static void loadNewScene(String fxml, AppService service) throws IOException {
        Parent newScene = loadFXMLAndController(fxml, service);
        scene.setRoot(newScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
