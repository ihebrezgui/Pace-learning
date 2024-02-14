package entities;

import java.util.Date;

public class events {
    private int id_event, nbr_place;
    private String nom,description;
    private Date date_event;

    public events() {
        this.id_event = id_event;
        this.nbr_place = nbr_place;
        this.nom = nom;
        this.description = description;
        this.date_event = date_event;
    }

    public events(int nbr_place, String nom, String description, Date date_event) {
        this.nbr_place = nbr_place;
        this.nom = nom;
        this.description = description;
        this.date_event = date_event;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDate_event() {
        return (java.sql.Date) date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    @Override
    public String toString() {
        return "events{" +
                "id_event=" + id_event +
                ", nbr_place=" + nbr_place +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", date_event=" + date_event +
                '}';
    }
}
