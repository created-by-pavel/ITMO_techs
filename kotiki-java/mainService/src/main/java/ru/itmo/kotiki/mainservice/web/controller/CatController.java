package ru.itmo.kotiki.mainservice.web.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.common.dto.CatDTO;
import ru.itmo.kotiki.common.enums.Color;
import ru.itmo.kotiki.common.requestTemplate.*;
import ru.itmo.kotiki.mainservice.web.security.DecodeTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "cat")
public class CatController {
    @Autowired
    RabbitTemplate template;

    @Autowired
    private DecodeTokenUtil decodeTokenUtil;

    @PostMapping(path = "add")
    public void addCat(@RequestBody CatDTO catDTO) {
        template.convertAndSend("addQueue", catDTO);
    }

    @GetMapping(path = "all")
    public List<CatDTO> getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(username);
        requestTemplate.setRoute("getAllQueue");
        return (ArrayList<CatDTO>) template.convertSendAndReceive("getAllQueue", requestTemplate);
    }

    @GetMapping(path = "get-by-id/{id}")
    public CatDTO getById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getByIdQueue");
        return (CatDTO) template.convertSendAndReceive("getByIdQueue", requestTemplate);
    }

    @GetMapping(path = "get-by-color/{color}")
    public List<CatDTO> getByColor(@PathVariable Color color, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(color, username);
        requestTemplate.setRoute("getByColorQueue");
        return (ArrayList<CatDTO>) template.convertSendAndReceive("getByColorQueue", requestTemplate);
    }

    @GetMapping(path = "get-friends/{id}")
    public List<CatDTO> getFriends(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        requestTemplate.setRoute("getFriendsQueue");
        return (ArrayList<CatDTO>) template.convertSendAndReceive("getFriendsQueue", requestTemplate);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        template.convertAndSend("deleteQueue", requestTemplate);
    }

    @PutMapping(path = "update")
    public void updateCat(@RequestBody CatDTO catDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(catDTO, username);
        template.convertAndSend("updateQueue", requestTemplate);
    }

    @PatchMapping(path = "set-friends/{id}&&")
    public void setFriends(@PathVariable long id, @RequestBody List<CatDTO> friends, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = decodeTokenUtil.decodeToken(request, response);
        RequestTemplate requestTemplate = new RequestTemplate(id, username);
        template.convertAndSend("setFriendsQueue", requestTemplate);
    }
}


