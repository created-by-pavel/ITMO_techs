package ru.itmo.kotiki_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki_spring.dto.OwnerDTO;
import ru.itmo.kotiki_spring.services.OwnerService;

import java.util.List;

@RestController
@RequestMapping(path = "owner")
public class OwnerController {

    @Autowired
    private OwnerService service;

    @PostMapping(path = "add")
    public void addCat(@RequestBody OwnerDTO ownerDTO) {
        service.save(ownerDTO);
    }

    @GetMapping(path = "all")
    public List<OwnerDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "get/{id}")
    public OwnerDTO getById(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }

    @PutMapping(path = "update")
    public void updateOwner(@RequestBody OwnerDTO ownerDTO) {
        service.updateOwner(ownerDTO);
    }
}