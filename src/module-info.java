module tn.esprit.gestiondonation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens tn.esprit.gestiondonation to javafx.fxml;
    exports tn.esprit.gestiondonation.utils;
    exports tn.esprit.gestiondonation.test;
    opens tn.esprit.gestiondonation.test to javafx.fxml;
}