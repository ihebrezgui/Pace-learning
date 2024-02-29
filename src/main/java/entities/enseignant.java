package entities;

public class enseignant {
    int idE , age;
    String email,matier,nom,prenom,comptences,langue;

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    @Override
    public String toString() {
        return "    " +
                "-" + idE +
                "|         " + age +
                "         " + email +
                "         " + matier +
                "         " + nom +
                "         " + prenom +
                "         " + comptences +
                "         " + langue +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatier() {
        return matier;
    }

    public void setMatier(String matier) {
        this.matier = matier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getComptences() {
        return comptences;
    }

    public void setComptences(String comptences) {
        this.comptences = comptences;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public enseignant() {
    }

    public enseignant(int idE, int age, String email, String matier, String nom, String prenom, String comptences, String langue) {
        this.idE = idE;
        this.age = age;
        this.email = email;
        this.matier = matier;
        this.nom = nom;
        this.prenom = prenom;
        this.comptences = comptences;
        this.langue = langue;
    }

    public enseignant(int age, String email, String matier, String nom, String prenom, String comptences, String langue) {
        this.age = age;
        this.email = email;
        this.matier = matier;
        this.nom = nom;
        this.prenom = prenom;
        this.comptences = comptences;
        this.langue = langue;
    }
}

