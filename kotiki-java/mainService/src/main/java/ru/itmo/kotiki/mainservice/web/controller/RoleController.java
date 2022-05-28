package ru.itmo.kotiki.mainservice.web.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.common.model.Role;
import ru.itmo.kotiki.mainservice.service.services.RoleService;

import java.util.List;

@RestController
@RequestMapping(path = "role")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

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
