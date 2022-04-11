package ru.itmo.kotiki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.convertation.Convert;
import ru.itmo.kotiki.dto.CatDTO;
import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.repository.CatRepository;
import ru.itmo.kotiki.tool.KotikiException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CatServiceImpl implements CatService {
    @Autowired
    private CatRepository repository;

    @Autowired
    private Convert convert;

    public void save(CatDTO catDTO) {
        Cat cat = convert.convertDtoToEntity(catDTO);
        repository.save(cat);
    }

    public List<CatDTO> getAll(String name) {
        return repository.findByOwnerName(name).stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public CatDTO getById(long id, String name) {
        return convert.convertEntityToDto(repository.findByOwnerNameAndId(name, id));
    }

    public void deleteById(long id, String name) {
        repository.deleteByOwnerNameAndId(name, id);
    }

    public void updateCat(CatDTO catDTO, String name) {
        Cat existingCat = repository.findByOwnerNameAndId(name, catDTO.getId());
        if (existingCat == null) throw new KotikiException("cant find");
        existingCat.setName(catDTO.getName());
        existingCat.setBirthDate(catDTO.getBirthDate());
        existingCat.setBreed(catDTO.getBreed());
        existingCat.setColor(catDTO.getColor());
        existingCat.setOwner(convert.convertDtoToEntity(catDTO.getOwnerDTO()));
        existingCat.setFriends(catDTO.getFriends().
                stream().map(convert::convertDtoToEntity).collect(Collectors.toList()));
        repository.save(existingCat);
    }

    public List<CatDTO> findAllByColor(Color color, String name) {
        return repository.findAllByOwnerNameAndColor(name, color).
                stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public List<CatDTO> getFriends(long id, String name) {
        Cat existingCat = repository.findByOwnerNameAndId(name, id);
        if (existingCat == null) throw new KotikiException("cant find");
        return existingCat.getFriends().stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public void setFriendsById(long id, List<CatDTO> friends, String name) {
        Cat existingCat = repository.findByOwnerNameAndId(name, id);
        if (existingCat == null) throw new KotikiException("cant find");

        List<Cat> foundFriends = new ArrayList<>();
        for (var cat : friends) {
            var foundCat = repository.findById(cat.getId()).orElse(null);
            if (foundCat == null) throw new KotikiException("cant find friend");
            foundFriends.add(foundCat);
        }
        existingCat.setFriends(foundFriends);
        repository.save(existingCat);
    }
}
