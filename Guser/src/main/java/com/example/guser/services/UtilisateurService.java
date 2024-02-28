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

        String req = "INSERT INTO utilisateur (nom,prenom,date_nais,num_tel,email,sexe) VALUES ('" + utilisateur.getNom() + "', '" +utilisateur.getPrenom() + "', '" + utilisateur.getDate_nais() + "', " + utilisateur.getNum_tel() + ",'" + utilisateur.getEmail() + "','" + utilisateur.getSexe() + "' )";
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


}
