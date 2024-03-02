package com.example.guser.services;

import com.example.guser.models.Utilisateur;
import com.example.guser.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurService implements IService <Utilisateur> {
    private Connection connection ;
    public UtilisateurService() {
        connection = MyDataBase.getInstance().getConnection();

    }
    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {

        String req = "INSERT INTO utilisateur (nom,prenom,date_nais,num_tel,email,sexe,role,password) VALUES ('" + utilisateur.getNom() + "', '" +utilisateur.getPrenom() + "', '" + utilisateur.getDate_nais() + "', '" + utilisateur.getNum_tel() + "','" + utilisateur.getEmail() + "','" + utilisateur.getSexe() + "' ,'" + utilisateur.getRole() + "'," + utilisateur.getPassword() + ")";
        Statement st = connection.createStatement();
        st.executeUpdate(req);

    }



    @Override
    public void modifier(Utilisateur utilisateur) throws SQLException {
        String req = "UPDATE utilisateur SET nom = ?, prenom = ?, date_nais = ?, num_tel = ?, email = ? , sexe = ? WHERE id= ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(  1, utilisateur.getNom());
        ps.setString(2, utilisateur.getPrenom());
        ps.setDate(3, utilisateur.getDate_nais());
        ps.setInt(4, utilisateur.getNum_tel());
        ps.setString(5, utilisateur.getEmail());
        ps.setString(6, utilisateur.getSexe());
        ps.setInt(  7, utilisateur.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM utilisateur WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Utilisateur> recuperer() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM utilisateur";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom((rs.getString("prenom")));
            utilisateur.setDate_nais(rs.getDate("date_nais"));
            utilisateur.setNum_tel(rs.getInt("num_tel"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setSexe(rs.getString("sexe"));

            utilisateurs.add(utilisateur);



        }
        return utilisateurs;
    }

    private Utilisateur extractUtilisateurFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        Date date_nais = resultSet.getDate("date_nais");
        int num_tel = resultSet.getInt("num_tel");
        String email = resultSet.getString("email");
        String sexe = resultSet.getString("sexe");
        String password = resultSet.getString("password");
        Utilisateur.Role role = Utilisateur.Role.valueOf(resultSet.getString("role"));

        return new Utilisateur(id, nom, prenom, date_nais, num_tel, email, sexe, role,password);
    }
    public Utilisateur authenticateUser(String email, String password) {
        String query = "SELECT * FROM utilisateur WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // User found, create and return the Utilisateur object
                return extractUtilisateurFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        }

        return null; // User not found
    }
}
