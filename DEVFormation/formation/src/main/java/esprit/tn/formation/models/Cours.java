package esprit.tn.formation.models;

public class Cours {
    private int idCours, idFormation;
    private String nomCours, description, categorie;
    private String cours;

    public Cours() {
    };

    public Cours(int idCours, int idFormation, String nomCours, String description, String categorie, String cours) {
        this.idCours = idCours;
        this.idFormation = idFormation;
        this.nomCours = nomCours;
        this.description = description;
        this.categorie = categorie;
        this.cours = cours;
    }

    public Cours(int idFormation, String nomCours, String description, String categorie, String cours) {
        this.idFormation = idFormation;
        this.nomCours = nomCours;
        this.description = description;
        this.categorie = categorie;
        this.cours = cours;
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

    public String getnomCours() {
        return nomCours;
    }

    public void setnomCours(String nomCours) {
        this.nomCours = nomCours;
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

    public String getcours() {
        return cours;
    }

    public void setcours(String cours) {
        this.cours = cours;
    }

    @Override
    public String toString() {
        return
                "    " + nomCours + "    " +
                "    " + description + "    " +
                "    " + categorie + "    " +
                "    " + cours ;
    }
}
