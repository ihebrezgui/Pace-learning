package tn.esprit.gestiondonation.utils;

import tn.esprit.gestiondonation.models.Don;
import tn.esprit.gestiondonation.models.Give;
import tn.esprit.gestiondonation.models.Request;
import tn.esprit.gestiondonation.service.DonService;
import tn.esprit.gestiondonation.service.GiveService;
import tn.esprit.gestiondonation.service.RequestService;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main (String [] args) {
        MyDatabase db = MyDatabase.getInstance();
        MyDatabase db2 = MyDatabase.getInstance();
        System.out.println(db);
       GiveService don = new GiveService();
        DonService dons = new DonService();
        RequestService ds = new RequestService();
       Give D = new Give("donateur régulier",700, Date.valueOf("2001-04-17"),"nermineziadi@gmail.com", 2955533, "etudiant", "par carte bancaire",500);
        Request donataire = new Request("formation java","ner@gmail.com",Date.valueOf("2024-04-17"),"nabeul","j'aimerai avoir une don");
        Don dd = new Don(1100, 1, 2, Date.valueOf("2024-04-20"), "traité", "don pour le cours de français", "paiement par carte");
       try {
       /* DonateurService donateurService = new DonateurService();

        List<Donateur> donateurs = donateurService.getAll();

        for (Donateur donateur : donateurs) {
            System.out.println("Donateur :");
            System.out.println("ID : " + donateur.getId());
            System.out.println("Nom : " + donateur.getNom());
            System.out.println("Prénom : " + donateur.getPrenom());

            Don dons = donateur.getDon();
            System.out.println("dons :");
            System.out.println("Somme : " + dons.getSomme());
            System.out.println("ID du don : " + dons.getIdDon());

                System.out.println("------------------");

        } */

        /* System.out.println(" liste des donations");
          System.out.println(don.recuperer());*/
            don.ajouter(D);
            //don.supprimer(8);
           // don.modifier(D);

           //dons.ajouter(dd);
           //dons.supprimer(1);
            //dons.modifier(dd);

           // ds.ajouter(donataire);
            //ds.modifier(donataire);
           // ds.supprimer(1);
          /* System.out.println(" liste des demandes de donations:");
            System.out.println(ds.recuperer());*/
           /*System.out.println(" liste des dons");
            System.out.println(dons.recuperer());*/


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

        }
}
