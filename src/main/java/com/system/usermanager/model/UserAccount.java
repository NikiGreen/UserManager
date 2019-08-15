package com.system.usermanager.model;

import com.system.usermanager.parametrs.Role;
import com.system.usermanager.parametrs.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String userName;

    private String password;
    private String firstName;

    private Role userRole;
    private Status userStatus;

    private Date createdAt;
}
