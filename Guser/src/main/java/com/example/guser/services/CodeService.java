package com.example.guser.services;

import com.example.guser.models.Code_promo;
import com.example.guser.models.Utilisateur;
import com.example.guser.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CodeService implements IService <Code_promo> {
    private Connection connection ;

    public CodeService() {
        connection = MyDataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Code_promo code) throws SQLException {
        String req = "INSERT INTO Code_promo (code,date_expiration,active,idUser) VALUES ('" + code.getCode() + "', '" + code.getDate_expiration() + "', '" + code.getActive() + "','" + code.getIdUser() + "' )";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Code_promo code) throws SQLException {
        String req = "UPDATE Code_promo SET code = ?, date_expiration = ?, active = ?, idUser = ? WHERE id_promo = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, code.getCode());
        ps.setDate(2, code.getDate_expiration());
        ps.setInt(3, code.getActive());
        ps.setInt(4, code.getIdUser());
        ps.setInt(5, code.getId_promo());

        ps.executeUpdate();
        System.out.println("success");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM Code_promo WHERE id_promo=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override

    public  List<Code_promo> recuperer() throws SQLException {
        List<Code_promo> codes = new ArrayList<>();

        String req = "SELECT " +
                "code_promo.id_promo, code_promo.code, code_promo.date_expiration, code_promo.active, " +
                "utilisateur.id, utilisateur.nom, utilisateur.prenom, utilisateur.date_nais, " +
                "utilisateur.num_tel, utilisateur.email, utilisateur.sexe " +
                "FROM code_promo " +
                "JOIN utilisateur ON code_promo.idUser = utilisateur.id;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Code_promo code = new Code_promo();
            code.setId_promo(rs.getInt("id_promo"));
            code.setCode(rs.getString("code"));
            code.setDate_expiration(rs.getDate("date_expiration"));
            code.setActive(rs.getInt("active"));
            code.setIdUser(rs.getInt("id"));

            // Set user information
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(rs.getInt("id"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setDate_nais(rs.getDate("date_nais"));
            utilisateur.setNum_tel(rs.getInt("num_tel"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setSexe(rs.getString("sexe"));

            // Set the user for the code_promo
           code.setUser(utilisateur);
          System.out.println(utilisateur.toString());

            codes.add(code);
        }
        return codes;
    }

}

