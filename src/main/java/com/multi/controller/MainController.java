package com.multi.controller;

import com.multi.model.*;
import com.multi.mysql.entity.User;
import com.multi.postgre.entity.Akses;
import com.multi.postgre.entity.Menu;
import com.multi.service.MainService;
import com.multi.util.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    private static Response response = new Response();
    private static final String SUCC = "Success Save";

    @GetMapping({"", "/"})
    @ResponseBody
    public String index() {
        return "Assalamu'alaikum Wr Wb";
    }

    @GetMapping("/user")
    @ResponseBody
    public List<User> getAlluser() {
        return mainService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getById(@PathVariable("id") String id) {
        return mainService.getByUsername(id);
    }

    @PostMapping("/user/save")
    @ResponseBody
    public Response saveUser(@Valid @RequestBody UserModel user) {
        try {
            Akses akses = mainService.getByAksesKode(user.getAksesCode());
            if (akses != null) {
                User usr = new User();
                usr.setUsername(user.getUsername());
                usr.setName(user.getName());
                usr.setPassword(Function.textEncrypt(user.getPassword()));
                usr.setAccessCode(user.getAksesCode());
                mainService.saveUser(usr);
                response.setResult(true);
                response.setMessage(SUCC);
            } else {
                response.setResult(false);
                response.setMessage("Akses " + user.getAksesCode() + " Tidak Ditemukan");
            }
        } catch (Exception g) {
            response.setResult(false);
            response.setMessage(g.getMessage());
        }
        return response;
    }

    @GetMapping("/menu")
    @ResponseBody
    public List<Menu> getAllMenu() {
        return mainService.getAllMenu();
    }

    @GetMapping("/menu/{id}")
    @ResponseBody
    public Menu getMenuByCode(@PathVariable("id") String id) {
        return mainService.getByMenuCode(id);
    }

    @PostMapping("/menu/save")
    @ResponseBody
    public Response saveMenu(@Valid @RequestBody MenuModel menu) {
        try {
            Menu mnu = new Menu();
            mnu.setMenuCode(menu.getMenuCode());
            mnu.setDescription(menu.getDescription());
            mainService.saveMenu(mnu);
            response.setResult(true);
            response.setMessage(SUCC);
        } catch (Exception d) {
            response.setResult(false);
            response.setMessage(d.getMessage());
        }
        return response;
    }

    @GetMapping("/akses")
    @ResponseBody
    public List<Akses> getAllAkses() {
        return mainService.getAllAkses();
    }

    @GetMapping("/akses/{id}")
    @ResponseBody
    public Akses getAksesByKode(@PathVariable("id") String id) {
        return mainService.getByAksesKode(id);
    }

    @PostMapping("/akses/save")
    @ResponseBody
    public Response saveAkses(@Valid @RequestBody AksesModel akses) {
        try {
            Menu menu = mainService.getByMenuCode(akses.getMenuCode());
            if (menu != null) {
                Akses aks = new Akses();
                aks.setAccessCode(akses.getAccessCode());
                aks.setMenuCode(akses.getMenuCode());
                aks.setDescription(akses.getDescription());
                mainService.saveAkses(aks);
                response.setResult(true);
                response.setMessage(SUCC);
            } else {
                response.setResult(false);
                response.setMessage("Menu " + akses.getMenuCode() + " Tidak Ditemukan");
            }
        } catch (Exception p) {
            response.setResult(false);
            response.setMessage(p.getMessage());
        }
        return response;
    }

    @PostMapping("/all/save")
    @ResponseBody
    public Response saveAll(@Valid @RequestBody UsersModel users) {
        return mainService.saveAll(users);
    }
}
