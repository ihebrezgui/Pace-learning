
package esprit.tn.test;
import esprit.tn.models.Panier;
import esprit.tn.services.PanierService;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        PanierService css = new PanierService();

        // Créer un objet Panier avec des valeurs appropriées pour les tests
        Panier panier = new Panier(150, 10, "iheb", 105.0f, "23");

        // Appeler la méthode modifier sans bloc try-catch
        css.modifier(panier);

        System.out.println("Modification du panier effectuée avec succès.");
    }
}