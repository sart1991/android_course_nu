package com.super_test.thesupertest;

/**
 * Created by SergioAlejandro on 2/09/2016.
 */
public enum RequestedURLS {

    LOGIN_URL("https://api.parse.com/1/login?"),
    TOKEN_LOGIN_URL("https://api.parse.com/1/users/me"),
    SIGN_UP_URL("https://api.parse.com/1/"),
    PRODUCTS_URL("https://api.parse.com/1/classes/products");


    private final String url;
    RequestedURLS(String url){
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
