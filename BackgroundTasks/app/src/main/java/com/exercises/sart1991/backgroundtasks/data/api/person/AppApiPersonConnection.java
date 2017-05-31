package com.exercises.sart1991.backgroundtasks.data.api.person;

import com.exercises.sart1991.backgroundtasks.data.api.person.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sart1 on 5/31/2017.
 */

public class AppApiPersonConnection implements ApiPersonConnection {

    private final String url;

    public AppApiPersonConnection() {
        url = "http://192.168.1.58:8086/myexample/webapi";
    }

    @Override
    public String getPeople() {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/people"));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    private HttpURLConnection connectToUrl(URL url) {
        try {
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
