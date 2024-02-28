package tn.esprit.gestiondonation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/pacelearning";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static MyDatabase instance;

    public MyDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("connection established");
            //String sql = "SELECT dons.*, donateur.* FROM dons " +
            //"INNER JOIN donateur ON dons.idDonateur = donateur.idDonateur";
            Statement st = connection.createStatement();

            // Exécuter la requête SQL
            // ResultSet resultSet = st.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static MyDatabase getInstance() {
        if (instance == null)
            instance = new MyDatabase();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

