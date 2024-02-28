package tn.esprit.devformation.models;

public class Chapitre {
    private int idChapitre,idCours;
    private String nomC,description;
    public Chapitre(){};
    public Chapitre(int idChapitre, int idCours, String nomC, String description) {
        this.idChapitre = idChapitre;
        this.idCours = idCours;
        this.nomC = nomC;
        this.description = description;
    }

    public Chapitre(int idCours, String nomC, String description) {
        this.idCours = idCours;
        this.nomC = nomC;
        this.description = description;
    }

    public int getIdChapitre() {
        return idChapitre;
    }

    public void setIdChapitre(int idChapitre) {
        this.idChapitre = idChapitre;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Chapitre{" +
                "idChapitre=" + idChapitre +
                ", idCours=" + idCours +
                ", nomC='" + nomC + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
