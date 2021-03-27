import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;




public class Paaohjelma extends Application {
    private Scene loginScene;
    private Scene registerScene;
      @Override
    public void start(Stage stage) {
        // Login scene
        GridPane loginGrid  = initGrid();
        loginGrid.setGridLinesVisible(true);
        loginScene = new Scene(loginGrid, 640, 480);
        
        Text scenetitle = new Text("Tervetuloa");
        loginGrid.add(scenetitle, 0, 0);

        Label userName = new Label("Käyttäjätunnus:");
        loginGrid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        loginGrid.add(userTextField, 1, 1);

        Button signIn = new Button("Sign in");
        Hyperlink registerLink= new Hyperlink("Rekisteröidy");
        
                registerLink.setOnAction(e->{
            stage.setScene(registerScene);
        });

        VBox hbBtn = new VBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(signIn);
        hbBtn.getChildren().add(registerLink);
        loginGrid.add(hbBtn, 1, 3);

        
        
       // Register scene
        GridPane registerGrid = initGrid();
        registerScene = new Scene(registerGrid, 640, 480);
        registerGrid.setGridLinesVisible(true);
        
        Text userCreationMessage = new Text("Luo uusi käyttäjä");
        registerGrid.add(userCreationMessage, 0, 0);


        Label fullName = new Label("Nimesi:");
        registerGrid.add(fullName, 0, 1);

        TextField nameTextField = new TextField();
        registerGrid.add(nameTextField, 1, 1);
        
        Label userNamecreate = new Label("Käyttäjätunnukseso:");
        registerGrid.add(userNamecreate, 0, 2);
        
        TextField userNameCreateTextField = new TextField();
        registerGrid.add(userNameCreateTextField, 1, 2);

        Button createUser = new Button("Login");
        Hyperlink backToLogin= new Hyperlink("Takaisin");
        
        
        backToLogin.setOnAction(e->{
        stage.setScene(loginScene);
        });

        VBox regVbox = new VBox(10);
        regVbox.setAlignment(Pos.BOTTOM_RIGHT);
        regVbox.getChildren().add(createUser);
        regVbox.getChildren().add(backToLogin);
        registerGrid.add(regVbox, 1, 3);

        
        
        




        
// setup primary stage
        stage.setScene(loginScene);
        stage.show();

        

    }
    public GridPane initGrid(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
// Set a CSS for the GridPane
        grid.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");
        
        return grid;
        }

    public static void main(String[] args) {
        launch(args);
    }

}  
