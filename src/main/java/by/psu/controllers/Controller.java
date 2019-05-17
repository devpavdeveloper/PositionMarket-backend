package by.psu.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Controller<T, E> {

    public ResponseEntity<List<T>> get();

    public ResponseEntity<T> get(E uuid);

    public ResponseEntity<T> create(T obj);

    public ResponseEntity<T> update(T obj);

    public ResponseEntity delete(E uuid);

    public ResponseEntity delete(E[] uuid);

}
