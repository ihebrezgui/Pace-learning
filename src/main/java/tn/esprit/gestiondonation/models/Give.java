package tn.esprit.gestiondonation.models;

import java.sql.Date;

public class Give {

        private  int  id ;
        private  String statut;
        private  int montant;
        private Date date_de_naissance;
        private String email;
        private Integer num_tlf;
        private String profession;
        private String méthode_paiement ;
        private  int don;

        public int getDon() {
            return don;
        }

        public void setDon(int don) {
            this.don = don;
        }

        public Give(int id, String nom, int montant,
                        Date date_de_naissance, String email,
                        Integer numéro_de_téléphone, String profession,
                        String methpaiement, int don) {
            this.id = id;
            this.statut = nom;
            this.montant = montant;
            this.date_de_naissance = (Date) date_de_naissance;
            this.email = email;
            this.num_tlf = numéro_de_téléphone;
            this.profession = profession;
            this.méthode_paiement = methpaiement;
            this.don = don;
        }
        public Give( String statut, int montant,
                         Date date_de_naissance, String email,
                         Integer numéro_de_téléphone, String profession,
                         String methpaiement, int don) {

            this.statut = statut;
            this.montant = montant;
            this.date_de_naissance = date_de_naissance;
            this.email = email;
            this.num_tlf = numéro_de_téléphone;
            this.profession = profession;
            this.méthode_paiement = methpaiement;
            this.don= don;

        }



        public Give() {

        }

        public Date getDate_de_naissance() {
            return date_de_naissance;
        }

        public String getEmail() {
            return email;
        }

        public Integer getNum_tlf() {
            return num_tlf;
        }

        public String getProfession() {
            return profession;
        }

        public String getMéthode_paiement() {
            return méthode_paiement;
        }

        public void setDate_de_naissance(Date date_de_naissance) {
            this.date_de_naissance = date_de_naissance;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setNum_tlf(Integer num_tlf) {
            this.num_tlf = num_tlf;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public void setMéthode_paiement(String methpaiement) {
            this.méthode_paiement = methpaiement;
        }

        public  int getId() {
            return id;
        }

        public  String getStatut() {
            return statut;
        }

        public int getMontant() {
            return montant;
        }

        public void setId(int id) {
            this.id = id;
        }

        public  void setStatut(String nom) {
            this.statut = statut;
        }

        public  void setMontant(int prenom) {
            this.montant = montant;
        }

        @Override
        public String toString() {
            return
                  id +
                    "   " + statut +
                    "   " + montant +
                    "   " + date_de_naissance +
                    "   " + email +
                    "   " + num_tlf +
                    "   " + profession +
                          "   "+ méthode_paiement +
                          "   "+ don;
        }

        public void setDate_de_naissance(String dateNaissanceStr) {
            // Convertir la String en java.sql.Date
            java.sql.Date dateNaissance = java.sql.Date.valueOf(dateNaissanceStr);

            // Assigner la date de naissance
            this.date_de_naissance = dateNaissance;
        }
    /*public void setDon(Don dons) {
    }

    public Don getDon() { return don;
    } */
    }

