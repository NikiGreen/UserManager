package com.system.usermanager.repository;

import com.system.usermanager.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    UserAccount findByFirstName(String name);
}
