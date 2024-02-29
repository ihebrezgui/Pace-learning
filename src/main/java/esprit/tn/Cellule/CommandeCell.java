package esprit.tn.Cellule;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import esprit.tn.models.Commande;

public class CommandeCell extends ListCell<Commande> {

    @Override
    protected void updateItem(Commande commande, boolean empty) {
        super.updateItem(commande, empty);

        if (empty || commande == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Création du label pour afficher les détails de la commande
            Label label = new Label();
            label.setStyle("-fx-font-weight: bold;"); // Appliquer un style au label
            label.setWrapText(true); // Permettre le retour à la ligne automatique

            // Construction de la chaîne de caractères avec les détails de la commande
            StringBuilder sb = new StringBuilder();
            sb.append("Référence: ").append(commande.getIdc()).append("\n")
                    .append("Nom: ").append(commande.getNom()).append("\n")
                    .append("Prénom: ").append(commande.getPrenom()).append("\n")
                    .append("Téléphone: ").append(commande.getTel()).append("\n")
                    .append("Email: ").append(commande.getMail()).append("\n")
                    .append("Adresse: ").append(commande.getAddress()).append("\n")
                    .append("Panier: ").append(commande.getPanier());

            // Définition du texte du label
            label.setText(sb.toString());

            setGraphic(label);
        }
    }
}