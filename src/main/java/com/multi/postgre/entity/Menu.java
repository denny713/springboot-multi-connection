package com.multi.postgre.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "menu_code", length = 5)
    private String menuCode;

    @Column(name = "description", length = 50)
    private String description;
}
