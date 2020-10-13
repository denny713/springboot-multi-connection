package com.multi.service.implement;

import com.multi.mysql.entity.User;
import com.multi.mysql.repository.UserRepository;
import com.multi.postgre.entity.Akses;
import com.multi.postgre.entity.Menu;
import com.multi.postgre.repository.AksesRepository;
import com.multi.postgre.repository.MenuRepository;
import com.multi.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImplement implements MainService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AksesRepository aksesRepository;

    @Autowired
    private MenuRepository menuRepository;

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
    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public void saveAkses(Akses akses) {
        aksesRepository.save(akses);
    }
}
