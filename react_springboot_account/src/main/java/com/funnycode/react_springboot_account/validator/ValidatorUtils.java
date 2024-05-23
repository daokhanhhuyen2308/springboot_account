package com.funnycode.react_springboot_account.validator;

public class ValidatorUtils {
    public static boolean isValidPassword(String password){
        return password != null && password.length() >= 5;
    }

    public static boolean isValidUsername(String username){
        return username!= null && username.length() >= 3;
    }
}
