package esprit.tn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {
    //db
    final static String URL = "jdbc:mysql://127.0.0.1:3306/pidev";
    final static String USERNAME = "root";
    final static String PASSWORD = "";
    //var
    private Connection cnx ;

    static MaConnexion instance=null;

    //constructeur
        private MaConnexion(){
            try {
                cnx = java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connexion établie");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            }
            public static MaConnexion getInstance(){
                if (instance == null){
                    instance = new MaConnexion();
                }
                return instance;
            }
            public Connection getCnx(){
                return cnx;
            }
        }

