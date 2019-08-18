package com.system.usermanager.service;

import com.system.usermanager.model.User;
import com.system.usermanager.model.param.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface UserMangerService {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    Optional<User> findById(Long id);

    User save(User user);

    Iterable<User> findAll();

    Page<User> findAllByUsername(String username, Pageable pageable);

    Page<User> findAllByRoles(Set<Role> roles, Pageable pageable);

    void removeAllByUsername(String userName);
}
