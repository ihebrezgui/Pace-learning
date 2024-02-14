package com.example.guser.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDataBase {
   private final String URL="jdbc:mysql://localhost:3306/projet";
   private final String USR="root";
   private final String PWD="";
   private Connection connection;

    private static MyDataBase instance;

   public MyDataBase(){
       try{
           connection = DriverManager.getConnection(URL,USR,PWD);
           System.out.println("connection established");
       }catch(SQLException e){
           System.err.println(e.getMessage());
       }
   }
   //cree une methode static
    public static MyDataBase getInstance() {
       if(instance == null)
           instance = new MyDataBase();
       return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
