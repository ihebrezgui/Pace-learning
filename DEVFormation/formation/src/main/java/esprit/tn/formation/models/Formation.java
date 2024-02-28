package esprit.tn.formation.models;

import java.util.Objects;

public class Formation {
    private int idFormation;
    private String typeF;
    private String img;
    private Float prix;
    private String duree;
    private String status;


    public Formation(String typeF, String img, Float prix, String duree,String status) {
        this.typeF = typeF;
        this.img = img;
        this.prix = prix;
        this.duree = duree;
        this.status = status;
    }

    public Formation(int idFormation, String typeF, String img, Float prix, String duree,String status) {
        this.idFormation = idFormation;
        this.typeF = typeF;
        this.img = img;
        this.prix = prix;
        this.duree = duree;
        this.status = status;
    }



    public Formation() {
    }

    ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Formation(String typeF) {
        this.typeF = typeF;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public String getTypeF() {
        return typeF;
    }

    public int setIdFormation(int idFormation) {
        this.idFormation = idFormation;
        return idFormation;
    }

    public String setTypeF(String typeF) {
        this.typeF = typeF;
        return typeF;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "idFormation=" + idFormation +
                ", typeF='" + typeF + '\'' +
                ", img='" + img + '\'' +
                ", prix=" + prix +
                ", duree='" + duree + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}




