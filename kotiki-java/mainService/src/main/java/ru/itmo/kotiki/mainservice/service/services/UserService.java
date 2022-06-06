package ru.itmo.kotiki.mainservice.service.services;

import ru.itmo.kotiki.common.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User getByName(String name);

    List<User> getAll();

    User getById(long id);

    void deleteById(long id);

    void updateUser(User user);
}
