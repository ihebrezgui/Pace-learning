package tn.esprit.project.test;

import tn.esprit.project.models.Events;
import tn.esprit.project.models.Planning;
import tn.esprit.project.services.EventService;
import tn.esprit.project.services.PlanningService;
import tn.esprit.project.utils.MyDataBase;

import java.sql.SQLException;
import java.util.List;

public class main {
    public static void main(String[] args) throws SQLException {
        MyDataBase db1 = MyDataBase.getInstance();
        db1 = new MyDataBase();
        System.out.println(db1.hashCode());
        EventService ev = new EventService();
        PlanningService Pl = new PlanningService();

        try {
            System.out.println("Planning : ");
            List<Planning> planningList = Pl.recuperer();
            if (planningList.isEmpty()) {
                System.out.println("No planning found.");
            } else {
                for (Planning planning : planningList) {
                    System.out.println(planning.toString());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving planning: " + e.getMessage());
            e.printStackTrace();
        }

        MyDataBase db = MyDataBase.getInstance();
    }

}



//Pl.ajouter(new Planning("ok","online",new java.sql.Date(2020-11-11),9));
//ev.ajouter(new Events("ok",new java.sql.Date(2020-11-11),8,"ok ok jamil"));
//recuperer
//System.out.println("Events:");
//           System.out.println(ev.recuperer());


//supprimer
//ev.supprimer(1);
//System.out.println("Deleted");

