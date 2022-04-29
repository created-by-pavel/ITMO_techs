package ru.itmo.kotiki.services;

import ru.itmo.kotiki.dto.CatDTO;
import ru.itmo.kotiki.enums.Color;

import java.util.List;

public interface CatService {
    void save(CatDTO catDTO);

    List<CatDTO> getAll(String name);

    CatDTO getById(long id, String name);

    void deleteById(long id, String name);

    void updateCat(CatDTO catDTO, String name);

    List<CatDTO> findAllByColor(Color color, String name);

    List<CatDTO> getFriends(long id, String name);

    void setFriendsById(long id, List<CatDTO> friends, String name);
}
