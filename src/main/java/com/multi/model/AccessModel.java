package com.multi.model;

import lombok.Data;

@Data
public class AccessModel {

    private String accessCode;
    private String menuCode;
    private String description;
    private MenuModel menu;
}
