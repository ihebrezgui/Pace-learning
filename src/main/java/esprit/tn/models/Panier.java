package esprit.tn.models;

public class Panier {
    private int idp, quantite;
    private float prix ;
    private String nom, prod_id;

    public Panier() {
    }

    public Panier(int idp, int quantite, String nom,  float prix, String prod_id) {
        this.idp = idp;
        this.quantite = quantite;
        this.nom = nom;
        this.prix = prix;
        this.prod_id = prod_id;
    }

    public Panier(int quantite, String nom,  float prix, String prod_id) {
        this.quantite = quantite;
        this.nom = nom;
        this.prix = prix;
        this.prod_id = prod_id;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    @Override
    public String toString() {
        return "Panier{" +
                "Id_Panier=" + idp +
                ", Quantite=" + quantite +
                ", Prix=" + prix +
                ", Nom='" + nom + '\'' +
                ", Produit_id='" + prod_id + '\'' +
                '}';
    }

}