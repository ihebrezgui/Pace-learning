package services;


import entities.partnerships;
import utils.MyDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class  ServicePartnerships implements IService<partnerships>{
        private Connection connection;
        public ServiceEnseignant(){
            connection= MyDB.getInstance().getConnection();
        }

    @Override
    public void ajouter(partnerships partnerships) throws SQLException {
        String req ="insert into partnership (nom_p,domaine,choix)"+
                "values('"+ partnerships.getNom_p()+"','"+partnerships.getDomaine()+"','"+partnerships.getChoix()+"')" ;
        Statement ste= connection.createStatement();
        ste.executeUpdate(req);
    }

    @Override
    public void modifier(partnerships partnerships) throws SQLException {

    }

    @Override
    public void supprimer(partnerships partnerships) throws SQLException {

    }
}

