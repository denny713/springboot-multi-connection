package com.multi.postgre.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "akses")
public class Akses {

    @Id
    @Column(name = "access_code", length = 5)
    private String accessCode;

    @Column(name = "menu_code", length = 5)
    private String menuCode;
}
