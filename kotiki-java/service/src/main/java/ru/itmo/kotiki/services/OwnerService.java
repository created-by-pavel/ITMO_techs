package ru.itmo.kotiki.services;

import ru.itmo.kotiki.dto.OwnerDTO;
import ru.itmo.kotiki.model.Owner;

import java.util.List;

public interface OwnerService {
    void save(OwnerDTO ownerDTO);

    List<OwnerDTO> getAll();

    OwnerDTO getById(long id);

    void deleteById(long id);

    void updateOwner(OwnerDTO ownerDTO);

    Owner getByName(String name);
}
