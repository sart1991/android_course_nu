package com.exercises.sart1991.databasecreation;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (LinearLayout) findViewById(R.id.fruit_container);

        SQLiteDatabase myDatabase = openOrCreateDatabase("MyDatabase", MODE_PRIVATE, null);
        myDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS vegetable(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "color TEXT NOT NULL" +
                ");"
        );
        /*myDatabase.execSQL(
                "INSERT INTO fruit VALUES(" +
                        "1, \"Pineapple\", \"Yellow\"" +
                ");"
        );*/
        /*String name[] = new String[] {"Onion", "Carrot", "Tomato", "Potato", "Garlic"};
        String color[] = new String[] {"White", "Orange", "Red", "Brown", "White"};
        for(int i = 0; i < name.length; i++) {
            myDatabase.execSQL(
                    "INSERT INTO vegetable(name, color) VALUES(" +
                            "?, ?" +
                            ")",
                    new Object[]{name[i], color[i]}
            );
        }*/
        /*ContentValues values = new ContentValues();
        values.put("name", "Strawberry");
        values.put("color", "Red");
        long count = myDatabase.insert("fruit", "null", values);*/

        myDatabase.execSQL(
                "UPDATE fruit SET name=?, color=? WHERE id = 15", new Object[]{"Orange", "Orange"}
        );
        ContentValues values = new ContentValues();
        values.put("name", "Ribbon");
        values.put("color", "Amber");
        myDatabase.update("fruit", values, "id=?", new String[]{"3"});
//        myDatabase.execSQL("DELETE FROM fruit WHERE id = ?", new Object[]{16});
//        myDatabase.delete("fruit", "id = ?", new String[] {"13"});

        Cursor cursor = myDatabase.rawQuery(
                "SELECT f.name, v.name FROM fruit f LEFT OUTER JOIN vegetable v ON f.color=v.color", null
        );
        readAllData(cursor);
    }

    private void readAllData(Cursor cursor) {
        if (cursor.moveToFirst()) {
            do {
//                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(0);
                String color = cursor.getString(1);
                TextView textView = new TextView(this);
                String fullFruit = String.format(
                        Locale.getDefault(), "NAME: %s, NAME: %s", name, color
                );
                textView.setText(fullFruit);
                container.addView(textView);

            } while (cursor.moveToNext());
        }
    }
}
