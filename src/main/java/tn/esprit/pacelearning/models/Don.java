package tn.esprit.pacelearning.models;

public class Don {
    private  int somme;
    private  int idDon;
    private  int idDonateur;

    public Don(int somme, int idDon, int idDonateur) {
        this.somme  = somme;
        this.idDon = idDon;
        this.idDonateur = idDonateur;

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
    public String toString() {
        return "Don{" +
                "id=" + idDon+
                ", somme='" + somme + '\'' +
                ", idDonateur='" + idDonateur + '\'' +
                '}';
    }
}