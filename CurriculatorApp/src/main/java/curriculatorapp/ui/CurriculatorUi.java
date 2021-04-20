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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));

        Parent uiRoot = loader.load();
        Controller controller = loader.getController();

        controller.initService(service);

        scene = new Scene(uiRoot, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRegisterRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CurriculatorUi.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void initTestipoyta() throws SQLException {
        /*  * * *
        NÄMÄ EI JÄÄ TÄNNE    
            * * *  */

        Connection db = DriverManager.getConnection("jdbc:sqlite:curriculatorapp.db");

        PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name    VARCHAR(255), username    VARCHAR(255),  password   VARCHAR(255))");
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Luodaan tietokanta jos sitä ei ole");

    }
}
