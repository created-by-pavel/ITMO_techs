package ru.itmo.kotiki.service;

import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.repository.CatRepository;
import ru.itmo.kotiki.tool.KotikiException;

import java.util.List;

public class CatService {
    private final CatRepository repository;

    public CatService() {
        repository = new CatRepository();
    }

    public void add(Cat cat) {
        repository.add(cat);
    }

    public List<Cat> getAll() {
        return repository.getAll();
    }

    public Cat getById(Long id) {
        Cat cat = repository.getById(id);
        if(cat == null) throw new KotikiException("cant find");
        return cat;
    }

    public void update(Cat cat) {
        repository.update(cat);
    }

    public void remove(Cat cat) {
        repository.remove(cat);
    }

}

