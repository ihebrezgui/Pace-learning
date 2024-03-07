package services;

import entities.recrutments;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ServiceRecrutements implements IService<recrutments> {
    private Connection connection;
    public ServiceRecrutements(){connection= MyDB.getInstance().getConnection();}


    @Override
    public void ajouter(recrutments recrutements) throws SQLException {
        String req ="insert into recrutments (poste,discription,salaire)"+
                "values('"+recrutements.getPoste()+"','"+recrutements.getDiscription()+"','"+ recrutements.getSalaire()+"')" ;
        Statement ste= connection.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(recrutments recrutments) throws SQLException {
        String req="UPDATE recrutments SET poste= ?,discription= ?,salaire= ? WHERE idR= ?";
        PreparedStatement ps= connection.prepareStatement(req);
        ps.setString(1,recrutments.getPoste());
        ps.setString(2, recrutments.getDiscription());
        ps.setInt(3,recrutments.getSalaire());
        ps.setInt(4,recrutments.getIdR());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(recrutments recrutments) throws SQLException {
        String req="DELETE FROM recrutments where idR=? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,recrutments.getIdR());
        ps.executeUpdate();
    }

    @Override
    public List<recrutments> afficher() throws SQLException {
        String req = "SELECT * FROM recrutments";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<recrutments> list  = new ArrayList<>();

        int rowCount = 0; // Variable to track the number of rows processed

        while (res.next()) {
            recrutments f = new recrutments();
            f.setIdR(res.getInt("idR"));
            f.setPoste(res.getString("poste"));
            f.setDiscription(res.getString("discription"));
            f.setSalaire(res.getInt("salaire"));

            list.add(f);

            // Print the current row being processed
            System.out.println("Row " + (++rowCount) + ": " + f.toString() + "\n");
        }

        System.out.println("Total rows processed: " + rowCount); // Print total rows processed
        return list;
    }
}
