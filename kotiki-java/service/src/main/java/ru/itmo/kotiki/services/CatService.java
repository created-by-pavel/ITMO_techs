package ru.itmo.kotiki.services;

import ru.itmo.kotiki.dto.CatDTO;
import ru.itmo.kotiki.enums.Color;

import java.util.List;

public interface CatService {
    void save(CatDTO catDTO);

    List<CatDTO> getAll();

    CatDTO getById(long id);

    void deleteById(long id);

    void updateCat(CatDTO catDTO);

    List<CatDTO> findAllByColor(Color color);

    List<CatDTO> getFriends(long id);

    void setFriendsById(long id, List<CatDTO> friends);
}
