package com.multi.mysql.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username", length = 10)
    private String username;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "access_code", length = 5)
    private String accessCode;
}
