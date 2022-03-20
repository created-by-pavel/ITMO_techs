package ru.itmo.kotiki.dao;

import ru.itmo.kotiki.entity.Cat;

import java.util.List;

public interface CatDAO {

    void add(Cat cat);

    List<Cat> getAll();

    Cat getById(Long id);

    void update(Cat cat);

    void remove(Cat cat);
}
