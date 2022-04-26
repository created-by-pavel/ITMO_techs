package ru.itmo.kotiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.security.DecodeTokenUtil;
import ru.itmo.kotiki.dto.CatDTO;
import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.services.CatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "cat")
public class CatController {
    @Autowired
    private CatService service;

    @Autowired
    private DecodeTokenUtil decodeTokenUtil;

    @PostMapping(path = "add")
    public void addCat(@RequestBody CatDTO catDTO) {
        service.save(catDTO);
    }

    @GetMapping(path = "all")
    public List<CatDTO> getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.getAll(username);
    }

    @GetMapping(path = "get-by-id/{id}")
    public CatDTO getById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.getById(id, username);
    }

    @GetMapping(path = "get-by-color/{color}")
    public List<CatDTO> getByColor(@PathVariable Color color, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.findAllByColor(color, username);
    }

    @GetMapping(path = "get-friends/{id}")
    public List<CatDTO> getFriends(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        return service.getFriends(id, username);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.deleteById(id, username);
    }

    @PutMapping(path = "update")
    public void updateCat(@RequestBody CatDTO catDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.updateCat(catDTO, username);
    }

    @PatchMapping(path = "set-friends/{id}&&")
    public void setFriends(@PathVariable long id, @RequestBody List<CatDTO> friends, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        service.setFriendsById(id, friends, username);
    }
}
