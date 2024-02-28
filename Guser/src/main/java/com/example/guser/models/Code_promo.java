package com.example.guser.models;
import java.time.LocalDate;
import java.util.Date;

public class Code_promo {
    private int id_promo;
    private String code;
    private Date date_expiration;
    private int active;
    private int idUser;
    private Utilisateur utilisateur;
    private com.example.guser.models.Utilisateur Utilisateur;


    public Code_promo(int id_promo,String code, Date date_expiration, int active, int idUser) {
        this.id_promo = id_promo;
        this.code = code;
        this.date_expiration = date_expiration;
        this.active = active;
        this.idUser = idUser;
    }

    public Code_promo() {
    }

    public Code_promo(String code, Date date_expiration, int active, int idUser) {
        this.code = code;
        this.date_expiration = date_expiration;
        this.active = active;
        this.idUser = idUser;
    }

    public int getId_promo() {
        return id_promo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public java.sql.Date getDate_expiration() {
        return (java.sql.Date) date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Code_promo{" +
                "id_promo=" + id_promo +
                ", code='" + code + '\'' +
                ", date_expiration=" + date_expiration +
                ", active=" + active +
                ", idUser=" + idUser +
                '}';
    }
    public void setUser(Utilisateur user) {
        this.utilisateur = user;
    }

    // Getter method for user
    public Utilisateur getUser() {
        return utilisateur;
    }

    public void setDate_expiration(LocalDate value) {
    }
}
