package com.multi.controller;

import com.multi.model.Response;
import com.multi.model.UserModel;
import com.multi.mysql.entity.User;
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
        Response response = new Response();
        try {
            User usr = new User();
            usr.setUsername(user.getUsername());
            usr.setName(user.getName());
            usr.setPassword(Function.textEncrypt(user.getPassword()));
            usr.setAccessCode(user.getAksesCode());
            mainService.saveUser(usr);
            response.setResult(true);
            response.setMessage("Success");
        } catch (Exception g) {
            response.setResult(false);
            response.setMessage(g.getMessage());
        }
        return response;
    }
}
