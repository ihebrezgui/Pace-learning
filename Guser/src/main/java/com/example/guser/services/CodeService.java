package com.example.guser.services;

import com.example.guser.models.Code_promo;
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
        String req = "INSERT INTO code (code,date_expiration,active) VALUES ('"+ code.getCode() + "'," + code.getDate_expiration() + " )";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Code_promo code) throws SQLException {
        String req = "UPDATE code SET code = ? date_expiration = ?, active = ?, WHERE id= ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(2, code.getCode());
        ps.setDate(4, code.getDate_expiration());
        ps.setBoolean(5, code.isActive());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM code WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Code_promo> recuperer() throws SQLException {
        List<Code_promo> codes = new ArrayList<>();
        String req = "SELECT * FROM code";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Code_promo code = new Code_promo();
            code.setId_promo(rs.getInt("id"));
            code.setCode(rs.getString("code"));
            code.setDate_expiration((rs.getDate("Date_expiration")));
            code.setActive(rs.getBoolean("active"));


            codes.add(code);



        }
        return codes;
    }

}

