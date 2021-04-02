module curriculatorapp.ui {
    requires javafx.controls;
    requires javafx.fxml;

    opens curriculatorapp.ui to javafx.fxml;
    exports curriculatorapp.ui;
}