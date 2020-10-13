package com.multi.service;

import com.multi.mysql.entity.User;
import com.multi.postgre.entity.Akses;
import com.multi.postgre.entity.Menu;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MainService {

    public List<User> getAllUsers();

    public User getByUsername(String username);

    public void saveUser(User user);

    public void saveMenu(Menu menu);

    public void saveAkses(Akses akses);
}
