package esprit.tn.models;


public class Commande {
    private int idc,tel;
    private String nom,
            prenom,
            mail,
            address,
            panier;

    public Commande(int idc, int tel, String nom, String prenom, String mail, String address, String panier) {
        this.idc = idc;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.address = address;
        this.panier = panier;
    }

    public Commande(int tel, String nom, String prenom, String mail, String address, String panier)
    {

        this.tel=tel;
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;
        this.address=address;
        this.panier=panier;

    }

    public Commande() {

    }

    public String getPanier() {
        return panier;
    }


    public int getIdc() {
        return idc;
    }

    public int getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setPanier(String panier) {
        this.panier = panier;
    }

    @Override
    public String toString() {
        return "commande{" +
                "Id_Commande=" + idc +
                ", Tel=" + tel +
                ", Nom='" + nom + '\'' +
                ", Prenom='" + prenom + '\'' +
                ", Mail='" + mail + '\'' +
                ", Address='" + address + '\'' +
                ", Panier='" + panier + '\'' +
                '}';
    }


}



