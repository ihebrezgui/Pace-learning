package services;

import entities.enseignant;
import utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceEnseignant implements IService<enseignant>{
        private Connection connection;
        public ServiceEnseignant(){
            connection= MyDB.getInstance().getConnection();
        }
        @Override
        public void ajouter(enseignant enseignant) throws SQLException {
            String req ="insert into enseignent (nomE,prenomE,email,matier,comptence,ageE,langue)"+
                    "values('"+enseignant.getNom()+"','"+enseignant.getPrenom()+"','"+enseignant.getEmail()+"','"+enseignant.getMatier()+"','"+enseignant.getComptences()+"','"+enseignant.getAge()+"','"+enseignant.getLangue()+"')" ;
            Statement ste= connection.createStatement();
            ste.executeUpdate(req);

        }

        @Override
        public void modifier(enseignant enseignant) throws SQLException {
            String req="UPDATE enseignent SET nomE= ?,prenomE= ?,email= ?,matier= ?,comptence= ?,ageE= ?,langue=? WHERE idE= ?";
            PreparedStatement ps= connection.prepareStatement(req);
            ps.setString(1,enseignant.getNom());
            ps.setString(2, enseignant.getPrenom());
            ps.setString(3,enseignant.getEmail());
            ps.setString(4,enseignant.getMatier());
            ps.setString(5, enseignant.getComptences());
            ps.setInt(6,enseignant.getAge());
            ps.setString(7,enseignant.getLangue());
            ps.setInt(8,enseignant.getIdE());
            ps.executeUpdate();
        }

        @Override
        public void supprimer(enseignant enseignant) throws SQLException {
            String req="DELETE FROM enseignent where idE=? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,enseignant.getIdE());
            ps.executeUpdate();
        }

//        @Override
//        public List<T> afficher() {
//            return null;
//        }
    }

