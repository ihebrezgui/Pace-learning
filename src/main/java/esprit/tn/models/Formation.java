package esprit.tn.models;

public class Formation {
    private int idFormation;
    private String typeF;
    private String img;
    private String duree;
    private float prix;

    // Constructeur



    public Formation(int idFormation, String typeF, String img, float prix, String duree) {
        this.idFormation = idFormation;
        this.typeF = typeF;
        this.img = img;
        this.prix = prix;
        this.duree = duree;
    }

    // Méthodes d'accès (getters et setters)
    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getTypeF() {
        return typeF;
    }

    public void setTypeF(String typeF) {
        this.typeF = typeF;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Formation{" +
                "idFormation=" + idFormation +
                ", typeF='" + typeF + '\'' +
                ", img='" + img + '\'' +
                ", duree='" + duree + '\'' +
                ", prix=" + prix +
                '}';
    }
}

