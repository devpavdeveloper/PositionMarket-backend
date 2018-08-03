package by.psu.service;

import java.util.List;

public interface BasicService<T, E>{
    public List<T> findAll();
    public T findById(E id);
    public T save(T obj);
    public T update(T obj, E id);
    public void remove(E id);
}
