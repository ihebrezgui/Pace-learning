module tn.esprit.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens tn.esprit.project to javafx.fxml;
    exports tn.esprit.project;
    exports tn.esprit.project.test;
    opens tn.esprit.project.test to javafx.fxml;
    exports tn.esprit.project.controllers;
    opens tn.esprit.project.controllers to javafx.fxml;
}