package services;


import entities.partnerships;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class  ServicePartnerships implements IService<partnerships>{
        private Connection connection;
        public ServicePartnerships(){
            connection= MyDB.getInstance().getConnection();
        }

    @Override
    public void ajouter(partnerships partnerships) throws SQLException {
        String req ="insert into partnership (nom_p,domaine,choix)"+
                "values('"+ partnerships.getNom_p()+"','"+partnerships.getDomaine()+"','"+partnerships.getChoix()+"')" ;
        Statement ste= connection.createStatement();
        ste.executeUpdate(req);
    }

    @Override
        public void modifier(partnerships partnerships) throws SQLException {
            String req="UPDATE partnership SET nom_p= ?,domaine= ?,choix= ? WHERE idP= ?";
            PreparedStatement ps= connection.prepareStatement(req);
            ps.setString(1,partnerships.getNom_p());
            ps.setString(2, partnerships.getDomaine());
            ps.setInt(3,partnerships.getChoix());
            ps.setInt(4,partnerships.getIdP());
            ps.executeUpdate();
    }

    @Override
    public void supprimer(partnerships partnerships) throws SQLException {
            String req="DELETE FROM partnership where idP=? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,partnerships.getIdP());
            ps.executeUpdate();
    }




    @Override
     public List<partnerships> afficher() throws SQLException {
        String req = "SELECT * FROM partnership";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<partnerships> list = new ArrayList<>();

        int rowCount = 0; // Variable to track the number of rows processed

        while (res.next()) {
            partnerships f = new partnerships();
            f.setIdP(res.getInt("idP"));
            f.setNom_p(res.getString("nom_p"));
            f.setDomaine(res.getString("domaine"));
            f.setChoix(res.getInt("choix"));

            list.add(f);

            // Print the current row being processed
            System.out.println("Row " + (++rowCount) + ": " + f.toString() + "\n");
        }

        System.out.println("Total rows processed: " + rowCount); // Print total rows processed
        return list;
    }

}

