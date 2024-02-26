package esprit.tn.controllers;

import esprit.tn.Cellule.PanierCell;
import esprit.tn.models.Formation;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import esprit.tn.models.Panier;
import esprit.tn.services.PanierService;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.List;

public class Afficherpanier {
    ObservableList<Panier> list = FXCollections.observableArrayList();


    @FXML
    private TextField quantiteTextField;

    @FXML
    private TextField prixTextField;

    @FXML
    private ListView<Panier> panierListView;

    public int idp;
    public float prix;
    public String nom;

    private final PanierService ps = new PanierService();
    @FXML
    void Retour(javafx.event.ActionEvent event) {
        try {
            // Charger le fichier FXML de la nouvelle vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panier.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec la nouvelle vue chargée
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène dans la fenêtre
            stage.setScene(scene);

            // Montrer la nouvelle vue
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur de chargement de la vue
        }
    }
    @FXML
    void Passer_Commande(javafx.event.ActionEvent event) {
        try {
            // Charger le fichier FXML de la nouvelle vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/validercommande.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec la nouvelle vue chargée
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène dans la fenêtre
            stage.setScene(scene);

            // Montrer la nouvelle vue
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur de chargement de la vue
        }
    }
    public Formation formation ;

    @FXML
    void modifierPanier(javafx.event.ActionEvent event) {
        try {
            int nouvelleQuantite = Integer.parseInt(quantiteTextField.getText());
            Float prix  = Float.parseFloat(prixTextField.getText());
            Panier p = new Panier(idp, nouvelleQuantite, nom, prix, null);
            ps.modifier(p);

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Succès");
            a.setContentText("Panier modifié avec succès. Le nouveau prix est ");
            a.showAndWait();

            initialize();
        } catch (NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez saisir des valeurs numériques valides pour la quantité et le prix.");
            errorAlert.showAndWait();
        } catch (SQLException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur de base de données");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Une erreur est survenue lors de la modification du panier. Veuillez réessayer plus tard.");
            errorAlert.showAndWait();
        }
    }
    @FXML
    void supprimerPanier() {
        Panier selected = panierListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Voulez-Vous Supprimer cet Categorie?");
            alert.setContentText("Supprimer?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    try {
                        ps.supprimer(selected.getIdp());
                        initialize();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type == noButton || type == cancelButton) {
                    initialize();
                }
            });
        }
    }

    @FXML
    void triasc() {
        List<Panier> tr;
        try {
            tr = ps.tri_par_prix_asc();
            ObservableList<Panier> updatedList = FXCollections.observableArrayList(tr);
            panierListView.setItems(updatedList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tridesc() {
        List<Panier> tr;
        try {
            tr = ps.tri_par_prix_desc();
            ObservableList<Panier> updatedList = FXCollections.observableArrayList(tr);
            panierListView.setItems(updatedList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        try {
            List<Panier> co = ps.recuperer();
            ObservableList<Panier> ob = FXCollections.observableList(co);
            panierListView.setCellFactory(param -> new PanierCell());
            panierListView.setItems(ob);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void SetValue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Panier selected = panierListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            quantiteTextField.setText(String.valueOf(selected.getQuantite()));
            prixTextField.setText(String.valueOf(selected.getPrix()));
            idp = selected.getIdp();
            nom = selected.getNom();
        }
    }
}