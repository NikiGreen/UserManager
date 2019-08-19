package com.system.usermanager.service.impl;

import com.system.usermanager.model.UserAccount;
import com.system.usermanager.model.param.Role;
import com.system.usermanager.repository.UserAccountRepository;
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
    private UserAccountRepository userAccountRepository;

    @Override
    public Page<UserAccount> findAll(Pageable pageable) {
        return userAccountRepository.findAll(pageable);
    }

    @Override
    public UserAccount findByUsername(String userName) {
        return userAccountRepository.findByUsername(userName);
    }

    @Override
    public Optional<UserAccount> findById(Long id) {
        return userAccountRepository.findById(id);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public Iterable<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public Page<UserAccount> findAllByUsername(String userName, Pageable pageable) {
        return userAccountRepository.findAllByUsername(userName, pageable);
    }

    @Override
    public Page<UserAccount> findAllByRoles(Set<Role> roles, Pageable pageable) {
        return userAccountRepository.findAllByRoles(roles, pageable);
    }

    @Override
    public void removeAllByUsername(String userName) {
        userAccountRepository.removeAllByUsername(userName);
    }
}
