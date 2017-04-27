package com.exercises.sart1991.startsessionsharedpreferences;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SigninFragment.OnFragmentInteractionListener{

    private SharedPreferences loginPreferences;
    private boolean haveSession;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginPreferences =
                getSharedPreferences(
                        PreferenceConstants.PREFERENCE_LOGIN.getContent(),
                        MODE_PRIVATE
                );
        haveSession = loginPreferences.getBoolean(
                PreferenceConstants.Login.KEY_SESSION.getContent(),
                false
        );
        username = loginPreferences.getString(
                PreferenceConstants.Login.KEY_USERNAME.getContent(),
                null
        );

        if (username == null) haveSession = false;

        Fragment fragment;
        if (haveSession) {
            fragment = WelcomeFragment.newInstance(username);
        } else {
            fragment = SigninFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onFragmentSignIn(String name, String password) {

        loginPreferences.edit().putString(
                PreferenceConstants.Login.KEY_USERNAME.getContent(),
                name
        ).putBoolean(
                PreferenceConstants.Login.KEY_SESSION.getContent(),
                true
        ).apply();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, WelcomeFragment.newInstance(name))
                .commit();
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return haveSession;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.close_session) {
            loginPreferences.edit().putBoolean(
                    PreferenceConstants.Login.KEY_SESSION.getContent(),
                    false).apply();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, SigninFragment.newInstance())
                    .commit();
            invalidateOptionsMenu();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshActivity() {
        finish();
        startActivity(getIntent());
    }
}
