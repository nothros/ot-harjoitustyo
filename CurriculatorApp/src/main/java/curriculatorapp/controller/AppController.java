package curriculatorapp.controller;

import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.logic.AppService;
import curriculatorapp.logic.Service;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Pane popup;
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
    private TextField courseNameTextfield, gradeTextfield;
    @FXML
    private TextField scopeTextfield;
    @FXML
    private Label errorLabel;
    @FXML
    private Label coursenamePopupLabel;
    @FXML
    private Label doneCoursesAmount;
    @FXML
    private Label courseAverage;
    @FXML
    private Label coursesLeft;
    @FXML
    private Label popupErrorLabel;
    @FXML
    private ProgressIndicator progressIndicator;
    private VBox todoNodes;
    @FXML
    ChoiceBox gradeChoiceBox;
    @FXML
    Button CourseDone;

    @Override
    public void initService(Service appservice) {

        try {
            this.appservice = (AppService) appservice;
            todoNodes = new VBox(10);
            popup.setVisible(false);
            setChoices();

            this.curriculum = (Curriculum) this.appservice.findCurriculum();
            System.out.print("Onnistui" + curriculum.toString());

            courselist.setContent(reDrawList());

            setLabels();
            setStats();
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodi kenttien täyttämiseen..
     *
     */
    @FXML
    public void setLabels() {
        nameLabel.setText(appservice.getLoggedUser().getName() + "!");
        curriculumNameLabel.setText(curriculum.getCurriculumName());
        courseMeterLabelUpper.setText(curriculum.getChoice() + "ttä");
        courseMeterLabelLower.setText(curriculum.getChoice() + "ttä");
    }

    @FXML
    public void setStats() throws SQLException {
        doneCoursesAmount.setText("" + appservice.coursesDoneAmount());
        courseAverage.setText(appservice.calculateAverageGrade());
        coursesLeft.setText(appservice.calculateLeftCourses());
        progressIndicator.setProgress(appservice.getProgressPercent());
    }

    @FXML
    public void onAddCourseButtonClick() throws SQLException {
        String courseName = courseNameTextfield.getText();
        String courseScope = scopeTextfield.getText();
        if ((courseName.trim().isEmpty()) || (courseScope.trim().isEmpty())) {
            System.out.println("TYHJÄ");
            setNotifications("empty");
        } else {

            try {
                Integer.parseInt(courseScope);
                int coursescope = Integer.valueOf(courseScope);
                if (coursescope > curriculum.getScope()) {
                    setNotifications("tooBigCourse");
                } else if (coursescope <= 0) {
                    setNotifications("zero");
                } else {
                    course = new Course(courseName, coursescope);
                    setNotifications("ok");
                    appservice.createCourse(courseName, coursescope);
                }

            } catch (NumberFormatException e) {
                System.out.print("PLLEFLEÅFL");
                setNotifications("notNumber");
            }

            courselist.setContent(reDrawList());
        }
    }

    public Node reDrawList() throws SQLException {

        List<Course> courses = appservice.findAllCourses();
        System.out.print(courses.toString());
        todoNodes.getChildren().clear();
        todoNodes.setMaxWidth(280);
        todoNodes.setMinWidth(280);
        for (Course c : courses) {
            System.out.println(c.toString() + " kurssi on " + c.isDone());
            if (!c.isDone()) {
                todoNodes.getChildren().add(createCourseNode(c));
            }
        }
        return todoNodes;

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
        if (reason.equals("tooBigCourse")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Laajuus on liian suuri!");
            emptyFields();
        }

        if (reason.equals("gradeEmpty")) {
            popupErrorLabel.setTextFill(Color.RED);
            popupErrorLabel.setText("Valitse arvosana!");

        }
        if (reason.equals("zero")) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Laajuuden on oltava suurempi kuin 0");
        }

    }

    /**
     * Metodi tyhjentää tekstikentät.
     */
    public void emptyFields() {
        courseNameTextfield.setText("");
        scopeTextfield.setText("");
    }

    public Node createCourseNode(Course course) {
        Label listLabel = new Label();
        HBox box = new HBox();
        box.setMinSize(290, 40);
        box.getStyleClass().add("cardpane-layout");

        Pane cpane = new Pane();
        cpane.getStyleClass().add("sidebar");
        cpane.setMinSize(40, 40);

        Label scope = new Label("" + course.getCourseScope());
        Label name = new Label(course.getCourseName());

        scope.getStyleClass().add("whitetext");
        name.setMinHeight(28);
        cpane.getChildren().add(scope);

        Button button = new Button("Done!");

        button.setOnAction(e -> {
            coursenamePopupLabel.setText(course.getCourseName());
            popup.setVisible(true);
            CourseDone.setOnAction(d -> {
                System.out.print("PAINETTU");
                String grade = String.valueOf(gradeChoiceBox.getValue());
                System.out.println(grade);
                if (!grade.trim().isEmpty()) {
                    try {
                        appservice.markDone(course, grade);
                        setStats();
                        gradeChoiceBox.getSelectionModel().select(0);
                        errorLabel.setText("");
                        popupErrorLabel.setText("");
                        popup.setVisible(false);

                    } catch (SQLException ex) {
                        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        reDrawList();

                    } catch (SQLException ex) {
                        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    setNotifications("gradeEmpty");
                }

            });

        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));

        box.getChildren().addAll(listLabel, cpane, name, spacer, button);
        return box;
    }

    @FXML
    public void onHidePopupButtonClick() throws SQLException {

        popup.setVisible(false);
    }

    @FXML
    public void setChoices() {
        gradeChoiceBox.getItems().add(0, "");
        gradeChoiceBox.getItems().add(1, "Hyväksytty");
        gradeChoiceBox.getItems().add(2, "1");
        gradeChoiceBox.getItems().add(3, "2");
        gradeChoiceBox.getItems().add(4, "3");
        gradeChoiceBox.getItems().add(5, "4");
        gradeChoiceBox.getItems().add(6, "5");
        gradeChoiceBox.getSelectionModel().select(0);
    }

}
