package ru.itmo.kotiki.mainservice.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.kotiki.common.model.User;
import ru.itmo.kotiki.mainservice.repository.UserRepository;
import ru.itmo.kotiki.mainservice.service.tool.MainServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repository.findByName(name);
        if (user == null) throw new MainServiceException("cant find user");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(long id) {
        return repository.getById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public User getByName(String name) {
        return repository.findByName(name);
    }

    public void updateUser(User user) {
        User existingUser = repository.findById(user.getId()).orElse(null);
        if (existingUser == null) throw new MainServiceException("cant find user");
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
    }
}
