package esprit.tn.formation.models;

import java.util.Objects;

public class Formation {
    private int idFormation;
    private String typeF;
    private String img;

    public Formation() {
    }

    ;

    public Formation(int idFormation, String typeF, String img) {
        this.idFormation = idFormation;
        this.typeF = typeF;
        this.img = img;
    }
    public Formation(String typeF, String img) {
        this.typeF = typeF;
        this.img = img;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formation formation = (Formation) o;
        return idFormation == formation.idFormation && Objects.equals(typeF, formation.typeF) && Objects.equals(img, formation.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormation, typeF, img);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "idFormation=" + idFormation +
                ", typeF='" + typeF + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}




