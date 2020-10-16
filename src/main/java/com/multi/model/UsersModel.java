package com.multi.model;

import lombok.Data;

import java.util.List;

@Data
public class UsersModel {

    private String username;
    private String name;
    private String password;
    private String aksesCode;
    private List<AccessModel> access;
}
