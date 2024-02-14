package tn.esprit.pacelearning.models;

public class Donateur {
    private  int  id ;
    private  String nom;
    private  String prenom;


    public Donateur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Donateur() {

    }

    public  int getId() {
        return id;
    }

    public  String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  void setNom(String nom) {
        this.nom = nom;
    }

    public  void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Donateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", Prénom='" + prenom + '\'' +
                '}';
    }
}