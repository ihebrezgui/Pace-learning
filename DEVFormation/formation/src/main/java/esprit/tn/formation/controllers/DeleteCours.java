package esprit.tn.formation.controllers;

import esprit.tn.formation.models.Cours;
import esprit.tn.formation.services.CoursService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class DeleteCours {
    @FXML
    private TextField IdCours;

    public void delete(ActionEvent actionEvent) {
        String idCoursText = IdCours.getText().trim();
        if (idCoursText.isEmpty()) {
            showAlert("Error", "IdCours cannot be empty.");
            return;
        }
        try {
            int id = Integer.parseInt(idCoursText);
            CoursService Fs = new CoursService();
            Fs.delete(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours deleted");
            alert.showAndWait();

            // Refresh the list of Courses in ListCours.fxml
            refreshList();
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid integer for IdCours.");
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
            System.err.println((e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void refreshList() {
        try {
            CoursService Fs = new CoursService();
            List<Cours> Courss = Fs.getAll();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/Cours/ListCours.fxml"));
            Parent root = loader.load();
            ListCours listCoursController = loader.getController();
            listCoursController.refreshList(Courss); // Pass the list of Courss to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAll() {
        try {
            CoursService Fs = new CoursService();
            List<Cours> Courss = Fs.getAll();

            // Update the list view in ListCours.fxml with the new list of Courss
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListCours.fxml"));
            Parent root = loader.load();
            ListCours listCoursController = loader.getController();
            listCoursController.refreshList(Courss); // Pass the list of Courss to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
