package tn.esprit.devformation.services;
import tn.esprit.devformation.models.Cours;

import tn.esprit.devformation.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoursService implements  Iservice <Cours>{
    private Connection connection;
    public CoursService(){connection = MyDataBase.getInstance().getConnection();};

    @Override
    public void add(Cours cours) {
        String req = "INSERT INTO `cours`(`nomCours`, `description`, `categorie`, `prix`, `idFormation`) VALUES('"+cours.getNomC()+"','"+cours.getDescription()+"','"+cours.getCategorie()+"','"+cours.getPrix()+"','"+cours.getIdFormation()+"')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Succesfully added !");
        } catch (SQLException e) {
            System.err.println("An error occurred while adding the course: " + e.getMessage());
        }

    }

    @Override
    public void update(Cours cours) {
        String req = "UPDATE `cours` SET `nomCours`='" + cours.getNomC() + "' ,`description`='" + cours.getDescription() + "',`categorie`='"+ cours.getCategorie() + "',`prix`='" + cours.getPrix() +"',`idFormation`='" + cours.getIdFormation() + "' WHERE `idCours` = '" + cours.getIdCours() + "' ";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            /*st.setString(2, cours.getNomC());
            st.setString(3, cours.getDescription());
            st.setString(4, cours.getCategorie());
            st.setFloat(5, cours.getPrix());
            st.setInt(6, cours.getIdFormation());
            */
            st.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            System.err.println("An error occurred while updating the course: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM `cours` WHERE `idCours` = "+ id;
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.executeUpdate();
            System.out.println("Successfully deleted!");
        } catch (SQLException e) {
            System.err.println("An error occurred while deleting the Course: " + e.getMessage());
        }
    }


    @Override
    public List<Cours> getAll() {
        List<Cours> L = new ArrayList<>();
        String req = "SELECT c.*, f.* FROM cours c JOIN formation f ON c.idFormation = f.idFormation";
        try {
            Statement st = connection.createStatement();
            ResultSet res= st.executeQuery(req);
            while (res.next())
            {
                Cours f = new Cours();
                f.setIdCours((res.getInt("idCours")));
                f.setNomC((res.getString("nomCours")));
                f.setDescription((res.getString("description")));
                f.setCategorie((res.getString("categorie")));
                f.setPrix((res.getFloat("prix")));
                f.setIdFormation((res.getInt("idFormation")));
                L.add(f);
                System.out.println(f);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while listing the course: " + e.getMessage());
        }

        return L;
    }

    public ArrayList<Cours> search(String searchTerm) {
        ArrayList<Cours> searchResults = new ArrayList<>();
        String query = "SELECT c.*, f.* FROM cours c JOIN formation f ON c.idFormation = f.idFormation WHERE c.nomCours = '" + searchTerm + "' OR c.categorie = '" + searchTerm + "' OR c.description LIKE '%" + searchTerm + "%'";

        try {
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                Cours cours = new Cours();
                cours.setIdCours(res.getInt("idCours"));
                cours.setNomC(res.getString("nomCours"));
                cours.setDescription(res.getString("description"));
                cours.setCategorie(res.getString("categorie"));
                cours.setPrix(res.getFloat("prix"));
                cours.setIdFormation(res.getInt("idFormation"));
                searchResults.add(cours);
                System.out.println(cours);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while searching for the course: " + e.getMessage());
        }
        if (searchResults.isEmpty()) {
            System.out.println("No courses found for the search term: " + searchTerm);
        }
        return searchResults;
    } void setConnection(Connection connection) {
        this.connection = connection;
    }
}
