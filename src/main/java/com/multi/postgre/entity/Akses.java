package com.multi.postgre.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menuCode")
    @NotFound(action = NotFoundAction.IGNORE)
    @OrderBy("menuCode")
    private List<Menu> menu;
}
