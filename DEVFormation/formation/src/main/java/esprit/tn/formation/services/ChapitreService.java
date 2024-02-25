package esprit.tn.formation.services;
import esprit.tn.formation.models.Chapitre;
import esprit.tn.formation.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ChapitreService implements Iservice <Chapitre>{
    private Connection connection;
    public ChapitreService(){connection = MyDataBase.getInstance().getConnection();};
    @Override
    public void add(Chapitre chapitre) {
        String req = "INSERT INTO `chapitre`(`nomC`, `description`, `idCours`) VALUES('"+chapitre.getNomC()+"','"+chapitre.getDescription()+"','"+chapitre.getIdCours()+"')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Succesfully added !");
        } catch (SQLException e) {
            System.err.println("An error occurred while adding the chaptre: " + e.getMessage());
        }

    }

    @Override
    public void update(Chapitre chapitre) {
        String req = "UPDATE `chapitre` SET `nomC` = '"+chapitre.getNomC()+"', `description` = '"+chapitre.getDescription()+"', `idCours` = '"+chapitre.getIdCours()+"' WHERE `idChapitre` = "+chapitre.getIdChapitre();
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Succesfully updated !");
        } catch (SQLException e) {
            System.err.println("An error occurred while updating the chaptre: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String req = "DELETE FROM `chapitre` WHERE `id` = "+id;
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(req);
            System.out.println("Succesfully deleted !");
        } catch (SQLException e) {
            System.err.println("An error occurred while deleting the chaptre: " + e.getMessage());
        }
    }

    @Override
    public List<Chapitre> getAll() {
        List<Chapitre> chapitres = new ArrayList<>();
        String req = "SELECT * FROM `chapitre`";
        try {
            PreparedStatement st = connection.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Chapitre f = new Chapitre();
                f.setIdChapitre((rs.getInt("idChapitre")));
                f.setNomC((rs.getString("nomC")));
                f.setDescription((rs.getString("description")));
                f.setIdCours((rs.getInt("idCours")));
                chapitres.add(f);
                System.out.println(f);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching the chapters: " + e.getMessage());
        }
        return chapitres;
    }

    @Override
    public void sortByCategorie(List<Chapitre> list) {
        list.sort(Comparator.comparing(Chapitre::getDescription));
        System.out.println("Sorted Chaptres by Category:");
        for (Chapitre chapitre : list) {
            System.out.println(chapitre);
        }
    }

    @Override
    public void sortByPrice(List<Chapitre> list) {

    }
    public ArrayList<Chapitre> search(String searchTerm){
        return null;
    }

}
