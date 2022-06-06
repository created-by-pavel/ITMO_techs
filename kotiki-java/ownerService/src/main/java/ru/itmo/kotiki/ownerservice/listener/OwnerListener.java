package ru.itmo.kotiki.ownerservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.common.dto.OwnerDTO;
import ru.itmo.kotiki.ownerservice.service.services.OwnerService;

import java.util.List;

@Component
public class OwnerListener {
    @Autowired
    OwnerService ownerService;

    @RabbitListener(queues = "addOwnerQueue")
    public void save(@Payload OwnerDTO ownerDTO) {
        ownerService.save(ownerDTO);
    }

    @RabbitListener(queues = "getOwnerByIdQueue")
    public OwnerDTO getById(@Payload long id) {
        return ownerService.getById(id);
    }

    @RabbitListener(queues = "getAllOwnersQueue")
    public List<OwnerDTO> getAll() {
        return ownerService.getAll();
    }

    @RabbitListener(queues = "deleteOwnerQueue")
    public void deleteById(@Payload long id) {
        ownerService.deleteById(id);
    }

    @RabbitListener(queues = "updateOwnerQueue")
    public void update(@Payload OwnerDTO ownerDTO) {
        ownerService.updateOwner(ownerDTO);
    }
}
