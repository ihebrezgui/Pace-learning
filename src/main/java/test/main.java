package test;

import entities.enseignant;
import entities.partnerships;
import services.ServiceEnseignant;
import services.ServicePartnerships;

import java.sql.SQLException;

public class main {
    public static void main(String[] args){
        /*MyDB db1=MyDB.getInstance();
        db1 = new MyDB();

        System.out.println(db1.hashCode());*/

        enseignant p1 =new enseignant(21,"aziz@gmail.com","prog","aziz","chaabi","jawi bhi","english");
// ajouter les enseignants
        ServiceEnseignant services = new ServiceEnseignant();
        //ajouter
        /*
        try {
            services.ajouter(p1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        enseignant p2 =new enseignant(1,21,"zizou@gmail.com","prog","aziz","chaabi","jawi bhi","english");

        try {
            services.modifier(p2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            enseignant p3 =new enseignant(1,21,"zizou@gmail.com","prog","aziz","chaabi","jawi bhi","english");

            services.supprimer(p3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            System.out.println(services.afficher());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        partnerships pp=new partnerships("aziz","info",3);*/
        ServicePartnerships service = new ServicePartnerships();/*
        try {
            service.ajouter(pp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());}*/
        try {
            partnerships pp=new partnerships(1,"aziz","info",5);
            service.modifier(pp);
        } catch (SQLException e) {
            System.out.println(e.getMessage());}


    }

}
