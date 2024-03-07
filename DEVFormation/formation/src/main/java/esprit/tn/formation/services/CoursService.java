package esprit.tn.formation.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import esprit.tn.formation.models.Cours;
import esprit.tn.formation.models.Formation;
import esprit.tn.formation.utils.MyDataBase;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CoursService implements Iservice <Cours> {
    private Connection connection;

    public CoursService() {
        connection = MyDataBase.getInstance().getConnection();
    }

    ;

    @Override
    public void add(Cours cours) {
        String req = "INSERT INTO `cours`(`nomCours`, `description`, `categorie`, `cours`, `idFormation`) VALUES('" + cours.getnomCours() + "','" + cours.getDescription() + "','" + cours.getCategorie() + "','" + cours.getcours() + "','" + cours.getIdFormation() + "')";
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
        String req = "UPDATE `cours` SET `nomCours`='" + cours.getnomCours() + "' ,`description`='" + cours.getDescription() + "',`categorie`='" + cours.getCategorie() + "',`cours`='" + cours.getcours() + "',`idFormation`='" + cours.getIdFormation() + "' WHERE `idCours` = '" + cours.getIdCours() + "' ";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            System.err.println("An error occurred while updating the course: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM `cours` WHERE `idCours` = " + id;
        try {
            PreparedStatement st = connection.prepareStatement(req);
            st.executeUpdate();
            System.out.println("Successfully deleted!");
        } catch (SQLException e) {
            System.err.println("An error occurred while deleting the Course: " + e.getMessage());
        }
    }

    /*
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
                f.setnomCours((res.getString("nomCours")));
                f.setDescription((res.getString("description")));
                f.setCategorie((res.getString("categorie")));
                f.setcours((res.getFloat("cours")));
                f.setIdFormation((res.getInt("idFormation")));
                L.add(f);
                //System.out.println(f);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while listing the course: " + e.getMessage());
        }

        return L;
    }*/
    @Override
    public List<Cours> getAll() {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8082/courses").asJson();
        System.out.println(jsonResponse.getBody().toString());
        if (jsonResponse.isSuccess()) {
            JsonNode jsonNode = jsonResponse.getBody();
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<Cours> cours = mapper.readValue(jsonNode.toString(), new TypeReference<List<Cours>>() {
                });
                System.out.println(cours);
                return cours;
            } catch (Exception e) {
                // Handle the parsing error
                System.err.println("Error parsing the courses: " + e.getMessage());
                return Collections.emptyList();
            }
        } else {
            // Handle the error scenario
            System.err.println("Failed to fetch courses: " + jsonResponse.getStatusText());
            return Collections.emptyList();
        }
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
                cours.setnomCours(res.getString("nomCours"));
                cours.setDescription(res.getString("description"));
                cours.setCategorie(res.getString("categorie"));
                cours.setcours(res.getString("cours"));
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
    }

    //SortByPrice
    public void sortByPrice(List<Cours> cours) {
        cours.sort(Comparator.comparing(Cours::getcours));

        // Print the sorted list of courses
        System.out.println("Sorted Courses by Price:");
        for (Cours Cours : cours) {
            System.out.println(Cours);
        }
    }

    //Sort By Categorie
    public void sortByCategorie(List<Cours> cours) {
        cours.sort(Comparator.comparing(Cours::getCategorie));

        // Print the sorted list of courses
        System.out.println("Sorted Courses by Categorie:");
        for (Cours Cours : cours) {
            System.out.println(Cours);
        }
    }

    public Cours searchidF(int idCours) {
        Cours cours = null;
        String req = "SELECT * FROM cours WHERE idCours = ?";
        try (PreparedStatement st = connection.prepareStatement(req)) {
            st.setInt(1, idCours);
            try (ResultSet res = st.executeQuery()) {
                if (res.next()) {
                    cours = new Cours();
                    cours.setIdCours(res.getInt("idCours"));
                    cours.setIdFormation(res.getInt("idFormation"));
                    cours.setnomCours(res.getString("nomCours"));
                    cours.setDescription(res.getString("description"));
                    cours.setCategorie(res.getString("categorie"));
                    cours.setcours(res.getString("cours"));
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while searching for the course: " + e.getMessage());
        }
        if (cours == null) {
            System.out.println("No course found for the search term: " + idCours);
        }
        return cours;
    }

    public List<Cours> getFormationCourses(int formationId) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8082/courses/" + formationId).asJson();
            if (jsonResponse.isSuccess()) {
                JsonNode jsonNode = jsonResponse.getBody();
                ObjectMapper mapper = new ObjectMapper();
                try {
                    List<Cours> formationCourses = mapper.readValue(jsonNode.toString(), new TypeReference<List<Cours>>() {
                    });
                    System.out.println(formationCourses);
                    return formationCourses;
                } catch (Exception e) {
                    // Handle the parsing error
                    System.err.println("Error parsing the courses: " + e.getMessage());
                    return Collections.emptyList();
                }
            } else {
                // Handle the error scenario
                System.err.println("Failed to fetch courses for formation ID " + formationId + ": " + jsonResponse.getStatusText());
                return Collections.emptyList();
            }
        } catch (UnirestException e) {
            // Handle Unirest exception
            System.err.println("Unirest exception occurred: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

