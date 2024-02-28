package tn.esprit.gestiondonation.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import tn.esprit.gestiondonation.models.Request;
import tn.esprit.gestiondonation.utils.MyDatabase;


public class RequestService implements Iservice<Request> {

        private Connection connection;
        public RequestService(){
            connection = MyDatabase.getInstance().getConnection();
        }
        public void setConnection(Connection connection) {
            this.connection = connection;
        }
    @Override
    public void ajouter(Request request) throws SQLException {
        String req = "INSERT INTO requestdonation (formation_souhaitée, email, date_limite, lieu_de_résidence, Description) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, request.getFormation_souhaitée());
            stm.setString(2, request.getEmail());

            // Conversion de java.util.Date en java.sql.Date
            java.util.Date dateLimite = request.getDate_limite();
            java.sql.Date sqlDate = new java.sql.Date(dateLimite.getTime());

            stm.setDate(3, sqlDate);
            stm.setString(4, request.getLieu_de_résidence());
            stm.setString(5, request.getDescription());
            stm.executeUpdate();

            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                request.setIdDonataire(generatedId);
            }

            System.out.println("Demande ajoutée");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void modifier(Request request) throws SQLException {
        String req = "UPDATE requestdonation SET formation_souhaitée = ?, email = ?, date_limite = ?, lieu_de_résidence = ?, Description = ? WHERE idRequest = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, request.getFormation_souhaitée());
            ps.setString(2, request.getEmail());

            // Conversion de java.util.Date en java.sql.Date
            java.util.Date dateLimite = request.getDate_limite();
            java.sql.Date sqlDate = new java.sql.Date(dateLimite.getTime());

            ps.setDate(3, sqlDate);
            ps.setString(4, request.getLieu_de_résidence());
            ps.setString(5, request.getDescription());
            ps.setInt(6, request.getIdDonataire()); // Assuming the ID field is getIdRequest()
            ps.executeUpdate();
            System.out.println("Donataire modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

        @Override
        public void supprimer(int id) throws SQLException {
            String req = "DELETE FROM requestdonation WHERE idRequest= ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Demande supprimée");
        }

    @Override
    public List<Request> recuperer() throws SQLException {
        List<Request> donataires = new ArrayList<>();
        String req = "SELECT * FROM requestdonation";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                int idDonataire = rs.getInt("idRequest");
                String formation_souhaitée = rs.getString("formation_souhaitée");

                String email = rs.getString("email");
                java.sql.Date date_limite = rs.getDate("date_limite"); // Utilisation de getDate pour les colonnes de type Date
                String lieu_de_résidence = rs.getString("lieu_de_résidence");
                String description = rs.getString("Description");

                Request requestdonation = new Request();
                requestdonation.setIdDonataire(idDonataire);
                requestdonation.setFormation_souhaitée(formation_souhaitée);

                requestdonation.setEmail(email);
                requestdonation.setDate_limite(date_limite);

                requestdonation.setLieu_de_résidence(lieu_de_résidence);
                requestdonation.setDescription(description);

                donataires.add(requestdonation);
            }
        } catch (SQLException e) {
            System.err.println("Une erreur s'est produite lors de la récupération des donataires : " + e.getMessage());
        }
        return donataires;
    }
    }

