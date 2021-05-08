package curriculatorapp.controller;

import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.ui.CurriculatorUi;
import curriculatorapp.logic.AppService;
import curriculatorapp.logic.Service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author ehorrosw
 */
public class AppController implements Controller {
    private Course course;
    private Curriculum curriculum;
    private AppService appservice;
    @FXML
    private Label courselabel;
    @FXML
    private ScrollPane courselist;
    @FXML
    private Label nameLabel;
    @FXML
    private Label curriculumNameLabel;
    @FXML
    private Label courseMeterLabelLower;
    @FXML
    private Label courseMeterLabelUpper;
    @FXML
    private Button addCourseButton;
    @FXML
    private TextField courseNameTextfield;
    @FXML
    private TextField scopeTextfield;
    @FXML
    private Label errorLabel;
    
    @Override
    public void initService(Service appservice) {

        this.appservice = (AppService) appservice;


        try {
            this.curriculum = (Curriculum) this.appservice.findCurriculum();
            System.out.print("ONnistui");
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNameLabels();

    }

    /**
     * Metodi kenttien täyttämiseen..
     *
     */
    @FXML
    public void setNameLabels() {
        nameLabel.setText(appservice.getLoggedUser().getName() + "!");
        curriculumNameLabel.setText(curriculum.getCurriculumName());
        courseMeterLabelUpper.setText(curriculum.getChoice() + "ttä");
        courseMeterLabelLower.setText(curriculum.getChoice() + "ttä");
    }

    @FXML
    public void onAddCourseButtonClick() throws SQLException {
        String courseName=courseNameTextfield.getText();
        String courseScope=scopeTextfield.getText();
        if ((courseName.trim().isEmpty()) || (courseScope.trim().isEmpty())) {
            System.out.println("TYHJÄ");
            setNotifications("empty");
        }else{
            
        try {
            Integer.parseInt(courseScope);
            int coursescope = Integer.valueOf(courseScope);
           
            course = new Course(courseName, coursescope);
            setNotifications("ok");
            appservice.createCourse(appservice.getLoggedUser().getUsername(), courseName, coursescope);
        } catch (NumberFormatException e) {
            System.out.print("PLLEFLEÅFL");
            setNotifications("notNumber");
        }
        //VBox  todoNodes = new VBox(10);
        //todoNodes.setMaxWidth(280);
   //     todoNodes.setMinWidth(280);    
    
       // todoNodes.getChildren().add(createTodoNode(course));
        
   // courselist.setContent(todoNodes);
    }
    }
       public void setNotifications(String reason) {

        if (reason.equals("empty")) {
            errorLabel.setTextFill(Color.RED);
           errorLabel.setText("Täytä kaikki kentät!");

        }
        if (reason.equals("ok")) {
            errorLabel.setTextFill(Color.GREEN);
           errorLabel.setText("Kurssi lisätty!");
           emptyFields();
        }
        if (reason.equals("notNumber")) {
            errorLabel.setTextFill(Color.RED);
           errorLabel.setText("Laajuuden on oltava numero!");
            emptyFields();

        }

    }

    /**
     * Metodi tyhjentää tekstikentät.
     */
    public void emptyFields() {
        courseNameTextfield.setText("");
        scopeTextfield.setText("");
    }
    
     /*  public Node createTodoNode(Course course) {
        HBox box = new HBox(10);
        Label label  = new Label(course.getCourseName());
        label.setMinHeight(28);
        Button button = new Button("done");
      
                
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));
        
        box.getChildren().addAll(label, spacer, button);
        return box;
    }
*/
    
    
    }


