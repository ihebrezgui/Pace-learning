package tn.esprit.gestiondonation.models;

import java.util.Date;

public class Request {
        private int idDonataire;
        private String formation_souhaitée;

        private String email;
        private Date date_limite;
        private String lieu_de_résidence;
        private String Description;

        public int getIdDonataire() {
            return idDonataire;
        }

        public String getFormation_souhaitée() {
            return formation_souhaitée;
        }



        public String getEmail() {
            return email;
        }

        public Date getDate_limite() {
            return date_limite;
        }

        public String getLieu_de_résidence() {
            return lieu_de_résidence;
        }

        public String getDescription() {
            return Description;
        }

        public void setIdDonataire(int idDonataire) {
            this.idDonataire = idDonataire;
        }

        public void setFormation_souhaitée(String formation_souhaitée) {
            this.formation_souhaitée = formation_souhaitée;
        }




        public void setEmail(String email) {
            this.email = email;
        }

        public void setDate_limite(Date date_limite) {
            this.date_limite = date_limite;
        }

        public void setLieu_de_résidence(String lieu_de_résidence) {
            this.lieu_de_résidence = lieu_de_résidence;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public Request(int idDonataire, String formation_souhaitée, String email, Date date_limite,
                         String lieu_de_résidence, String description) {
            this.idDonataire = idDonataire;
            this.formation_souhaitée = formation_souhaitée;
            this.email = email;
            this.date_limite = date_limite;
            this.lieu_de_résidence = lieu_de_résidence;
            Description = description;
        }
        public Request(String formation_souhaitée, String email, Date date_limite,
                         String lieu_de_résidence, String description) {

            this.formation_souhaitée = formation_souhaitée;

            this.email = email;
            this.date_limite = date_limite;
            this.lieu_de_résidence = lieu_de_résidence;
            Description = description;
        }

        public Request() {
        }

        @Override
        public String toString() {
            return " " +
                    " " + idDonataire +
                    " " + formation_souhaitée +

                    " " + email +
                    " " + date_limite +
                    " " + lieu_de_résidence +
                    " " + Description
                   ;
        }
    }


