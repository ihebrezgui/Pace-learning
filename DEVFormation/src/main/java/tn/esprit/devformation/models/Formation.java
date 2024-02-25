package tn.esprit.devformation.models;

public class Formation {
  private int idFormation;
  private String typeF;
    public Formation(){};
    public Formation(int idFormation, String typeF) {
        this.idFormation = idFormation;
        this.typeF = typeF;
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

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public void setTypeF(String typeF) {
        this.typeF = typeF;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "idFormation=" + idFormation +
                ", typeF='" + typeF + '\'' +
                '}';
    }
}
