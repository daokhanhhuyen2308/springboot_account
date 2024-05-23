package com.funnycode.react_springboot_account.utils;

public class Endpoints {

    public static final String[] PUBLIC = {
            "/api/auth/login",
            "/api/auth/register",
            "/api/product/getAll",
            "/api/product/{id}",

    };

    public static final String[] PRIVATE = {
            "/api/product/add",
            "/api/product/update/{id}",
            "/api/product/delete/{id}",
            "/api/auth/getAllAccount"
    };
}
