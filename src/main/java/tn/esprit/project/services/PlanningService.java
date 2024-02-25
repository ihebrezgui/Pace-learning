package tn.esprit.project.services;

import javafx.event.Event;
import tn.esprit.project.models.Events;
import tn.esprit.project.services.EventService;
import tn.esprit.project.models.Planning;
import tn.esprit.project.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanningService implements IService<Planning> {

    private Connection connection;

    public PlanningService() {

        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Planning planning) throws SQLException {
        String req = "INSERT INTO planning (titre,lieu,date,id_event) VALUES ('" + planning.getTitre() + "','" + planning.getLieu() + "','" + planning.getDate() + "',"+ planning.getId_event()+")";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Planning planning) throws SQLException {
        String req = "Update planning Set id_event =?, id_planning = ?,titre=?,lieu=?,date=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, planning.getId_planning());
        ps.setString(2, planning.getTitre());
        ps.setString(3, planning.getLieu());
        ps.setDate(4, (java.sql.Date) new Date(planning.getDate().getTime()));
        ps.setInt(5, planning.getId_event());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id_planning) throws SQLException {
        String req = "Delete from planning where id_planning = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_planning);
        ps.executeUpdate();
    }

    @Override
    public List<Planning> recuperer() throws SQLException {
        List<Planning> planningList = new ArrayList<>();
        String req = "SELECT c.*, f.* FROM planning c JOIN events f ON c.id_event = f.id_event";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                // Create a new Planning object for each row in the result set
                Planning planning = new Planning();

                // Populate the Planning object with data from the result set
                planning.setId_planning(rs.getInt("id_planning"));
                planning.setTitre(rs.getString("titre"));
                planning.setLieu(rs.getString("lieu"));
                planning.setDate(rs.getDate("date"));
                planning.setId_event(rs.getInt("id_event"));
                System.out.println(planning.toString());

                // Create a new Events object and populate it with data from the result set
                Events event = new Events();
                event.setId_event(rs.getInt("id_event"));
                event.setNom(rs.getString("nom"));
                event.setDate_event(rs.getDate("date_event"));
                event.setNbr_place(rs.getInt("nbr_place"));
                event.setDescription(rs.getString("description"));
                System.out.println(event.toString());

                // Associate the Events object with the Planning object
                planning.setEvent(event);

                // Print the Events object for debugging purposes
                System.out.println(event.toString());

                // Add the Planning object to the list
                planningList.add(planning);
            }
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            // You might want to throw or handle this exception differently based on your application's logic
            throw ex;
        }
        // Return the list of Planning objects
        return planningList;
    }

    }
