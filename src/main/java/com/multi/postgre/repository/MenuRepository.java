package com.multi.postgre.repository;

import com.multi.postgre.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

    public Menu findByMenuCode(String menuCode);
}
