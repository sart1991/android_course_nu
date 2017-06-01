package com.exercises.sart1991.backgroundtasks.data.api.user;

import com.exercises.sart1991.backgroundtasks.data.api.user.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by sart1 on 5/31/2017.
 */

public class AppApiUserConnection implements ApiUserConnection {

    private static final String TAG = AppApiUserConnection.class.getSimpleName();

    private String url;

    public AppApiUserConnection() {
        url = "http://192.168.1.58:8086/nextuniversity/webapi";
    }

    @Override
    public String getUsers() {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users"));
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return "";
    }

    @Override
    public void postUser(User user) {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users"));
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(user.toString().getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(user.toString().getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public void putUser(User user) {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users/" + user.getId()));
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(user.toString().getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(user.toString().getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public void deleteUser(int userId) {
        HttpURLConnection connection = null;
        try {
            connection = connectToUrl(new URL(url + "/users/" + userId));
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(String.valueOf(userId).getBytes().length);

            OutputStream output = connection.getOutputStream();
            output.write(String.valueOf(userId).getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
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
