package com.exercises.sart1991.dotsmenuoptionscreation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        SubMenu subMenu = menu.addSubMenu("Submenu test");
        subMenu.add("Test 1").setIcon(R.drawable.ic_blur_on).setIntent(intent);
        subMenu.add("Test 2").setIcon(R.drawable.ic_timeline);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Snackbar.make(toolbar, "Settings", Snackbar.LENGTH_LONG).show();
                //Menu contextual
                registerForContextMenu(toolbar);
                openContextMenu(toolbar);
                break;
            case R.id.action_options:
//                Snackbar.make(toolbar, "Options", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_settings, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_op1:
                Snackbar.make(toolbar, "option1", Snackbar.LENGTH_LONG).show();
            break;
            case R.id.settings_op2:
                Snackbar.make(toolbar, "option2", Snackbar.LENGTH_LONG).show();
            break;
            case R.id.settings_op2_sub1:
                Snackbar.make(toolbar, "option 2 sub 1", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.settings_op2_sub2:
                Snackbar.make(toolbar, "option 2 sub 2", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.settings_op3:
                Snackbar.make(toolbar, "option3", Snackbar.LENGTH_LONG).show();
            break;
        }
        return super.onContextItemSelected(item);
    }

    public void onHelloClicked(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.show();
    }
}
