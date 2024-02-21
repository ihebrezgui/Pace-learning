package esprit.tn.formation.models;

public class Cours {
    private int idCours, idFormation;
    private String nomC, description, categorie;
    private float prix;

    public Cours() {
    };

    public Cours(int idCours, int idFormation, String nomC, String description, String categorie, float prix) {
        this.idCours = idCours;
        this.idFormation = idFormation;
        this.nomC = nomC;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
    }

    public Cours(int idFormation, String nomC, String description, String categorie, float prix) {
        this.idFormation = idFormation;
        this.nomC = nomC;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "idCours=" + idCours +
                ", idFormation=" + idFormation +
                ", nomC='" + nomC + '\'' +
                ", description='" + description + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prix=" + prix +
                '}';
    }
}
