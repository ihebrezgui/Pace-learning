package esprit.tn.controllers;

import esprit.tn.models.Commande;
import esprit.tn.util.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;


public class CodeQR {
    @FXML
    private ImageView QRCode;
    @FXML
    private Button qrcodebtn;
    public int idc, tel;
    public String nom, prenom, mail, address, panier;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getMail() {
        return mail;
    }
    public String getPanier() {
        return panier;
    }

    public void setPanier(String panier) {
        this.panier = panier;
    }



    private Connection connection;

    public CodeQR() {
        connection = MaConnexion.getInstance().getCnx();
    }

    public void generateQrCode(ActionEvent event) throws SQLException {
        String sql = "SELECT * FROM commande ";
        ObservableList<Commande> QR = FXCollections.observableArrayList();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idc"); // Récupérer l'ID de la commande
                String nom = rs.getString("nom"); // Récupérer le nom
                String prenom = rs.getString("prenom"); // Récupérer le prénom
                String mail = rs.getString("mail"); // Récupérer l'adresse e-mail
                String address = rs.getString("address"); // Récupérer l'adresse

                Commande commande = new Commande(id, nom, prenom, mail, address, rs.getString("panier"));
                QR.add(commande);
            }

            String qrData = String.valueOf(QR);
            // Générer et afficher le code QR
            generateAndDisplayQRCode(qrData);
        }
    }

    private void generateAndDisplayQRCode(String qrData) {
        try {
            // Configuration pour générer le code QR
            Map<EncodeHintType, Object> hint = new HashMap<>();
            hint.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Générer le code QR avec ZXing
            BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, 184, 199, hint);

            QRCode.setFitWidth(200);
            QRCode.setFitHeight(200);

            // Convertir la matrice en image JavaFX
            Image QRCodeImage = matrixToImage(matrix);

            // Afficher l'image du code QR dans l'ImageView
            QRCode.setImage(QRCodeImage);

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("QRCode Succes");
            alert.setContentText("Code QR généré avec succees");
            alert.showAndWait();

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private Image matrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixelColor = matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                pixelWriter.setArgb(x, y, pixelColor);
            }
        }

        System.out.println("Matrice convertie en image avec succès");

        return writableImage;


    }


    @FXML
    void retour(javafx.event.ActionEvent event) {
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
}
