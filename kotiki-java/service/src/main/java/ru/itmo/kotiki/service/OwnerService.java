package ru.itmo.kotiki.service;

import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Owner;
import ru.itmo.kotiki.repository.OwnerRepository;
import ru.itmo.kotiki.tool.KotikiException;


import java.util.List;

public class OwnerService {
    private final OwnerRepository repository;

    public OwnerService() {
        repository = new OwnerRepository();
    }

    public void add(Owner owner) {
        repository.add(owner);
    }

    public List<Owner> getAll() {
        return repository.getAll();
    }

    public Owner getById(Long id) {
        Owner owner = repository.getById(id);
        if (owner == null) throw new KotikiException("cant find");
        return owner;
    }

    public List<Cat> getCatsByOwnerId(Long ownerId) {
        return repository.getCatsByOwnerId(ownerId);
    }

    public void update(Owner owner) {
        repository.update(owner);
    }

    public void remove(Owner owner) {
        repository.remove(owner);
    }

}
