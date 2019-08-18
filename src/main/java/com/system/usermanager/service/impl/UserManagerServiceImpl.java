package com.system.usermanager.service.impl;

import com.system.usermanager.model.User;
import com.system.usermanager.model.param.Role;
import com.system.usermanager.repository.UserRepository;
import com.system.usermanager.service.UserMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserManagerServiceImpl implements UserMangerService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAllByUsername(String userName, Pageable pageable) {
        return userRepository.findAllByUsername(userName, pageable);
    }

    @Override
    public Page<User> findAllByRoles(Set<Role> roles, Pageable pageable) {
        return userRepository.findAllByRoles(roles, pageable);
    }

    @Override
    public void removeAllByUsername(String userName) {
        userRepository.removeAllByUsername(userName);
    }
}
