package com.super_test.thesupertest;

import java.util.ArrayList;

/**
 * Created by SergioAlejandro on 4/09/2016.
 */
public interface ProductListener {
    void addProduct(Product product);
    ArrayList<Product> getAllProducts();
    int getProductsCount();
    void resetAll();
    ArrayList<String> getProperty(String property);
    //void dropTable();
}
