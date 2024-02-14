package entities;

import java.util.Date;

public class planning {
    private static int id_event;
    private String titre;
    private Date date;
    private static Boolean approved;

    public planning(int id_event, String titre, Date date, Boolean approved) {
        this.id_event = id_event;
        this.titre = titre;
        this.date = date;
        this.approved = approved;
    }

    public planning(String titre, Date date, Boolean approved) {
        this.titre = titre;
        this.date = date;
        this.approved = approved;
    }

    public static int getId_event() {
        return id_event;
    }

    public static java.sql.Date getDate_event() {
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public static String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public static Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "planning{" +
                "id_event=" + id_event +
                ", titre='" + titre + '\'' +
                ", date=" + date +
                ", approved=" + approved +
                '}';
    }

}
