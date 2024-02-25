package tn.esprit.project.models;

import java.util.Date;

public class Planning {
    private int id_planning;
    private String titre;
    private String Lieu;
    private Date date;
    private int id_event;
    private Events Events;

    public Planning() {
    }

    public Planning(int id_planning, String titre, String lieu, Date date, int id_event) {
        this.id_planning = id_planning;
        this.titre = titre;
        Lieu = lieu;
        this.date = date;
        this.id_event = id_event;
    }

    public Planning(String titre, String lieu, Date date, int id_event) {
        this.titre = titre;
        Lieu = lieu;
        this.date = date;
        this.id_event = id_event;
    }

    public int getId_planning() {
        return id_planning;
    }

    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return Lieu;
    }

    public void setLieu(String lieu) {
        Lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    @Override
    public String toString() {
        return "Planning{" +
                "id_planning=" + id_planning +
                ", titre='" + titre + '\'' +
                ", Lieu='" + Lieu + '\'' +
                ", date=" + date +
                ", id_event=" + id_event +
                '}';
    }
    public void setEvent(Events Events) {
        this.Events = Events;
    }

    // Getter method for user
    public Events getEvent() {
        return Events;
    }

}


