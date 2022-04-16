package ru.itmo.kotiki.services;

import ru.itmo.kotiki.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User getByName(String name);

    List<User> getAll();

    User getById(long id);

    void deleteById(long id);

    void updateUser(User user);
}
