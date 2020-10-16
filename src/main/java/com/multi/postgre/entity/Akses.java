package com.multi.postgre.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "akses")
public class Akses {

    @Id
    @Column(name = "access_code", length = 5)
    private String accessCode;

    @Column(name = "menu_code", length = 5)
    private String menuCode;

    @Column(name = "description", length = 50)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "menu_code", insertable = false, updatable = false, referencedColumnName = "menu_code")
    private Menu menu;
}
