package esprit.tn.formation.services;

import esprit.tn.formation.models.Formation;
import esprit.tn.formation.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kong.unirest.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class FormationService implements Iservice<Formation> {

    private final Connection connection;

    public FormationService() {
        connection = MyDataBase.getInstance().getConnection();
    }

    // ADD FORMATION
    @Override
    public void add(Formation formation) {
        String req = "INSERT INTO `formation`(`typeF`, `img`,`prix`,`duree`,`status`) VALUES ('" + formation.getTypeF() + "', '" + formation.getImg() + "', '" + formation.getPrix() + "', '" + formation.getDuree() + "', '" + formation.getStatus() +"')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Successfully added !");
        } catch (SQLException e) {
            System.err.println("An error occurred while adding the formation: " + e.getMessage());
        }
    }

    @Override
    public void update(Formation formation) {
        String req = "UPDATE `formation` SET `typeF` = '" + formation.getTypeF() + "', `img` = '" + formation.getImg() + "', `prix` = '" + formation.getPrix() + "', `duree` = '" + formation.getDuree() + "', `status` = '" + formation.getStatus() + "' WHERE `idFormation` = '" + formation.getIdFormation() + "'";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Successfully updated!");
        } catch (SQLException e) {
            System.err.println("An error occurred while updating the formation: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM `formation` WHERE `idFormation` = " + id;
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Successfully deleted!");
        } catch (SQLException e) {
            System.err.println("An error occurred while deleting the formation: " + e.getMessage());
        }
    }

    @Override
    public List<Formation> getAll() {
        HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8082/formations").asJson();

        if (jsonResponse.isSuccess()) {
            JsonNode jsonNode = jsonResponse.getBody();
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<Formation> formations = mapper.readValue(jsonNode.toString(), new TypeReference<List<Formation>>(){});
                return formations;
            } catch (Exception e) {
                // Handle the parsing error
                System.err.println("Error parsing the formations: " + e.getMessage());
                return Collections.emptyList();
            }
        } else {
            // Handle the error scenario
            System.err.println("Failed to fetch formations: " + jsonResponse.getStatusText());
            return Collections.emptyList();
        }
    }


    // SortByType
    public void sortByCategorie(List<Formation> formations) {
        formations.sort(Comparator.comparing(Formation::getTypeF));
        System.out.println("Sorted Formations:");
        for (Formation formation : formations) {
            System.out.println(formation);
        }
    }
    // SortByType
    public void sortByPrice(List<Formation> formations) {
        formations.sort(Comparator.comparing(Formation::getPrix));
        System.out.println("Sorted Formations:");
        for (Formation formation : formations) {
            System.out.println(formation);
        }
    }

    // search per type
    public ArrayList<Formation> search(String searchTerm) {
        ArrayList<Formation> searchResults = new ArrayList<>();
        String req = "SELECT * FROM `formation` WHERE `typeF` = '" + searchTerm + "' OR `prix` LIKE '%" + searchTerm + "%' OR `duree` LIKE '%" + searchTerm + "%'";try {
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                Formation formation = new Formation();
                formation.setIdFormation(res.getInt("idFormation"));
                formation.setTypeF(res.getString("typeF"));
                formation.setImg(res.getString("img"));
                formation.setPrix(res.getFloat("prix"));
                formation.setDuree(res.getString("duree"));
                formation.setStatus(res.getString("status"));
                searchResults.add(formation);
                System.out.println(formation);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while searching for the formation: " + e.getMessage());
        }
        if (searchResults.isEmpty()) {
            System.out.println("No formation found for the search term: " + searchTerm);
        }
        return searchResults;
    }
    //get by id
    public Formation searchidF(int idFormation) {
        Formation formation = null;
        String req = "SELECT * FROM formation WHERE idFormation = " + idFormation;
        try {
            Statement st = connection.createStatement();
            ResultSet res = st.executeQuery(req);
            if (res.next()) {
                formation = new Formation();
                formation.setIdFormation(res.getInt("idFormation"));
                formation.setTypeF(res.getString("typeF"));
                formation.setPrix(res.getFloat("prix"));
                formation.setDuree(res.getString("duree"));
                formation.setImg(res.getString("img"));
                formation.setStatus(res.getString("status"));
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while searching for the formation: " + e.getMessage());
        }
        if (formation == null) {
            System.out.println("No formation found for the search term: " + idFormation);
        }
        return formation;
    }


}
