package com.system.usermanager.service;

import com.system.usermanager.model.UserAccount;
import com.system.usermanager.model.param.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface UserMangerService {

    Page<UserAccount> findAll(Pageable pageable);

    UserAccount findByUsername(String username);

    Optional<UserAccount> findById(Long id);

    UserAccount save(UserAccount userAccount);

    Iterable<UserAccount> findAll();

    Page<UserAccount> findAllByUsername(String username, Pageable pageable);

    Page<UserAccount> findAllByRoles(Set<Role> roles, Pageable pageable);

    void removeAllByUsername(String userName);
}
