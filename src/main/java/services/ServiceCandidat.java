package services;


import entities.candidat;
import utils.MyDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ServiceCandidat implements IService<candidat> {

    private Connection connection;

    public ServiceCandidat() {
        connection = MyDB.getInstance().getConnection();
    }
    @Override
    public void ajouter(candidat candidat) throws SQLException {
        String req = "insert into candidat (nom,prenom,lettre,idRecrutement)" +
                "values('" + candidat.getNom() + "','" + candidat.getPrenom() + "','" + candidat.getLettre() + "','" + candidat.getidRecrutement() + "')";
        Statement ste = connection.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(candidat candidat) throws SQLException {

    }

    @Override
    public void supprimer(candidat candidat) throws SQLException {

    }

    @Override
    public List<candidat> afficher() throws SQLException {
        return null;
    }
}
