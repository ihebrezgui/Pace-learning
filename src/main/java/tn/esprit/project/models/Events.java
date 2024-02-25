package tn.esprit.project.models;

import java.util.Date;

public class Events {

       private int id_event;
       private String nom;
       private Date date_event;
       private int nbr_place;
       private String description;




        public Events(int id_event, String nom, Date date_event, int nbr_place, String description) {
            this.id_event = id_event;
            this.nom = nom;
            this.date_event = date_event;
            this.nbr_place = nbr_place;
            this.description = description;
        }

        public Events(String nom, Date date_event, int nbr_place, String description) {
            this.nom = nom;
            this.date_event = date_event;
            this.nbr_place = nbr_place;
            this.description = description;
        }

    public Events() {

    }

    public int getId_event() {
            return id_event;
        }

        public void setId_event(int id_event) {
            this.id_event = id_event;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public java.sql.Date getDate_event() {
            return (java.sql.Date) date_event;
        }

        public void setDate_event(Date date_event) {
            this.date_event = date_event;
        }

        public int getNbr_place() {
            return nbr_place;
        }

        public void setNbr_place(int nbr_place) {
            this.nbr_place = nbr_place;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "events{" +
                    "id_event=" + id_event +
                    ", nom='" + nom + '\'' +
                    ", date_event=" + date_event +
                    ", nbr_place=" + nbr_place +
                    ", description='" + description + '\'' +
                    '}';
        }
    }



