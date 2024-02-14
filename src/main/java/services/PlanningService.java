package services;
import entities.events;
import entities.planning;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanningService implements IService <PlanningService> {
    private Connection connection;

    public PlanningService() {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(PlanningService planningService) throws SQLException {
        String req ="INSERT INTO events (id_event,titre,date,approved)"+
                "VALUS('"+planning.getId_event()+"','"+planning.getTitre()+"','"+planning.getDate()+"',"planning.getApproved()"+)";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }
    }

    @Override
    public void modifier(PlanningService planningService) throws SQLException {
        String req = "UPDATE planning SET titre = ?date = ?, approved = ? WHERE id= ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(  1, planning.getId_event());
        ps.setString(2, planning.getTitre());
        ps.setDate(3, planning.getDate_event());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id_event) throws SQLException {
        String req = "DELETE FROM planning WHERE id_event=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_event);
        ps.executeUpdate();

    }

    @Override
    public List<Planning> recuperer() throws SQLException {
        List<events> eventsList = new ArrayList<>();
        String req = "Select * From evnets";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
        /*    events eventsListe = new events();
            eventsListe.getId_event(rs.getInt("id"));
            eventsListe.getNom(rs.getString("nom"));
            eventsListe.getDate_event(rs.getDate("date"));
            eventsListe.getNbr_place(rs.getInt("nbr_place"));
            eventsListe.getDescription(rs.getString("description"));

            //events.add (eventsListe);//
        }

         */
        }
        return null;
    }

