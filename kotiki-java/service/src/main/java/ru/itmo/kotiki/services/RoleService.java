package ru.itmo.kotiki.services;

import ru.itmo.kotiki.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    List<Role> getAll();

    Role getById(long id);

    void deleteById(long id);

}


