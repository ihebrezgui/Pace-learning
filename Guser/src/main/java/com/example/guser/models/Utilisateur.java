package com.example.guser.models;

import java.sql.Date;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private Date date_nais;
    private int num_tel;

    private String email;

    private String sexe;
    private Role role;
    private String password ;

    public enum Role {
        ETUDIANT,
        FORMATEUR,
        RH,
        FINANCIER
    }

    public Utilisateur(int id, String nom, String prenom, Date date_nais, int num_tel, String email, String sexe, Role role, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_nais = date_nais;
        this.num_tel = num_tel;
        this.email = email;
        this.sexe = sexe;
        this.role = role;
        this.password = password;
    }

    public Utilisateur(String nom, String prenom, Date date_nais, int num_tel, String email, String sexe, Role role, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_nais = date_nais;
        this.num_tel = num_tel;
        this.email = email;
        this.sexe = sexe;
        this.role = role;
        this.password = password;
    }

    public Utilisateur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDate_nais() {
        return date_nais;
    }

    public void setDate_nais(Date date_nais) {
        this.date_nais = date_nais;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
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
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_nais=" + date_nais +
                ", num_tel=" + num_tel +
                ", email='" + email + '\'' +
                ", sexe='" + sexe + '\'' +
                ", role=" + role +
                '}';
    }
}
