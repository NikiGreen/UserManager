package com.system.usermanager.repository;


import com.system.usermanager.model.User;
import com.system.usermanager.model.param.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String userName);

    Optional<User> findById(Long id);

    @Transactional
    void removeAllByUsername(String userName);

    Page<User> findAllByUsername(String userName, Pageable pageable);

    Page<User> findAllByRoles(Set<Role> roles, Pageable pageable);

    Page<User> findAllById(Long id, Pageable pageable);


}