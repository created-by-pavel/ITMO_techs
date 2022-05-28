package ru.itmo.kotiki.catservice.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.catservice.repository.CatRepository;
import ru.itmo.kotiki.catservice.repository.OwnerRepository;
import ru.itmo.kotiki.catservice.repository.UserRepository;
import ru.itmo.kotiki.common.convertation.Convert;
import ru.itmo.kotiki.catservice.service.tool.CatServiceException;
import ru.itmo.kotiki.common.dto.CatDTO;
import ru.itmo.kotiki.common.enums.Color;
import ru.itmo.kotiki.common.model.Cat;

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
    private UserRepository userRepository;
    @Autowired
    private OwnerRepository ownerRepository;


    @Autowired
    private Convert convert;

    public void save(CatDTO catDTO) {
        Cat cat = convert.convertDtoToEntity(catDTO);
        repository.save(cat);
    }

    public List<CatDTO> getAll(String userName) { // username
        return repository.findByOwnerId(findOwnerId(userName)).stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public CatDTO getById(long id, String userName) {
        return convert.convertEntityToDto(repository.findByOwnerIdAndId(findOwnerId(userName), id));
    }

    public void deleteById(long id, String userName) {
        repository.deleteByOwnerIdAndId(findOwnerId(userName), id);
    }

    public void updateCat(CatDTO catDTO, String userName) {
        Cat existingCat = repository.findByOwnerIdAndId(findOwnerId(userName), catDTO.getId());
        if (existingCat == null) throw new CatServiceException("cant find");
        existingCat.setName(catDTO.getName());
        existingCat.setBirthDate(catDTO.getBirthDate());
        existingCat.setBreed(catDTO.getBreed());
        existingCat.setColor(catDTO.getColor());
        existingCat.setOwner(convert.convertDtoToEntity(catDTO.getOwnerDTO()));
        existingCat.setFriends(catDTO.getFriends().
                stream().map(convert::convertDtoToEntity).collect(Collectors.toList()));
        repository.save(existingCat);
    }

    public List<CatDTO> findAllByColor(Color color, String userName) {
        return repository.findAllByOwnerIdAndColor(findOwnerId(userName), color).
                stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public List<CatDTO> getFriends(long id, String userName) {
        Cat existingCat = repository.findByOwnerIdAndId(findOwnerId(userName), id);
        if (existingCat == null) throw new CatServiceException("cant find");
        return existingCat.getFriends().stream().map(convert::convertEntityToDto).collect(Collectors.toList());
    }

    public void setFriendsById(long id, List<CatDTO> friends, String userName) {
        Cat existingCat = repository.findByOwnerIdAndId(findOwnerId(userName), id);
        if (existingCat == null) throw new CatServiceException("cant find");

        List<Cat> foundFriends = new ArrayList<>();
        for (var cat : friends) {
            var foundCat = repository.findById(cat.getId()).orElse(null);
            if (foundCat == null) throw new CatServiceException("cant find friend");
            foundFriends.add(foundCat);
        }
        existingCat.setFriends(foundFriends);
        repository.save(existingCat);
    }

    private long findOwnerId(String userName) {
        var user = userRepository.findByName(userName);
        if (user == null) throw new CatServiceException("cant find user");
        var owner = ownerRepository.findByUserId(user.getId());
        if (owner == null) throw new CatServiceException("cant find owner");
        System.out.println(owner.getId());
        return owner.getId();
    }
}
