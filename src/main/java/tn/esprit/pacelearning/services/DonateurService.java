package tn.esprit.pacelearning.services;

import tn.esprit.pacelearning.models.Donateur;
import tn.esprit.pacelearning.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonateurService implements Iservices <Donateur> {

    private Connection connection;
    public DonateurService(){
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Donateur donateur) throws SQLException {
        String req="INSERT INTO donateur VALUES(?,?,?)";
        try {
            PreparedStatement stm=connection.prepareStatement(req);
            stm.setInt(1,donateur.getId());
            stm.setString(2,donateur.getNom());
            stm.setString(3,donateur.getPrenom());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void modifier(Donateur donateur) throws SQLException {

        String req ="UPDATE Donateur SET nom = ?, Prenom = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1,donateur.getNom());
        ps.setString(2, donateur.getPrenom());
        ps.setInt(3, donateur.getId());
        ps.executeUpdate();

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req ="DELETE FROM Donateur WHERE id =?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();

    }

    @Override
    public List<Donateur> recuperer() throws SQLException {
        List<Donateur> donateurs = new ArrayList<>();
        String req = "SELECT * FROM Donateur ";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Donateur donateur = new Donateur();
            donateur.setId(rs.getInt("id"));
            donateur.setNom(rs.getString("nom"));
            donateur.setPrenom(rs.getString("prenom"));
            donateurs.add(donateur);



        }
        return donateurs;
    }}