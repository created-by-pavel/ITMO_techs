package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    public Role save(Role role) {
        return repository.save(role);
    }

    public List<Role> getAll() {
        return repository.findAll();
    }

    public Role getById(long id) {
        return repository.getById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
