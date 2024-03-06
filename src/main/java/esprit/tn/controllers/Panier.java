package esprit.tn.controllers;

import esprit.tn.models.Formation;
import esprit.tn.util.MaConnexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Panier implements Initializable {

    @FXML
    private GridPane menuGrid;

    @FXML
    private TableColumn<Panier, String> menuName;

    @FXML
    private TableColumn<Panier, String> menuPrice;

    @FXML
    private TableColumn<Panier, Integer> menuQuantity;

    @FXML
    private ScrollPane menuScroll;

    @FXML
    private TableView<Panier> menuTable;

    @FXML
    private Label menuTotal;
    private String imageUrl;
    private ObservableList<Formation> formation = FXCollections.observableArrayList();
    private Connection connection;


    public Panier() {
        this.connection = MaConnexion.getInstance().getCnx();
    }
    @FXML
    void Affiche_Panier(javafx.event.ActionEvent event) {
        try {
            // Charger le fichier FXML de la nouvelle vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficherpanier.fxml"));
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


    public ObservableList<Formation> getMenuData() {
        String sql = "SELECT * FROM formation";
        ObservableList<Formation> dataList = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Formation formation;
            while (resultSet.next()) {
                formation = new Formation(
                        resultSet.getInt("idFormation"),
                        resultSet.getString("typeF"),
                        resultSet.getString("img"),
                        resultSet.getFloat("prix"),
                        resultSet.getString("duree")
                );
                dataList.add(formation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }
    public void displayMenu() {
        formation.clear();
        formation.addAll(getMenuData());
        int row = 0;
        int column = 0;
        menuGrid.getRowConstraints().clear();
        menuGrid.getColumnConstraints().clear();
        for (int i = 0; i < formation.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/carte.fxml"));
                AnchorPane pane = loader.load();
                Carte carteController = loader.getController();
                carteController.setData(formation.get(i));
                if (column == 5) {
                    column = 0;
                    row += 1;
                }
                menuGrid.add(pane, column++, row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayMenu();
    }


}