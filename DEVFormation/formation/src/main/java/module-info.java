module esprit.tn.formation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mail;
    requires org.apache.pdfbox;
    requires javafx.swing;
    requires unirest.java;
    requires com.fasterxml.jackson.databind;

    opens esprit.tn.formation to javafx.fxml;
    opens esprit.tn.formation.controllers to javafx.fxml;
    opens esprit.tn.formation.models to javafx.fxml, javafx.base, com.fasterxml.jackson.databind;

    exports esprit.tn.formation;
    opens esprit.tn.formation.services to javafx.fxml;

}