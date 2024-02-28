package esprit.tn.formation.services;

import java.util.ArrayList;
import java.util.List;

public interface Iservice<T>{
    void add(T t);
    void update(T t);
    void delete(int id);
    List<T> getAll();
    void sortByCategorie(List<T> list);
    void sortByPrice(List<T> list);
    public ArrayList<T> search(String searchTerm);
}
