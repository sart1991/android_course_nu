package com.super_test.thesupertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by SergioAlejandro on 4/09/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper implements ProductListener {

    private static final int DB_VERSION;
    private static final String DB_NAME, TABLE_NAME, KEY_ID,
                                KEY_CREATED_AT, KEY_IMAGE, KEY_NAME,
                                KEY_OBJECT_ID, KEY_PRICE, KEY_QUANTITY, KEY_UPDATED_AT;

    static {
        DB_VERSION = 1;
        DB_NAME = "SuperTestDatabase.db";
        TABLE_NAME = "products";
        KEY_ID = "_id";
        KEY_IMAGE = "_imageURL";
        KEY_NAME = "_name";
        KEY_OBJECT_ID = "_objectId";
        KEY_PRICE = "_price";
        KEY_QUANTITY = "_quantity";
        KEY_CREATED_AT = "_createdAt";
        KEY_UPDATED_AT = "_updatedAt";
    }

    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY,"
                                                                      + KEY_IMAGE + " TEXT,"
                                                                      + KEY_NAME + " TEXT,"
                                                                      + KEY_OBJECT_ID + " TEXT,"
                                                                      + KEY_PRICE + " TEXT,"
                                                                      + KEY_QUANTITY + " INTEGER,"
                                                                      + KEY_CREATED_AT + " TEXT,"
                                                                      + KEY_UPDATED_AT + " TEXT, "
                                                                      + "UNIQUE(" + KEY_OBJECT_ID + ") ON CONFLICT REPLACE)";

    private String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DataBaseHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_IMAGE, product.getImageURL());
            values.put(KEY_NAME, product.getName());
            values.put(KEY_OBJECT_ID, product.getObjectId());
            values.put(KEY_PRICE, product.getPrice());
            values.put(KEY_QUANTITY, product.getQuantity());
            values.put(KEY_CREATED_AT, product.getCreatedAt());
            values.put(KEY_UPDATED_AT, product.getUpdatedAt());
            db.insert(TABLE_NAME, null, values);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Product> getAllProducts() {

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Product> productsList = null;
        try {
            productsList = new ArrayList<>();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    Product product = new Product();
                    product.setId(cursor.getInt(0));
                    product.setImageURL(cursor.getString(1));
                    product.setName(cursor.getString(2));
                    product.setObjectId(cursor.getString(3));
                    product.setPrice(cursor.getString(4));
                    product.setQuantity(cursor.getInt(5));
                    product.setCreatedAt(cursor.getString(6));
                    product.setUpdatedAt(cursor.getString(7));
                    productsList.add(product);
                }
            }
            db.close();
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return productsList;
    }

    @Override
    public int getProductsCount() {
        int count;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            String QUERY = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            count = cursor.getCount();
            db.close();
            cursor.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void resetAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DROP_TABLE);
        db.execSQL("VACUUM");
        db.execSQL(CREATE_TABLE);
        db.close();
        Log.i("DataBaseHandler.class", "done");
    }

    @Override
    public ArrayList<String> getProperty(String property) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> propertyList = null;
        try {
            propertyList = new ArrayList<>();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    propertyList.add(cursor.getString(cursor.getColumnIndex(property)));
                }
            }
            db.close();
            cursor.close();
        } catch (Exception e) {
            Log.i("DataBaseHandler.class", e.getMessage());
        }
        return propertyList;
    }
}
