package esprit.tn.interfaces;
import java.sql.SQLException;
import java.util.List;
public interface InterfaceService<T> {

        void ajouter(T t) throws SQLException;
        void modifier(T t) throws SQLException;
        void supprimer(int idc) throws SQLException;
        List<T>recuperer() throws SQLException;
        List<T>tri_par_nom_asc() throws SQLException;
        List<T>chercher(String nom) throws SQLException;
        List<T>tri_par_nom_desc() throws SQLException;

    }


