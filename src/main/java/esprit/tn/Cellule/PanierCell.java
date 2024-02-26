package esprit.tn.Cellule;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import esprit.tn.models.Panier;

public class PanierCell extends ListCell<Panier> {

    @Override
    protected void updateItem(Panier item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Création du label pour afficher les détails du panier
            Label label = new Label();
            label.setStyle("-fx-font-weight: bold;"); // Appliquer un style au label
            label.setWrapText(true); // Permettre le retour à la ligne automatique

            // Construction de la chaîne de caractères avec les détails du panier
            StringBuilder sb = new StringBuilder();
                     sb.append("Type Formation: ").append(item.getNom()).append("\n")
                    .append("Quantité: ").append(item.getQuantite()).append("\n")
                    .append("Coût unitaire: ").append(String.format("%.2f", item.getPrix()));

            // Définition du texte du label
            label.setText(sb.toString());

            setGraphic(label);
        }
    }
}