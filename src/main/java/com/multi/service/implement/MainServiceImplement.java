package com.multi.service.implement;

import com.multi.model.Response;
import com.multi.model.UserModel;
import com.multi.model.UsersModel;
import com.multi.mysql.entity.User;
import com.multi.mysql.repository.UserRepository;
import com.multi.postgre.entity.Akses;
import com.multi.postgre.entity.Menu;
import com.multi.postgre.entity.Users;
import com.multi.postgre.repository.AksesRepository;
import com.multi.postgre.repository.MenuRepository;
import com.multi.postgre.repository.UsersRepository;
import com.multi.service.MainService;
import com.multi.util.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImplement implements MainService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AksesRepository aksesRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UsersRepository usersRepository;

    private static Response response = new Response();
    private static String SUCC = "Success Save";

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getByMenuCode(String menuCode) {
        return menuRepository.findByMenuCode(menuCode);
    }

    @Override
    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public List<Akses> getAllAkses() {
        return aksesRepository.findAll();
    }

    @Override
    public Akses getByAksesKode(String accessCode) {
        return aksesRepository.findByAccessCode(accessCode);
    }

    @Override
    public void saveAkses(Akses akses) {
        aksesRepository.save(akses);
    }

    @Override
    public Response saveAll(UsersModel users) {
        try {
            User user = new User();
            user.setUsername(users.getUsername());
            user.setPassword(Function.textEncrypt(users.getPassword()));
            user.setName(users.getName());
            user.setAccessCode(users.getAksesCode());
            userRepository.save(user);
            List<Akses> akses = new ArrayList<>();
            users.getAccess().forEach(x -> {
                Menu menu = new Menu();
                Akses aks = new Akses();
                aks.setAccessCode(x.getAccessCode());
                aks.setDescription(x.getDescription());
                aks.setMenuCode(x.getMenuCode());
                menu.setMenuCode(x.getMenu().getMenuCode());
                menu.setDescription(x.getMenu().getDescription());
                aks.setMenu(menu);
                akses.add(aks);
            });
            aksesRepository.saveAll(akses);
            response.setResult(true);
            response.setMessage(SUCC);
        } catch (Exception l) {
            response.setResult(false);
            response.setMessage(l.getMessage());
        }
        return response;
    }

    @Override
    public Response saveUserByResource(UserModel user, String resource) {
        try {
            User mysqlUser = new User();
            Users postgreUser = new Users();
            if (resource.equals("mysql")) {
                mysqlUser.setUsername(user.getUsername());
                mysqlUser.setName(user.getName());
                mysqlUser.setPassword(Function.textEncrypt(user.getPassword()));
                mysqlUser.setAccessCode(user.getAksesCode());
                userRepository.save(mysqlUser);
            } else {
                postgreUser.setUsername(user.getUsername());
                postgreUser.setName(user.getName());
                postgreUser.setPassword(Function.textEncrypt(user.getPassword()));
                postgreUser.setAccessCode(user.getAksesCode());
                usersRepository.save(postgreUser);
            }
            response.setResult(true);
            response.setMessage(SUCC);
        } catch (Exception f) {
            response.setResult(false);
            response.setMessage(f.getMessage());
        }
        return response;
    }
}
