package esprit.tn.controllers;

import esprit.tn.Cellule.PanierCell;
import esprit.tn.models.Commande;
import esprit.tn.models.Panier;
import esprit.tn.services.CommandeService;
import esprit.tn.services.PanierService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import esprit.tn.util.MaConnexion;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Validercommande {
    @FXML
    private URL location;
    @FXML
    private ImageView imageV;
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField adresseField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telField;

    @FXML
    private ListView<Panier> tableCommandeListView;

    @FXML
    private Label totalLabel;

    private int total;

    private Connection connection;

    public Validercommande() {
        connection = MaConnexion.getInstance().getCnx();
    }
    @FXML
    void Details(javafx.event.ActionEvent  event) {
        try {
            // Charger le fichier FXML de la nouvelle vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichecommande.fxml"));
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
    void ajouter(ActionEvent event) {
        CommandeService commandeService = new CommandeService();
        Commande commande = new Commande();
        commande.setNom(nomField.getText());
        commande.setPrenom(prenomField.getText());
        commande.setAddress(adresseField.getText());
        commande.setTel(Integer.parseInt(telField.getText()));
        commande.setMail(emailField.getText());

        // Vérification si telField contient une valeur numérique
        String telText = telField.getText();
        if (!telText.matches("\\d+")) { // Vérifie si la chaîne contient uniquement des chiffres
            afficherErreur("Le numéro de téléphone doit être un nombre.");
            return; // Arrêter la méthode si le numéro de téléphone n'est pas un nombre
        }

        // Vérification de l'adresse email
        String email = emailField.getText();
        if (!isValidEmail(email)) {
            afficherErreur("Adresse e-mail invalide.");
            return; // Arrêter la méthode si l'adresse e-mail est invalide
        }

        // Vérification des autres champs
        if (nomField.getText().isEmpty()) {
            afficherErreur("Veuillez saisir votre nom.");
            return;
        }

        if (prenomField.getText().isEmpty()) {
            afficherErreur("Veuillez saisir votre prénom.");
            return;
        }

        if (adresseField.getText().isEmpty()) {
            afficherErreur("Veuillez saisir votre adresse.");
            return;
        }
        if (telText.isEmpty()) {
            afficherErreur("Téléphone non valide. Veuillez saisir un numéro de téléphone.");
            return;
        }
        if (telField.getText().isEmpty() || nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() || adresseField.getText().isEmpty()) {
            afficherErreur("Veuillez remplir tous les champs.");
            return;
        }

        // Convertir la valeur du champ telField en entier
        commande.setTel(Integer.parseInt(telText));

        try {
            commandeService.ajouter(commande);
            afficherConfirmation("Commande ajoutée");
        } catch (SQLException ex) {
            afficherErreur("Erreur lors de l'ajout de la commande");
        }
    }
    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Méthode pour vérifier si l'adresse e-mail est valide
    private boolean isValidEmail(String email) {
        // Vous pouvez utiliser une expression régulière pour valider l'adresse e-mail
        // Ceci est juste un exemple simple
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }



    // Méthode pour afficher une boîte de dialogue de confirmation
    private void afficherConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void calculerTotal() throws SQLException {
        String req = "SELECT SUM(prix) FROM panier";
        PreparedStatement pre = connection.prepareStatement(req);
        ResultSet res = pre.executeQuery();
        if (res.next()) {
            total = res.getInt(1);
        }
        totalLabel.setText(total + " DT");
    }

    @FXML
    void initialize() {
        PanierService panierService = new PanierService();
        try {
            List<Panier> commandes = panierService.recuperer();
            ObservableList<Panier> obCommandes = FXCollections.observableList(commandes);
            tableCommandeListView.setItems(obCommandes);
            tableCommandeListView.setCellFactory(param -> new PanierCell());
            calculerTotal();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
