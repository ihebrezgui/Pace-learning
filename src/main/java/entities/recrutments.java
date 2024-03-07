package entities;

import java.util.Objects;

public class recrutments {
    int idR,salaire;
    String poste,discription;

    public recrutments() {
    }

    public int getIdR() {
        return idR;
    }

    public int getSalaire() {
        return salaire;
    }

    public String getPoste() {
        return poste;
    }

    public String getDiscription() {
        return discription;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public recrutments(int idR, String poste, String discription,int salaire) {
        this.idR = idR;
        this.poste = poste;
        this.discription = discription;
        this.salaire = salaire;
    }
    public recrutments(String poste, String discription,int salaire) {
        this.poste = poste;
        this.discription = discription;
        this.salaire = salaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        recrutments that = (recrutments) o;
        return idR == that.idR && salaire == that.salaire && Objects.equals(poste, that.poste) && Objects.equals(discription, that.discription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idR, salaire, poste, discription);
    }

    @Override
    public String toString() {
        return "    " +
                "-" + idR +
                "|         " + poste +
                "                                   " + discription + '\'' +
                "              " + salaire+ '\'' +
                ' ';
    }
}
