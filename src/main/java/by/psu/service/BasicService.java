package by.psu.service;

import java.util.List;

public interface BasicService<T, E>{

    List<T> findAll();

    T findById(E id);

    T save(T obj);

    T update(T obj, E id);

    void remove(E id);

}