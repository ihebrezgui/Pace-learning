package esprit.tn.controllers;

import esprit.tn.Cellule.CommandeCell;
import esprit.tn.models.Commande;
import esprit.tn.services.CommandeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Affichecommande {
    @FXML
    private Button passerQR;

    @FXML
    private Button qrcode;
    @FXML
    private TextField textFieldTel;

    @FXML
    private TextField textFieldAdresse;

    @FXML
    private TextField textFieldNom;
    @FXML
    private Button passer;

    @FXML
    private TextField textFieldPrenom;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldRecherche;

    @FXML
    private ListView<Commande> listViewCommande;

    ObservableList<Commande> list = FXCollections.observableArrayList();
    private final CommandeService cs = new CommandeService();
    private CommandeService css = new CommandeService();

    public int idc, tel,prix;
    public String nom, prenom, mail, address, panier;


    public String getPanier() {
        return panier;
    }

    public int getIdc() {
        return idc;
    }

    public int getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAddress(String addr) {
        this.address = address;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setPanier(String panier) {
        this.panier = panier;
    }

    @FXML
    void initialize() {
        CommandeService cs = new CommandeService();
        try {
            List<Commande> co = cs.recuperer();
            ObservableList<Commande> ob = FXCollections.observableList(co);
            listViewCommande.setCellFactory(param -> new CommandeCell());
            listViewCommande.setItems(ob);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Retour(javafx.event.ActionEvent  event) {
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
    @FXML
    public void SetValue(javafx.scene.input.MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Commande selected = listViewCommande.getSelectionModel().getSelectedItem();
        if (selected != null) {
            textFieldTel.setText(String.valueOf(selected.getTel()));
            textFieldNom.setText(selected.getNom());
            textFieldPrenom.setText(selected.getPrenom());
            textFieldEmail.setText(selected.getMail());
            textFieldAdresse.setText(selected.getAddress());
            idc = selected.getIdc();
            panier = selected.getPanier();
        }
    }



    @FXML
    void suppcommande(javafx.event.ActionEvent event) {
        Commande selected = listViewCommande.getSelectionModel().getSelectedItem();
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
                        css.supprimer(selected.getIdc());
                        initialize();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else if (type == noButton) {
                    initialize();
                } else {
                    initialize();
                }
            });
        }
    }


    @FXML
    void updatecommande(javafx.event.ActionEvent event) throws SQLException {
        String nom = textFieldNom.getText().trim();
        String prenom = textFieldPrenom.getText().trim();
        String telStr = textFieldTel.getText().trim();
        String mail = textFieldEmail.getText().trim();
        String address = textFieldAdresse.getText().trim();

        // Vérification du champ nom
        if (nom.isEmpty()) {
            afficherMessageErreur("Nom", "Veuillez saisir un nom.");
            return;
        }
        if (!nom.matches("[a-zA-Z]+")) {
            afficherMessageErreur("Nom", "Le nom doit contenir uniquement des lettres.");
            return;
        }

        // Vérification du champ prénom
        if (prenom.isEmpty()) {
            afficherMessageErreur("Prénom", "Veuillez saisir un prénom.");
            return;
        }
        if (!prenom.matches("[a-zA-Z]+")) {
            afficherMessageErreur("Prénom", "Le prénom doit contenir uniquement des lettres.");
            return;
        }

        // Vérification du champ téléphone
        if (telStr.isEmpty()) {
            afficherMessageErreur("Téléphone", "Veuillez saisir un numéro de téléphone.");
            return;
        }
        if (!telStr.matches("\\d+")) {
            afficherMessageErreur("Téléphone", "Le numéro de téléphone doit être un nombre.");
            return;
        }

        int tel;
        try {
            tel = Integer.parseInt(telStr);
        } catch (NumberFormatException e) {
            afficherMessageErreur("Téléphone", "Veuillez saisir un numéro de téléphone valide.");
            return;
        }

        // Vérification du champ email
        if (mail.isEmpty()) {
            afficherMessageErreur("Email", "Veuillez saisir une adresse email.");
            return;
        }
        if (!isValidEmail(mail)) {
            afficherMessageErreur("Email", "Veuillez saisir une adresse email valide.");
            return;
        }

        // Vérification du champ adresse
        if (address.isEmpty()) {
            afficherMessageErreur("Adresse", "Veuillez saisir une adresse.");
            return;
        }
        if (!address.matches("[a-zA-Z0-9\\s]+")) {
            afficherMessageErreur("Adresse", "L'adresse doit contenir uniquement des lettres, des chiffres et des espaces.");
            return;
        }

        // Si toutes les validations sont passées avec succès, la commande est créée et modifiée, et une alerte de succès est affichée.
        Commande c = new Commande(idc, tel, nom, prenom, mail, address, panier);
        css.modifier(c);

        Alert al = new Alert(Alert.AlertType.WARNING);
        al.setTitle("Succès");
        al.setContentText("Produit Modifié");
        al.showAndWait();

        initialize();
    }




    // Méthode utilitaire pour afficher un message d'erreur
    private void afficherMessageErreur(String champ, String message) {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Erreur de saisie");
        al.setHeaderText(null);
        al.setContentText("Erreur dans le champ " + champ + ": " + message);
        al.showAndWait();
    }

    // Méthode utilitaire pour valider le format d'une adresse email
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @FXML
    void recherchecommande(javafx.scene.input.KeyEvent event) {
        // Create a new FilteredList with the original list as the source
        FilteredList<Commande> filter = new FilteredList<>(listViewCommande.getItems(), ev -> true);

        // Add a listener on the text property of the search field to update the predicate of the FilteredList
        textFieldRecherche.textProperty().addListener((observable, oldValue, searchText) -> {
            // Update the predicate based on the search text
            filter.setPredicate(commande -> {
                if (searchText == null || searchText.isEmpty()) {
                    return true; // Show all items when the filter text is empty.
                }

                String lowerCaseFilter = searchText.toLowerCase();

                if (commande.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches nom.
                }

                return false; // Does not match.
            });
        });

        // Create a new SortedList and attach it to the FilteredList
        SortedList<Commande> sortedList = new SortedList<>(filter);

        // Set the comparator for the sorted list
        sortedList.setComparator(Comparator.comparing(Commande::getNom));

        // Set the items of the ListView to the sorted list
        listViewCommande.setItems(sortedList);

        // Refresh the ListView to update the filtered items
        listViewCommande.refresh();
    }
    @FXML
    void triasc(javafx.event.ActionEvent event) {
        List<Commande> tempList = new ArrayList<>(listViewCommande.getItems());

        tempList.sort(Comparator.comparing(Commande::getIdc));

        ObservableList<Commande> updatedList = FXCollections.observableArrayList(tempList);
        listViewCommande.setItems(updatedList);
    }

    @FXML
    void tridesc(javafx.event.ActionEvent event) {
        List<Commande> tempList = new ArrayList<>(listViewCommande.getItems());

        tempList.sort(Comparator.comparing(Commande::getIdc).reversed());

        ObservableList<Commande> updatedList = FXCollections.observableArrayList(tempList);
        listViewCommande.setItems(updatedList);
    }

    public void pdf(javafx.event.ActionEvent actionEvent) throws IOException {
        ObservableList<Commande> data = listViewCommande.getItems();

        try {
            // Créez un nouveau document PDF
            PDDocument document = new PDDocument();

            // Créez une page dans le document
            PDPage page = new PDPage();
            document.addPage(page);

            // Obtenez le contenu de la page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Définissez la police de caractères
            contentStream.setFont(PDType1Font.HELVETICA, 12); // Exemple de police (Helvetica, taille 12), ajustez selon vos besoins

            // Écrivez du texte dans le document
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);

            for (Commande commande : data) {
                String ligne = "ID : " + commande.getIdc() + "     Nom : " + commande.getNom()
                        + "     tel : " + commande.getTel() + "     mail : " + commande.getMail()
                        + "    addresse : " + commande.getAddress() + "     Votre Panier : " + commande.getPanier();
                contentStream.showText(ligne);
                contentStream.newLine();
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.endText();

            // Fermez le contenu de la page
            contentStream.close();

            String outputPath = "C:/Users/ihebr/Desktop/pdf_pidev/pdf.pdf";
            File file = new File(outputPath);
            document.save(file);

            // Fermez le document
            document.close();

            System.out.println("Le PDF a été généré avec succès.");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void Passer(ActionEvent event) {
        try {
            // Charger le fichier FXML de la nouvelle vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation.fxml"));
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

    public void passerQR(javafx.event.ActionEvent event) {
        try {
            // Charger le fichier FXML de la nouvelle vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CodeQR.fxml"));
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
}