package services;

import entities.events;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService implements IService <events> {
    @Override
    public void supprimer(int id_event) throws SQLException {
        String req = "DELETE FROM events WHERE id_event=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, id_event);
        ps.executeUpdate();
    }

    @Override
    public List<events> recuperer() throws SQLException {
        List<events> eventsList =new ArrayList<>();
        String req ="Select * From evnets";
        Statement st =connection.createStatement();
        ResultSet rs =st.executeQuery(req);
        while (rs.next()) {
            events eventsListe = new events();
            //eventsListe.getId_event(rs.getInt("id"));
            //eventsListe.getNom(rs.getString("nom"));
            //eventsListe.getDate_event(rs.getDate("date"));
            //eventsListe.getNbr_place(rs.getInt("nbr_place"));
            //eventsListe.getDescription(rs.getString("description"));

            //events.add (eventsListe);//
        }
        return eventsList;
    }


    private Connection connection;

    public EventService(){
    connection= MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(events events) throws SQLException {
     String req
             ="insert into events (id_event,nom,date_event,nbr_place,description)"+
        "values('"+events.getId_event()+"','"+events.getNom()+"','"+events.getDate_event()+"','"+events.getNbr_place()+"',"+events.getDescription()+")";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(events events) throws SQLException {
        String req = "UPDATE events SET num_tel = ?nom = ?, prenom = ?, date_nais = ?, email = ? , sexe = ? WHERE id= ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(  1, events.getId_event());
        ps.setString(2, events.getNom());
        ps.setDate(3, events.getDate_event());
        ps.setInt(4, events.getNbr_place());
        ps.setString(5, events.getDescription());
        ps.executeUpdate();
    }
    }




