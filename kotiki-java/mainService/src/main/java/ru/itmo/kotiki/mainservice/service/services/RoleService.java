package ru.itmo.kotiki.mainservice.service.services;


import ru.itmo.kotiki.common.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    List<Role> getAll();

    Role getById(long id);

    void deleteById(long id);

}


