module com.example.guser {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.guser to javafx.fxml;
    exports com.example.guser;
    exports com.example.guser.controller;
    opens com.example.guser.controller to javafx.fxml;
    exports com.example.guser.test;
    opens com.example.guser.test to javafx.fxml;
}