package com.multi.service;

import com.multi.model.Response;
import com.multi.model.UserModel;
import com.multi.model.UsersModel;
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

    public List<Menu> getAllMenu();

    public Menu getByMenuCode(String menuCode);

    public void saveMenu(Menu menu);

    public List<Akses> getAllAkses();

    public Akses getByAksesKode(String accessCode);

    public void saveAkses(Akses akses);

    public Response saveAll(UsersModel users);

    public Response saveUserByResource(UserModel user, String resource);
}
