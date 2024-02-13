package tn.esprit.devformation.services;

import tn.esprit.devformation.models.Formation;
import tn.esprit.devformation.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FormationService implements Iservice<Formation> {
    private Connection connection;

    public FormationService() {
        connection = MyDataBase.getInstance().getConnection();
    }
   //ADD FORMATION
    @Override
    public void add(Formation formation) {
      String req = "INSERT INTO `formation`(`typeF`) VALUES ('"+formation.getTypeF()+"')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Succesfully added !");
        } catch (SQLException e) {
            System.err.println("An error occurred while adding the formation: " + e.getMessage());
        }
    }
    /*public void add(Formation formation) {
        String req = "INSERT INTO `formation`(`typeF`) VALUES ('"+formation.getTypeF()+"')";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.setString(1,formation.getTypeF());
            st.executeUpdate();
            System.out.println("Succesfully added !");
        } catch (SQLException e) {
            System.err.println("An error occurred while adding the formation: " + e.getMessage());
        }
    }*/

    @Override
    public void update(Formation formation) {
        String req = "UPDATE `formation` SET `typeF` = '" + formation.getTypeF() + "' WHERE `idFormation` = '" + formation.getIdFormation() + "'";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            //st.setString(2, formation.getTypeF());
            st.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            System.err.println("An error occurred while updating the formation: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM `formation` WHERE `idFormation` = "+ id;
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.executeUpdate();
            System.out.println("Successfully deleted!");
        } catch (SQLException e) {
            System.err.println("An error occurred while deleting the formation: " + e.getMessage());
        }
    }

    @Override
    public List<Formation> getAll() {
        List<Formation> L = new ArrayList<>();
        String req = "SELECT * FROM `formation`";
        try {
            Statement st = connection.createStatement();
            ResultSet res= st.executeQuery(req);
            while (res.next())
            {
                Formation f = new Formation();
                f.setIdFormation((res.getInt("idFormation")));
                f.setTypeF((res.getString("typeF")));
                L.add(f);
                System.out.println(f);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while listing the formation: " + e.getMessage());
        }

        return L;
    }
    public void sortByPrice(List<Formation> formations){}
    //SortByType
    public void sortByCategorie(List<Formation> formations) {
        formations.sort(Comparator.comparing(Formation::getTypeF));
        System.out.println("Sorted Formations:");
        for (Formation formation : formations) {
            System.out.println(formation);
        }
    }
}
