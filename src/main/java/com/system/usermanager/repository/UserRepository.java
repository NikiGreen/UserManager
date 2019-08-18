package com.system.usermanager.repository;


import com.system.usermanager.model.User;
import com.system.usermanager.model.parametr.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findById(Long id);

    @Transactional
    void removeAllByUsername(String username);

    Iterable<User> findAllByUsername(String username);

    Iterable<User> findAllByRoles(Set<Role> roles);

    Iterable<User> findAllById(Long id);


}