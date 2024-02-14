package tn.esprit.pacelearning.services;

import tn.esprit.pacelearning.models.Don;
import tn.esprit.pacelearning.models.Donateur;
import tn.esprit.pacelearning.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonService implements Iservices<Don>{
    private Connection connection;
    public DonService(){
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Don  don) throws SQLException {
        String req = "INSERT INTO Don(somme) VALUES('"+ don.getSomme()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);


    }

    @Override
    public void modifier(Don don) throws SQLException {
        String req ="UPDATE Don SET somme = ?, idDonateur = ? WHERE idDon = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, don.getSomme());
        ps.setInt(2, don.getIdDonateur());
        ps.setInt(3, don.getIdDon());
        ps.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req ="DELETE FROM Don WHERE idDon =?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();

    }

    @Override
    public List recuperer() throws SQLException {
        List<Don> dons = new ArrayList<>();
        String req = "SELECT * FROM Donateur ";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Don don = new Don();
            don.setIdDon(rs.getInt("idDon"));
            don.setSomme(rs.getInt("somme"));
            don.setIdDonateur(rs.getInt("idDoanteur"));
            dons.add(don);

        }
        return dons;
    }}
