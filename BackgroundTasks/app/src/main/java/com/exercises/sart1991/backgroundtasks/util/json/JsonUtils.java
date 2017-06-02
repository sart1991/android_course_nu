package com.exercises.sart1991.backgroundtasks.util.json;

import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sart1 on 6/2/2017.
 */

public class JsonUtils {

    private JsonUtils(){}

    public static User deserializeJsonUser(JSONObject jsonObject) {
        User user = null;
        try {
            user = new User(
                    jsonObject.getInt("id"),
                    jsonObject.getString("username"),
                    jsonObject.getString("email")
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> deserealizeJsonUserArray(JSONArray jsonArray) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                users.add(
                        deserializeJsonUser(jsonArray.getJSONObject(i))
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

}
