package entities;

import java.util.Objects;

public class partnerships {
  int idP,choix;
  String nom_p,domaine;

    public partnerships(int idP, int choix, String nom_p, String domaine) {
        this.idP = idP;
        this.choix = choix;
        this.nom
    _p = nom_p;
        this.domaine = domaine;
    }

    public partnerships(int choix, String nom_p, String domaine) {
        this.choix = choix;
        this.nom_p = nom_p;
        this.domaine = domaine;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getChoix() {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        partnerships that = (partnerships) o;
        return idP == that.idP && choix == that.choix && Objects.equals(nom_p, that.nom_p) && Objects.equals(domaine, that.domaine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idP, choix, nom_p, domaine);
    }

    @Override
    public String toString() {
        return "partnerships{" +
                "idP=" + idP +
                ", choix=" + choix +
                ", nomp_p='" + nom_p + '\'' +
                ", domaine='" + domaine + '\'' +
                '}';
    }
}
