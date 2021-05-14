package curriculatorapp.controller;

import curriculatorapp.dao.CoursesDao;
import curriculatorapp.dao.CurriculumDao;
import curriculatorapp.dao.UserDao;
import curriculatorapp.domain.Course;
import curriculatorapp.domain.Curriculum;
import curriculatorapp.logic.AppService;
import curriculatorapp.logic.LoginService;
import curriculatorapp.logic.Service;
import curriculatorapp.ui.CurriculatorUi;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Luokka Main-näkymän kontrollointiin.
 */
public class AppController implements Controller {

    private Course course;
    private Curriculum curriculum;
    private AppService appservice;
    @FXML
    private Pane popup;

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
    private TextField courseNameTextfield;
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
    private VBox courseNodes;
    @FXML
    ChoiceBox gradeChoiceBox;
    @FXML
    Button courseDone;

    /**
     * Metodi asettaa tälle luokalla logiikkaluokan ja asettaa näytettävät
     * labelit.
     *
     * @param appservice Annetaan kontrollerin käyttämä logiikka sitä
     * kutsuttaessa.
     */
    @Override
    public void initService(Service appservice) {

        try {
            this.appservice = (AppService) appservice;
            courseNodes = new VBox(10);
            popup.setVisible(false);
            setChoices();
            this.curriculum = (Curriculum) this.appservice.findCurriculum();
            courselist.setContent(reDrawList());
            setLabels();
            setStats();
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodi kenttien täyttämiseen.
     *
     */
    @FXML
    public void setLabels() {
        nameLabel.setText(appservice.getLoggedUser().getName() + "!");
        curriculumNameLabel.setText(curriculum.getCurriculumName());
        courseMeterLabelUpper.setText(curriculum.getChoice() + "ttä");
        courseMeterLabelLower.setText(curriculum.getChoice() + "ttä");
    }

    /**
     * Metodi ulos kirjautumiseen.
     *
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @FXML
    public void onLogoutButtonClick() throws IOException, SQLException {
        String url = "curriculatorapp.db";
        UserDao userdao = new UserDao(url);
        CurriculumDao curriculumdao = new CurriculumDao(url);
        CoursesDao coursesdao = new CoursesDao(url);
        LoginService loginservice = new LoginService(userdao, curriculumdao, coursesdao);
        CurriculatorUi.loadNewScene("LoginUI", loginservice);

    }

    /**
     * Metodi statistiikan asettamiseen.
     *
     * @throws java.sql.SQLException
     */
    @FXML
    public void setStats() throws SQLException {
        doneCoursesAmount.setText("" + appservice.coursesDoneAmount());
        courseAverage.setText(appservice.calculateAverageGrade());
        coursesLeft.setText(appservice.calculateLeftCourses());
        progressIndicator.setProgress(appservice.getProgressPercent());
    }

    /**
     * Metodi lisää uuden kurssin listaan.
     *
     * @throws java.sql.SQLException
     */
    @FXML
    public void onAddCourseButtonClick() throws SQLException {
        String courseName = courseNameTextfield.getText();
        String courseScope = scopeTextfield.getText();
        if ((courseName.trim().isEmpty()) || (courseScope.trim().isEmpty())) {
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
                setNotifications("notNumber");
            }

            courselist.setContent(reDrawList());
        }
    }

    /**
     * Metodi listan näyttämiseen ja päivittämiseen.
     *
     * @return palauttaa VBoxin
     * @throws java.sql.SQLException
     */
    public Node reDrawList() throws SQLException {

        List<Course> courses = appservice.findAllCourses();
        courseNodes.getChildren().clear();
        courseNodes.setMaxWidth(280);
        courseNodes.setMinWidth(280);
        for (Course c : courses) {
            if (!c.isDone()) {
                courseNodes.getChildren().add(createCourseNode(c));

            }
        }
        return courseNodes;

    }

    /**
     * Metodi asettaa ilmoituskenttään ilmoitukset. Mikäli tekstikentät ovat
     * tyhjiä kirjautuessa tai jos käyttäjätunnus/salasana on virheellinen
     *
     * @param reason Mahdollisen virheilmoituksen syy, kuten empty- tyhjä
     * kenttä.
     */
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

    /**
     * Metodi luo HBox noden kurssilistaan.
     *
     * @param course Luotava kurssi
     * @return palauttaa HBoxin
     */
    public Node createCourseNode(Course course) {
        HBox box = new HBox();
        box.setMinSize(300, 40);
        box.getStyleClass().add("cardpane-layout");

        StackPane aPane = new StackPane();
        aPane.getStyleClass().add("sidebar");
        aPane.setMinSize(40, 40);

        Label scope = new Label("" + course.getCourseScope());
        scope.getStyleClass().add("whitetext");

        aPane.getChildren().add(scope);
        aPane.setAlignment(scope, Pos.CENTER);

        StackPane bPane = new StackPane();
        bPane.setMinSize(180, 40);

        Label name = new Label("   " + course.getCourseName());
        name.getStyleClass().add("smallblacktext");

        bPane.getChildren().add(name);
        bPane.setAlignment(name, Pos.CENTER_LEFT);

        StackPane cPane = new StackPane();
        cPane.setMinSize(80, 40);
        Button button = new Button("Tehty!");

        button.setOnAction(e -> {
            coursenamePopupLabel.setText(course.getCourseName());
            popup.setVisible(true);
            courseDone.setOnAction(d -> {
                String grade = String.valueOf(gradeChoiceBox.getValue());
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

        cPane.getChildren().add(button);
        cPane.setAlignment(button, Pos.CENTER);
        box.setPadding(new Insets(0, 5, 0, 5));

        box.getChildren().addAll(aPane, bPane, cPane);
        return box;
    }

    /**
     * Metodi piilottaa kurssinsuoritus popupin
     *
     * @throws java.sql.SQLException
     */
    @FXML
    public void onHidePopupButtonClick() throws SQLException {
        popup.setVisible(false);
    }

    /**
     * Metodi asettaa arvosanavalikolle vaihtoehdot
     *
     */
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
