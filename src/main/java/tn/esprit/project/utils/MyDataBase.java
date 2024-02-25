package tn.esprit.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyDataBase {

    private static MyDataBase instance ;
    private final String URL = "jdbc:mysql://localhost:3306/pi";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connection;

    public MyDataBase() {
      try{
          connection = DriverManager.getConnection(URL,USER,PASSWORD);
          System.out.println("Connection established");

      } catch (Exception e) {
          System.err.println(e.getMessage());
      }
    }

    public static MyDataBase getInstance(){
         if (instance == null)
             instance = new MyDataBase();
            return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
