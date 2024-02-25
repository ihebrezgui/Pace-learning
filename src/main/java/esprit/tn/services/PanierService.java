package esprit.tn.services;

import esprit.tn.interfaces.InterfaceService;
import esprit.tn.models.Panier;
import esprit.tn.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierService implements InterfaceService<Panier> {

    private final Connection cnx;

    public PanierService() {
        this.cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Panier panier) throws SQLException {
        String req = "INSERT INTO panier (quantite, nom, prix,prod_id) VALUES ( ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, panier.getQuantite());
        ps.setString(2, panier.getNom());
        ps.setFloat(3, panier.getPrix());
        ps.setString(4, panier.getProd_id());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void modifier(Panier panier) throws SQLException {
        String req = "UPDATE panier SET quantite=?, nom=?, prix=?, prod_id=? WHERE idp=?";

        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, (panier.getQuantite()));
        ps.setString(2, (panier.getNom()));
        ps.setFloat(3, (panier.getPrix()));
        ps.setString(4,( panier.getProd_id()));
        ps.setInt(5, (panier.getIdp()));
        ps.executeUpdate();
        ps.close();

    }

    @Override
    public void supprimer(int idp) throws SQLException {
        String req = "DELETE FROM panier WHERE idp=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idp);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public List<Panier> recuperer() throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM panier";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Panier panier = new Panier();
            panier.setIdp(rs.getInt("idp"));
            panier.setQuantite(rs.getInt("quantite"));
            panier.setNom(rs.getString("nom"));
            panier.setPrix(rs.getFloat("prix"));
            panier.setProd_id(rs.getString("prod_id"));
            paniers.add(panier);
        }
        rs.close();
        st.close();
        return paniers;
    }

    @Override
    public List<Panier> tri_par_nom_asc() throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM panier ORDER BY nom ASC";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Panier panier = new Panier();
            panier.setIdp(rs.getInt("idp"));
            panier.setQuantite(rs.getInt("quantite"));
            panier.setNom(rs.getString("nom"));
            panier.setPrix(rs.getFloat("prix"));
            panier.setProd_id(rs.getString("prod_id"));
            paniers.add(panier);
        }
        rs.close();
        st.close();
        return paniers;
    }

    @Override
    public List<Panier> chercher(String nom) throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM panier WHERE nom LIKE ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, "%" + nom + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Panier panier = new Panier();
            panier.setIdp(rs.getInt("idp"));
            panier.setQuantite(rs.getInt("quantite"));
            panier.setNom(rs.getString("nom"));
            panier.setPrix(rs.getFloat("prix"));
            panier.setProd_id(rs.getString("prod_id"));
            paniers.add(panier);
        }
        rs.close();
        ps.close();
        return paniers;
    }


    @Override
    public List<Panier> tri_par_nom_desc() throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM panier ORDER BY nom DESC";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Panier panier = new Panier();
            panier.setIdp(rs.getInt("idp"));
            panier.setQuantite(rs.getInt("quantite"));
            panier.setNom(rs.getString("nom"));
            panier.setPrix(rs.getInt("prix"));
            panier.setProd_id(rs.getString("prod_id"));
            paniers.add(panier);
        }
        rs.close();
        st.close();
        return paniers;
    }
}