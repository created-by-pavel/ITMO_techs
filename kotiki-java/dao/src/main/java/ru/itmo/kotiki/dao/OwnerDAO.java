package ru.itmo.kotiki.dao;

import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Owner;

import java.util.List;

public interface OwnerDAO {

    void add(Owner owner);

    List<Owner> getAll();

    Owner getById(Long id);

    List<Cat> getCatsByOwnerId(Long id);

    void update(Owner owner);

    void remove(Owner owner);
}
