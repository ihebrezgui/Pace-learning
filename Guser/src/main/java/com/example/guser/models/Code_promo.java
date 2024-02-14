package com.example.guser.models;

import java.util.Date;

public class Code_promo {
    private int id_promo;
    private String code;
    private Date date_expiration ;
    private boolean active;

    public Code_promo() {
        this.id_promo = id_promo;
        this.code = code;
        this.date_expiration = date_expiration;
        this.active = active;
    }

    public Code_promo(String code, Date date_expiration, boolean active) {
        this.code = code;
        this.date_expiration = date_expiration;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Code_promo{" +
                "id_promo=" + id_promo +
                ", code='" + code + '\'' +
                ", date_expiration=" + date_expiration +
                ", active=" + active +
                '}';
    }
}
