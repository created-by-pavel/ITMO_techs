package ru.itmo.kotiki.catservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.itmo.kotiki.catservice.service.services.CatService;
import ru.itmo.kotiki.common.dto.CatDTO;
import ru.itmo.kotiki.common.enums.Color;
import ru.itmo.kotiki.common.requestTemplate.DeleteRequestTemplate;
import ru.itmo.kotiki.common.requestTemplate.GetRequestTemplate;
import ru.itmo.kotiki.common.requestTemplate.PatchRequestTemplate;
import ru.itmo.kotiki.common.requestTemplate.PutRequestTemplate;

import java.util.List;

@Component
public class CatListener {
    @Autowired
    CatService catService;

    @RabbitListener(queues = "addQueue")
    public void save(@Payload CatDTO catDTO) {
        catService.save(catDTO);
    }

    @RabbitListener(queues = "getByIdQueue")
    public CatDTO getById(@Payload GetRequestTemplate getRequestTemplate) {
        var parameters = getRequestTemplate.getParameters();
        return catService.getById((long) parameters.get(0), (String) parameters.get(1));
    }

    @RabbitListener(queues = "getAllQueue")
    public List<CatDTO> getAll(@Payload GetRequestTemplate getRequestTemplate) {
        return catService.getAll((String) getRequestTemplate.getParameters().get(0));
    }

    @RabbitListener(queues = "getByColorQueue")
    public List<CatDTO> getByColor(GetRequestTemplate getRequestTemplate) {
        var parameters = getRequestTemplate.getParameters();
        return catService.findAllByColor((Color) parameters.get(0), (String) parameters.get(1));
    }

    @RabbitListener(queues = "getFriendsQueue")
    public List<CatDTO> getFriends(GetRequestTemplate getRequestTemplate) {
        var parameters = getRequestTemplate.getParameters();
        return catService.getFriends((long) parameters.get(0), (String) parameters.get(1));
    }

    @RabbitListener(queues = "deleteQueue")
    public void delete(DeleteRequestTemplate deleteRequestTemplate) {
        var parameters = deleteRequestTemplate.getParameters();
        catService.deleteById((long) parameters.get(0), (String) parameters.get(1));
    }

    @RabbitListener(queues = "setFriendsQueue")
    public void setFriends(PatchRequestTemplate patchRequestTemplate) {
        var parameters = patchRequestTemplate.getParameters();
        catService.setFriendsById((long) parameters.get(0), (List<CatDTO>) parameters.get(1), (String) parameters.get(2));
    }

    @RabbitListener(queues = "updateQueue")
    public void updateCat(PutRequestTemplate putRequestTemplate) {
        var parameters = putRequestTemplate.getParameters();
        catService.updateCat((CatDTO) parameters.get(0), (String) parameters.get(1));
    }
}
