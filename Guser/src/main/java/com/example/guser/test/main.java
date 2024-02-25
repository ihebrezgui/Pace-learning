package com.example.guser.test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.example.guser.models.Code_promo;
import com.example.guser.models.Utilisateur;
import com.example.guser.services.CodeService;
import com.example.guser.services.UtilisateurService;
import com.example.guser.utils.MyDataBase;

public class main {

        public static void main(String[] args) throws SQLException {

            MyDataBase db1 = MyDataBase.getInstance();
            db1 = new MyDataBase();

            System.out.println(db1.hashCode());

           UtilisateurService us = new UtilisateurService();
/*
try {
    // Adding a new user
    us.ajouter(new Utilisateur("Rihab", "Tlili", new java.sql.Date(2000 - 1900, 9, 10), 3, "rihab@esprit.tn", "femme"));

    // Modifying an existing user
    us.modifier(new Utilisateur(1,"Rihab", "Tlili", new java.sql.Date(2000 - 1900, 9, 10), 254874, "rihab@esprit.tn", "male"));

    // Deleting a user
    us.supprimer(1);
    System.out.println("deleted");

    // Retrieving and printing the list of users
    System.out.println("le liste des utilisateur");
    System.out.println(us.recuperer());
} catch (SQLException e) {
    System.err.println(e.getMessage());
}

*/

            CodeService cs = new CodeService();

            try {

                //  add a new promo code
                cs.ajouter(new Code_promo("52ac", new java.sql.Date(2025 - 10 - 10), 1, 4));

                 // modify the promo code
               // cs.modifier(new Code_promo(  11, "25487", new java.sql.Date(2025 - 10 - 10), 0, 2));

                //  delete a promo code
                //cs.supprimer (9);
                //System.out.println("deleted");

                //  retrieve and print all promo codes
                /*System.out.println("le code : ");
                System.out.println(cs.recuperer());
                */

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

            MyDataBase db = MyDataBase.getInstance();

        }

}









