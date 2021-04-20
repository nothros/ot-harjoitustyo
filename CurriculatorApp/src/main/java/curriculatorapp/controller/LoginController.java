
package curriculatorapp.controller;

import curriculatorapp.domain.User;
import curriculatorapp.logic.AppService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class LoginController implements Controller {

    private AppService appservice;
    @FXML
    public TextField loginUsername;
    @FXML
    public PasswordField loginPassword;
    @FXML
    public Label loginErrorLabel;
    @FXML
    public Pane loginPane, registerPane;

    public void initService(AppService appservice) {
        this.appservice = appservice;
    }

    @FXML
    public void onNewUserButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterUI.fxml"));
        Parent uiRoot = loader.load();
        Controller controller = loader.getController();
        controller.initService(appservice);
        loginPane.getChildren().add(uiRoot);
    }

    @FXML
    public void onLoginButtonClick() throws SQLException, IOException {
        String username = loginUsername.getText().trim();
        String password = loginPassword.getText().trim();
        User u;
        if (username.isEmpty() || password.isEmpty()) {
            loginErrorLabel.setText("Täytä kaikki kentät!");
        } else {
            System.out.println("Loginbutton toimii");
        }
    }

}
