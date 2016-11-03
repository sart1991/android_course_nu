package com.exercise.nextu.codingfragmentsviewsl1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MailsListFragment mailsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mailsListFragment = new MailsListFragment();
        fragmentTransaction.replace(R.id.container_frame_layout_portrait, mailsListFragment);
        fragmentTransaction.commit();
    }

    public void onBtnShowContentClicked(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MailContentFragment mailContentFragment = new MailContentFragment();
        fragmentTransaction.add(R.id.container_frame_layout, mailContentFragment);
        fragmentTransaction.commit();
    }

    public void onBtnShowContentPortraitClicked(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MailContentFragment mailContentFragment = new MailContentFragment();
        fragmentTransaction.replace(R.id.container_frame_layout_portrait, mailContentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
