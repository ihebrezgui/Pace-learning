package tn.esprit.gestiondonation.models;

import java.util.Date;



    public class Don {
        private  int somme;
        private  int idDon;
        private  int idDonateur;
        private Date date_don;
        private String statut_don;
        private  String commentaires;
        private String méthode_paiement;

        public void setDate_don(Date date_don) {
            this.date_don = date_don;
        }

        public void setStatut_don(String statut_don) {
            this.statut_don = statut_don;
        }

        public void setCommentaires(String commentaires) {
            this.commentaires = commentaires;
        }

        public void setMéthode_paiement(String méthode_paiement) {
            this.méthode_paiement = méthode_paiement;
        }

        public Date getDate_don() {
            return date_don;
        }

        public String getStatut_don() {
            return statut_don;
        }

        public String getCommentaires() {
            return commentaires;
        }

        public String getMéthode_paiement() {
            return méthode_paiement;
        }

        public Don(int somme, int idDon, int idDonateur,
                   Date date_don, String statut_don,
                   String commentaires, String méthode_paiement) {
            this.somme = somme;
            this.idDon = idDon;
            this.idDonateur = idDonateur;
            this.date_don = date_don;
            this.statut_don = statut_don;
            this.commentaires = commentaires;
            this.méthode_paiement = méthode_paiement;
        }
        public Don(int somme, int idDonateur,
                   Date date_don, String statut_don,
                   String commentaires, String méthode_paiement) {
            this.somme = somme;
            this.idDonateur = idDonateur;
            this.date_don = date_don;
            this.statut_don = statut_don;
            this.commentaires = commentaires;
            this.méthode_paiement = méthode_paiement;
        }

        public Don() {

        }

        public  int getSomme() {
            return somme;
        }

        public  int getIdDon() {
            return idDon;
        }

        public  int  getIdDonateur() {
            return idDonateur;
        }

        public  void setSomme(int somme) {
            this.somme = somme;
        }

        public  void setIdDon(int idDon) {
            this.idDon = idDon;
        }

        public  void setIdDonateur(int idDonateur) {
            this.idDonateur = idDonateur;
        }

        @Override
        public String toString() {
            return "Don{" +
                    "somme=" + somme +
                    ", idDon=" + idDon +
                    ", idDonateur=" + idDonateur +
                    ", date_don=" + date_don +
                    ", statut_don='" + statut_don + '\'' +
                    ", commentaires='" + commentaires + '\'' +
                    ", méthode_paiement='" + méthode_paiement + '\'' +
                    '}';
        }
    }

