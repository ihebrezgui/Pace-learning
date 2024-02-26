package esprit.tn.controllers;

import esprit.tn.models.Formation;
import esprit.tn.util.MaConnexion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Carte implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private AnchorPane cardForm;

    @FXML
    private Label productTypeFLabel;

    @FXML
    private Label productPriceLabel;

    @FXML
    private Label productDescriptionLabel;

    @FXML
    private ImageView productImageView;

    @FXML
    private Spinner<Integer> quantitySpinner;


   private Image image;
    private String imageUrl;
    private Formation formation;
    private int productId;
    private Connection connection;
    private Alert alert;

    public Carte() {
        connection = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }

    public void setData(Formation formation ) {
        this.formation = formation;

        productId = formation.getIdFormation();
        productTypeFLabel.setText(formation.getTypeF());
        productPriceLabel.setText(String.valueOf(formation.getPrix()));
        productDescriptionLabel.setText(formation.getDuree());
    }



    public void setQuantity() {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        quantitySpinner.setValueFactory(spinnerValueFactory);
    }

    public void add(javafx.event.ActionEvent event) {
        int quantity = quantitySpinner.getValueFactory().getValue();
        if (quantity == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Quantité invalide !");
            alert.showAndWait();
        } else {
            try {
                if (formation == null) {
                    System.out.println("L'objet produit n'est pas initialisé !");
                    return;
                }
                String insertData = "INSERT INTO panier (quantite, nom, prix, prod_id) VALUES ( ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertData);
                preparedStatement.setInt(1, quantity);
                preparedStatement.setString(2, productTypeFLabel.getText());
               //double total = quantity * formation.getPrix();
                preparedStatement.setFloat(3, formation.getPrix());
                //preparedStatement.setDouble(3, total);
                preparedStatement.setInt(4, productId);

                preparedStatement.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Produit ajouté avec succès !");
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}