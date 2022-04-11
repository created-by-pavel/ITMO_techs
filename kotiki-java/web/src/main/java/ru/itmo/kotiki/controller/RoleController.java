package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.model.Role;
import ru.itmo.kotiki.services.RoleService;

import java.util.List;

@RestController
@RequestMapping(path = "role")
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping(path = "add")
    public void addRole(@RequestBody Role role) {
        service.save(role);
    }

    @GetMapping(path = "all")
    public List<Role> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "get-by-id/{id}")
    public Role getById(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }
}
