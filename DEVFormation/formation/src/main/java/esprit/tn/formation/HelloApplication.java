package esprit.tn.formation;

import esprit.tn.formation.utils.main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/Authentifier.fxml"));
                Parent root = loader.load();
                primaryStage.setTitle("Gestion Cours");
                primaryStage.setScene(new Scene(root, 800, 800));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            launch(args);
        }
}
