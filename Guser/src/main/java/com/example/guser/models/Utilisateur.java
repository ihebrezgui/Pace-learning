package com.example.guser.models;

import java.util.Date;

public class Utilisateur {
    private int id, num_tel;
    private String nom, prenom, email, sexe;
    private Date date_nais;

    public Utilisateur() {
        this.id = id;
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.date_nais = date_nais;
    }

    public Utilisateur(int num_tel, String nom, String prenom, String email, String sexe, Date date_nais) {
        this.num_tel = num_tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.date_nais = date_nais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public java.sql.Date getDate_nais() {
        return (java.sql.Date) date_nais;
    }

    public void setDate_nais(Date date_nais) {
        this.date_nais = date_nais;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", num_tel=" + num_tel +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", sexe='" + sexe + '\'' +
                ", date_nais=" + date_nais +
                '}';
    }

}
