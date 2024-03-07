package entities;

import java.util.Objects;

public class candidat {
    int idC,idRecrutement;
    String nom,prenom,lettre,num;

    public candidat() {
    }

    public candidat(int idC, String nom, String prenom, String lettre, String num,int idRecrutement) {
        this.idC = idC;
        this.nom = nom;
        this.prenom = prenom;
        this.lettre = lettre;
        this.num = num;
        this.idRecrutement = idRecrutement;
    }

    public candidat(String nom, String prenom, String lettre, String num,int idRecrutement) {
        this.nom = nom;
        this.prenom = prenom;
        this.lettre = lettre;
        this.num = num;
        this.idRecrutement = idRecrutement;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getidRecrutement() {
        return idRecrutement;
    }

    public void setidRecrutement(int idRecrutement) {
        this.idRecrutement = idRecrutement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        candidat candidat = (candidat) o;
        return idC == candidat.idC && Objects.equals(nom, candidat.nom) && Objects.equals(prenom, candidat.prenom) && Objects.equals(lettre, candidat.lettre) && Objects.equals(num, candidat.num )&& Objects.equals(idRecrutement, candidat.idRecrutement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idC, nom, prenom, lettre, num, idRecrutement);
    }

    @Override
    public String toString() {
        return "candidat{" +
                "idC=" + idC +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", lettre='" + lettre + '\'' +
                ", num='" + num + '\'' +
                ", idRecrutement='" + idRecrutement + '\'' +
                '}';
    }
}
