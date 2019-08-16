package com.system.usermanager.repository;


import com.system.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Transactional
    void removeAllByUsername(String username);
    Iterable<User> findAllByUsername(String username);
}