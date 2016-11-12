package com.super_test.thesupertest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by SergioAlejandro on 4/09/2016.
 */
public class Product implements Serializable{

    private static final String TAG = "Product.class";

    private int id;
    private String createdAt;
    private String imageURL;
    private String name;
    private String objectId;
    private String price;
    private int quantity;
    private String updatedAt;
    public Product() {}

    public Product(String createdAt, String imageURL, String name, String objectId, String price, int quantity, String updatedAt) {

        this.createdAt = createdAt;
        this.imageURL = imageURL;
        this.name = name;
        this.objectId = objectId;
        this.price = price;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
    }

    public Product(int id, String createdAt, String imageURL, String name, String objectId, String price, int quantity, String updatedAt) {

        this.id = id;
        this.createdAt = createdAt;
        this.imageURL = imageURL;
        this.name = name;
        this.objectId = objectId;
        this.price = price;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static RequestQueue updateFromJsonToDB(final Context context) {
        final DataBaseHandler dataBaseHandler = new DataBaseHandler(context);
        final RequestCall requestCall = new RequestCall();
        RequestQueue productsRequest = requestCall.doRequest(context);
        productsRequest.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<JSONObject>() {
            @Override
            public void onRequestFinished(Request<JSONObject> request) {
                try {
                    JSONObject jsonProducts = requestCall.getJson();
                    JSONArray jsonProductsArray = jsonProducts.getJSONArray("results");
                    for (int i = 0; i<jsonProductsArray.length(); i++) {
                        JSONObject mJsonObject = jsonProductsArray.getJSONObject(i);
                        Product product = new Product();
                        product.setImageURL(mJsonObject.has("imageURL")? mJsonObject.getString("imageURL") : "");
                        product.setName(mJsonObject.has("name")? mJsonObject.getString("name") : "Unnamed");
                        product.setObjectId(mJsonObject.getString("objectId"));
                        product.setPrice(mJsonObject.has("price")? mJsonObject.getString("price") : "$0");
                        product.setQuantity(mJsonObject.has("quantity")? mJsonObject.getInt("quantity") : 0);
                        product.setCreatedAt(mJsonObject.getString("createdAt"));
                        product.setUpdatedAt(mJsonObject.getString("updatedAt"));
                        dataBaseHandler.addProduct(product);
                        //Log.i(TAG,"Iteration # " + i + " " + dataBaseHandler.getAllProducts().get(i));
                    }
                    Log.i(TAG, "yes");
                } catch (JSONException jsone) {
                    Log.i("Product.class", jsone.getMessage());
                    jsone.printStackTrace();
                }
            }
        });

        return productsRequest;
    }

    public static RequestQueue updatFromDBtoJson(final Context context) {
        final DataBaseHandler dataBaseHandler = new DataBaseHandler(context);
        final RequestCall requestCall = new RequestCall();
        RequestQueue requestObjectUpdate = null;
        for (Product product : dataBaseHandler.getAllProducts()) {
            Log.i(TAG, product.toString());
            requestObjectUpdate = requestCall.doRequest(context, product);
        }
        return requestObjectUpdate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"imageUrl\":" + "\"" +imageURL + "\"" +
                ",\"name\":" + "\"" + name + "\"" +
                ",\"objectId\": " + "\"" + objectId + "\"" +
                ",\"price\":" + "\"" + price + "\"" +
                ",\"quantity\":" + "\"" +quantity + "\"" + "}";
    }
}
