package tn.esprit.project.services;

import tn.esprit.project.models.Events;
import tn.esprit.project.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventService implements IService<Events> {

    private Connection connection;
    public EventService(){
         connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Events events) throws SQLException {
        String req ="INSERT INTO events (nom,date_event,nbr_place,description) values('"+events.getNom()+"','"+events.getDate_event()+"','"+events.getNbr_place()+"','"+events.getDescription()+"')";
        Statement st =connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Events events) throws SQLException {
        String req ="Update events Set nom=? ,date_event=?, nbr_place=?,description=? where id_event = ?";
        PreparedStatement ps =connection.prepareStatement(req);
        ps.setString(1,events.getNom());
        ps.setDate(2, events.getDate_event());
        ps.setInt(3,events.getNbr_place());
        ps.setString(4,events.getDescription());
        ps.setInt(5,events.getId_event());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(int id_event) throws SQLException {
        String req ="Delete from events where id_event = ?";
        PreparedStatement ps =connection.prepareStatement(req);
        ps.setInt(1,id_event);
        ps.executeUpdate();
    }

    @Override
    public List<Events> recuperer() throws SQLException {
        List<Events> events = new ArrayList<>();
        String req ="SELECT * from events";
        Statement st = connection.createStatement();
        ResultSet rs =st.executeQuery(req);
        while (rs.next()){
            Events ev = new Events();
            ev.setId_event(rs.getInt("id_event"));
            ev.setNom(rs.getString("nom"));
            ev.setDate_event(rs.getDate("date_event"));
            ev.setNbr_place(rs.getInt("nbr_place"));
            ev.setDescription(rs.getString("description"));

            events.add(ev);

        }


        return events;
    }
}
