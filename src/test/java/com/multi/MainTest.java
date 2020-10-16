package com.multi;

import com.multi.controller.MainController;
import com.multi.model.*;
import com.multi.mysql.entity.User;
import com.multi.postgre.entity.Akses;
import com.multi.postgre.entity.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MainTest {

    @Autowired
    private MainController mainController;

    private static Response response = new Response();
    private static String resource = "postgresql";

    @Test
    public void testGetAllUsers() {
        List<User> users = mainController.getAlluser();
        Assertions.assertEquals(2, users.size());
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
        user.setUsername("denny");
        user.setPassword("123456");
        user.setName("Denny");
        user.setAksesCode("MGR");
        response = mainController.saveUser(user);
        Assertions.assertEquals(true, response.getResult());
    }

    @Test
    public void testAllAkses() {
        List<Akses> akses = mainController.getAllAkses();
        Assertions.assertEquals(6, akses.size());
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
        akses.setAccessCode("ADM");
        akses.setDescription("Administrasi");
        akses.setMenuCode("TRS");
        response = mainController.saveAkses(akses);
        Assertions.assertEquals(true, response.getResult());
    }

    @Test
    public void testAllMenu() {
        List<Menu> menu = mainController.getAllMenu();
        Assertions.assertEquals(5, menu.size());
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
        response = mainController.saveMenu(menu);
        Assertions.assertEquals(true, response.getResult());
    }

    @Test
    public void testSaveAll() {
        UsersModel user = new UsersModel();
        user.setUsername("denny1");
        user.setName("Denny1");
        user.setPassword("12345678");
        user.setAksesCode("GDG");
        List<AccessModel> akses = new ArrayList<>();
        for (int x = 0; x < 2; x++) {
            AccessModel aks = new AccessModel();
            MenuModel menu = new MenuModel();
            if (x == 0) {
                aks.setAccessCode("GDG");
                aks.setDescription("Gudang");
                aks.setMenuCode("SOP");
                menu.setMenuCode("SOP");
                menu.setDescription("Stock Opname");
            } else {
                aks.setAccessCode("ACC");
                aks.setDescription("Accounting");
                aks.setMenuCode("GLD");
                menu.setMenuCode("GLD");
                menu.setDescription("General Ledger");
            }
            aks.setMenu(menu);
            akses.add(aks);
        }
        user.setAccess(akses);
        response = mainController.saveAll(user);
        Assertions.assertEquals(true, response.getResult());
    }

    @Test
    public void testSaveByResource() {
        UserModel user = new UserModel();
        user.setUsername("denny2");
        user.setName("Denny2");
        user.setPassword("123456789");
        user.setAksesCode("ACC");
        response = mainController.saveUserByResource(user, resource);
        Assertions.assertEquals(true, response.getResult());
    }
}
