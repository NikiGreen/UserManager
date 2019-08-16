package com.system.usermanager.repository;


import com.system.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);
    @Transactional
    void removeAllByUsername(String username);
    Iterable<User> findAllByUsername(String username);
        Iterable<User> findAllById(Long id);

}