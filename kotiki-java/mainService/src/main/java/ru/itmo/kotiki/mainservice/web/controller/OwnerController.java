package ru.itmo.kotiki.mainservice.web.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.kotiki.common.dto.OwnerDTO;


@RestController
@RequestMapping(path = "owner/")
public class OwnerController {
    @Autowired
    RabbitTemplate template;

    @PostMapping(path = "add")
    public void addCat(@RequestBody OwnerDTO ownerDTO) {
        template.convertAndSend("addOwnerQueue", ownerDTO);
    }

    /*@GetMapping(path = "all")
    public List<OwnerDTO> getAll() {
       return (ArrayList<OwnerDTO>) template.sendAndReceive("getAllOwnersQueue");
    }*/

    @GetMapping(path = "get-by-id/{id}")
    public OwnerDTO getById(@PathVariable long id) {
        return (OwnerDTO) template.convertSendAndReceive("getOwnerByIdQueue", id);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable long id) {
        template.convertAndSend("deleteOwnerQueue", id);
    }

    @PutMapping(path = "update")
    public void updateOwner(@RequestBody OwnerDTO ownerDTO) {
        template.convertAndSend("updateOwnerQueue", ownerDTO);
    }
}
