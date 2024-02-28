package esprit.tn.formation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
   private final String URL="jdbc:mysql://localhost:3306/pacelearning";
   private final String USR="root";
   private final String PWD="";
   private Connection connection;
   private  static MyDataBase instance;
   private MyDataBase(){
       try{

           connection = DriverManager.getConnection(URL,USR,PWD);
           System.out.println("connection established");
       }catch(SQLException e){
           System.err.println(e.getMessage());
       }
   }
    public static MyDataBase getInstance() {
       if(instance == null)
           instance = new MyDataBase();
           return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
