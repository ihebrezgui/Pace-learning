package tn.esprit.devformation.services;

import tn.esprit.devformation.models.Formation;

import java.util.List;

public interface Iservice <T>{
    void add(T t);
    void update(T t);
    void delete(int id);
    List<T> getAll();
}
