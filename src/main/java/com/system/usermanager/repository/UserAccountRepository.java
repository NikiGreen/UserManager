package com.system.usermanager.repository;


import com.system.usermanager.model.UserAccount;
import com.system.usermanager.model.param.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Page<UserAccount> findAll(Pageable pageable);

    UserAccount findByUsername(String userName);

    Optional<UserAccount> findById(Long id);

    @Transactional
    void removeAllByUsername(String userName);

    Page<UserAccount> findAllByUsername(String userName, Pageable pageable);

    Page<UserAccount> findAllByRoles(Set<Role> roles, Pageable pageable);

    Page<UserAccount> findAllById(Long id, Pageable pageable);


}