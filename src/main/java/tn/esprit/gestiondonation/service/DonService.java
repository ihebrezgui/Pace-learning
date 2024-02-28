package tn.esprit.gestiondonation.service;

import tn.esprit.gestiondonation.models.Don;
import tn.esprit.gestiondonation.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonService implements Iservice<Don> {
    private Connection connection;

    public DonService() {
        connection = MyDatabase.getInstance().getConnection();
    }



    @Override
    public void ajouter(Don don) throws SQLException {
        String req = "INSERT INTO dons(idDon, somme, idDonateur, date_don, statut_don, commentaires, méthode_paiement) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(req);
        st.setInt(1, don.getIdDon());
        st.setInt(2, don.getSomme());
        st.setInt(3, don.getIdDonateur());
        st.setDate(4, new Date(don.getDate_don().getTime()));
        st.setString(5, don.getStatut_don());
        st.setString(6, don.getCommentaires());
        st.setString(7, don.getMéthode_paiement());
        st.executeUpdate();
        System.out.println("Don ajouté");}


    @Override
    public void modifier(Don don) throws SQLException {
        String req = "UPDATE dons SET idDon = ?, somme = ?, date_don = ?, statut_don = ?, commentaires = ?, méthode_paiement = ? WHERE idDonateur = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, don.getIdDon());
        ps.setInt(2, don.getSomme());
        ps.setDate(3, new java.sql.Date(don.getDate_don().getTime()));
        ps.setString(4, don.getStatut_don());
        ps.setString(5, don.getCommentaires());
        ps.setString(6, don.getMéthode_paiement());
        ps.setInt(7, don.getIdDonateur());
        ps.executeUpdate();
        System.out.println("Don modifié");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req ="DELETE FROM dons WHERE idDon =?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();
        System.out.println("don supprimé");

    }

    @Override
    public List<Don> recuperer() throws SQLException {
        List<Don> dons = new ArrayList<>();
        String req = "SELECT * FROM dons";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int idDon = rs.getInt("idDon");
            int somme = rs.getInt("somme");
            int idDonateur = rs.getInt("idDonateur");
            Date date = rs.getDate("date_don");
            String etat = rs.getString("statut_don");
            String description = rs.getString("commentaires");
            String modePaiement = rs.getString("méthode_paiement");

            Don don = new Don(idDon, somme, idDonateur, date, etat, description, modePaiement);
            dons.add(don);
        }
        System.out.println("c bon");
        return dons;
    }
}
