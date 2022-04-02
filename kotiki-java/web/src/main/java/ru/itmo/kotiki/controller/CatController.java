package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.dto.CatDTO;
import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.services.CatService;

import java.util.List;

@RestController
@RequestMapping(path = "cat")
public class CatController {

    @Autowired
    private CatService service;

    @PostMapping(path = "add")
    public void addCat(@RequestBody CatDTO catDTO) {
        service.save(catDTO);
    }

    @GetMapping(path = "all")
    public List<CatDTO> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "get-by-id/{id}")
    public CatDTO getById(@PathVariable long id) {
        return service.getById(id);
    }

    @GetMapping(path = "get-by-color/{color}")
    public List<CatDTO> getByColor(@PathVariable Color color) {
        return service.findAllByColor(color);
    }

    @GetMapping(path = "get-friends/{id}")
    public List<CatDTO> getFriends(@PathVariable long id) {
        return service.getFriends(id);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }

    @PutMapping(path = "update")
    public void updateCat(@RequestBody CatDTO catDTO) {
        service.updateCat(catDTO);
    }

    @PatchMapping(path = "set-friends/{id}&&")
    public void setFriends(@PathVariable long id, @RequestBody List<CatDTO> friends) {
        service.setFriendsById(id, friends);
    }
}
