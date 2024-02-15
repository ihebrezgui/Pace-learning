package services;

import java.sql.SQLException;

public interface IService <T> {
    public void ajouter(T t) throws SQLException;

    public void modifier(T t) throws SQLException;

    public void supprimer(T t) throws SQLException;
    //*public List<T t> afficher();
}


