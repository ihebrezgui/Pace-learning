package esprit.tn.formation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.services.FormationService;

import java.io.IOException;
import java.util.List;

public class DeleteFormation {

    @FXML
    private TextField IdFormation;
    private boolean validateInput() {
        String idFormationText = IdFormation.getText();
        if (idFormationText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'identifiant de la formation ne peut pas être vide.");
            alert.showAndWait();
            return false;
        }

        if (!isNumeric(idFormationText)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'identifiant de la formation doit être numérique.");
            alert.showAndWait();
            return false;
        }

        Formation formation = Fs.searchidF(Integer.parseInt(idFormationText));
        if (formation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucune formation avec cet identifiant n'a été trouvée.");
            alert.showAndWait();
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private FormationService Fs = new FormationService();

    @FXML
    void delete(ActionEvent actionEvent) {
        if (!validateInput()) {
            return;
        }

        try {
            int id = Integer.parseInt(IdFormation.getText());
            Fs.delete(id);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Formation deleted");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.err.println((e.getMessage()));
            throw new RuntimeException(e);
        }
    }

    private void refreshList() {
        try {
            FormationService Fs = new FormationService();
            List<Formation> formations = Fs.getAll();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/tn/formation/ListFormation.fxml"));
            Parent root = loader.load();
            ListFormation listFormationController = loader.getController();
           // listFormationController.refreshList(formations); // Pass the list of formations to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void getAll() {
        try {
            FormationService Fs = new FormationService();
            List<Formation> formations = Fs.getAll();

            // Update the list view in ListFormation.fxml with the new list of formations
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/ListFormation.fxml"));
            Parent root = loader.load();
            ListFormation listFormationController = loader.getController();
            //listFormationController.refreshList(formations); // Pass the list of formations to refreshList()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
