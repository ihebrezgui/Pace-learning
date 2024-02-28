package tn.esprit.gestiondonation.service;

import tn.esprit.gestiondonation.models.Don;
import tn.esprit.gestiondonation.models.Give;
import tn.esprit.gestiondonation.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiveService implements Iservice<Give> {
    private Connection connection;
    public GiveService(){
        connection = MyDatabase.getInstance().getConnection();
    }

    public List<Give> getAll() {
        List<Give> givedonations = new ArrayList<>();
        String req = "SELECT d.idDonateur, d.statut_donateur, d.montant, d.date_de_naissance, d.email, d.numéro_de_téléphone, d.profession, d.méthode_paiement, do.somme, do.idDon " +
                "FROM givedonation d " +
                "JOIN dons do ON d.idDonateur = do.idDonateur";
        try (PreparedStatement st = connection.prepareStatement(req);
             ResultSet res = st.executeQuery()) {
            while (res.next()) {
                int idDonateur = res.getInt("idDonateur");
                String statut = res.getString("statut");
                int montant = res.getInt("montant");
                Date dateNaissance = res.getDate("date_de_naissance");
                String email = res.getString("email");
                int numTlf = res.getInt("numéro_de_téléphone");
                String profession = res.getString("profession");
                String méthode_paiement = res.getString("méthode_paiement");
                int somme = res.getInt("somme");
                int idDon = res.getInt("idDon");

                Give givedonation = new Give();
                givedonation.setId(idDonateur);
                givedonation.setStatut(statut);
                givedonation.setMontant(montant);
                givedonation.setDate_de_naissance(dateNaissance);
                givedonation.setEmail(email);
                givedonation.setNum_tlf(numTlf);
                givedonation.setProfession(profession);
                givedonation.setMéthode_paiement(méthode_paiement);

                Don don = new Don();
                don.setSomme(somme);
                don.setIdDon(idDon);

                //donateur.setDon(don); // Set the donation for the donor

                givedonations.add(givedonation);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while listing donors with their donations: " + e.getMessage());
        }

        return givedonations;
    }



    /*public void ajouter(Give givedonation) throws SQLException {
        String req = "INSERT INTO Donateur (nom, prenom, date_de_naissance, email, numéro_de_téléphone, profession, genre) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (PreparedStatement stm = connection.prepareStatement(req)) {
            stm.setInt(1, givedonation.getId());
            stm.setString(2, givedonation.getNom());
            stm.setString(3, givedonation.getPrenom());
            stm.setDate(4, givedonation.getDate_de_naissance());
            stm.setString(5, givedonation.getEmail());
            stm.setInt(6, givedonation.getNum_tlf());
            Integer numTlf = givedonation.getNum_tlf();
            if (numTlf != null) {
                stm.setInt(6, numTlf.intValue());
            } else {
                stm.setNull(7, Types.INTEGER);
            }
            stm.setString(7, givedonation.getGenre());
            stm.executeUpdate();
            System.out.println("Donateur ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
    @Override
    public void ajouter(Give givedonation) throws SQLException {
        String req = "INSERT INTO givedonation (statut_donateur, montant, date_de_naissance, email, numéro_de_téléphone, profession, méthode_paiement) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = connection.prepareStatement(req)) {
            String statut = givedonation.getStatut();
            if (statut == null) {
                statut = "N/A";
            }

            stm.setString(1, statut);
            stm.setInt(2, givedonation.getMontant());
            stm.setDate(3, givedonation.getDate_de_naissance());
            stm.setString(4, givedonation.getEmail());
            stm.setInt(5, givedonation.getNum_tlf());
            stm.setString(6, givedonation.getProfession());
            stm.setString(7, givedonation.getMéthode_paiement());

            stm.executeUpdate();
            System.out.println("Donation attribuée");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   @Override
   public void modifier(Give givedonation) throws SQLException {
       String req = "UPDATE givedonation SET statut_donateur = '" + givedonation.getStatut() + "', montant = '" + givedonation.getMontant() +
               "', date_de_naissance = '" + givedonation.getDate_de_naissance() + "', email = '" + givedonation.getEmail() +
               "', numéro_de_téléphone = " + givedonation.getNum_tlf() + ", profession = '" + givedonation.getProfession() +
               "', méthode_paiement = '" + givedonation.getMéthode_paiement() + "' WHERE idDonateur = " + givedonation.getId();

       Statement stm = connection.createStatement();
       int rowCount = stm.executeUpdate(req);

       if (rowCount > 0) {
           System.out.println("Donation modifiée");
       } else {
           System.out.println("Aucune donation n'a été modifiée");
       }
   }

    @Override
    public void supprimer(int id) throws SQLException {
        String req ="DELETE FROM givedonation WHERE idDonateur =?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();
        System.out.println("donation supprimée");

    }

    @Override
    public List<Give> recuperer() throws SQLException {
        List<Give> donateurs = new ArrayList<>();
        String req = "SELECT * FROM givedonation";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int idDonateur = rs.getInt("idDonateur");
                String statut_donateur = rs.getString("statut_donateur");
                int montant = rs.getInt("montant");
                Date dateNaissance = rs.getDate("date_de_naissance");
                String email = rs.getString("email");
                int numTlf = rs.getInt("numéro_de_téléphone");
                String profession = rs.getString("profession");
                String méthode_paiement = rs.getString("méthode_paiement");

                Give donateur = new Give();
                donateur.setId(idDonateur);
                donateur.setStatut(statut_donateur);
                donateur.setMontant(montant);
                donateur.setDate_de_naissance(dateNaissance);
                donateur.setEmail(email);
                donateur.setNum_tlf(numTlf);
                donateur.setProfession(profession);
                donateur.setMéthode_paiement(méthode_paiement);

                donateurs.add(donateur);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while retrieving donors: " + e.getMessage());
        }
        return donateurs;
    }
}
