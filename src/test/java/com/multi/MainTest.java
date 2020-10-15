package com.multi;

import com.multi.controller.MainController;
import com.multi.model.AksesModel;
import com.multi.model.MenuModel;
import com.multi.model.Response;
import com.multi.model.UserModel;
import com.multi.mysql.entity.User;
import com.multi.postgre.entity.Akses;
import com.multi.postgre.entity.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MainTest {

    @Autowired
    private MainController mainController;

    @Test
    public void testGetAllUsers() {
        List<User> users = mainController.getAlluser();
        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void testGetSpecificUser() {
        String username = "denny";
        User user = mainController.getById(username);
        Assertions.assertEquals(username, user.getUsername());
    }

    @Test
    public void testSaveUser() {
        UserModel user = new UserModel();
        user.setUsername("denny1");
        user.setPassword("12345678");
        user.setName("Denny1");
        user.setAksesCode("MGR");
        Response response = mainController.saveUser(user);
        Assertions.assertEquals(true, response.getResult());
    }

    @Test
    public void testAllAkses() {
        List<Akses> akses = mainController.getAllAkses();
        Assertions.assertEquals(4, akses.size());
    }

    @Test
    public void testGetSpecificAkses() {
        String kode = "MGR";
        Akses akses = mainController.getAksesByKode(kode);
        Assertions.assertEquals(kode, akses.getAccessCode());
    }

    @Test
    public void testSaveAkses() {
        AksesModel akses = new AksesModel();
        akses.setAccessCode("MGR");
        akses.setDescription("Manager");
        akses.setMenuCode("TRS");
        Response response = mainController.saveAkses(akses);
        Assertions.assertEquals(true, response.getResult());
    }

    @Test
    public void testAllMenu() {
        List<Menu> menu = mainController.getAllMenu();
        Assertions.assertEquals(3, menu.size());
    }

    @Test
    public void testGetSpecificMenu() {
        String kode = "TRS";
        Menu menu = mainController.getMenuByCode(kode);
        Assertions.assertEquals(kode, menu.getMenuCode());
    }

    @Test
    public void testSaveMenu() {
        MenuModel menu = new MenuModel();
        menu.setMenuCode("RPT");
        menu.setDescription("Report");
        Response response = mainController.saveMenu(menu);
        Assertions.assertEquals(true, response.getResult());
    }
}
