package com.exercise.nextu.notificationbuildel4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int ID = 1;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
    }

    public void onNotificationButtonClicked(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                                    .setContentTitle("Titulo notificacion")
                                    .setContentText("Texto notificacion")
                                    .setSubText("Subtexto notificacion")
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setTicker("Ticker notificacion")
                                    .build();
        notificationManager.notify(1, notification);
    }

    public void onNotificationActionButtonClicked(View view) {
        intent.setClass(this, Main2Activity.class);
        intent.putExtra("NotificationID", ID);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        Notification.Action action = new Notification.Action.Builder(R.mipmap.ic_launcher, "Click aqui", pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new Notification.Builder(this)
                                        .setContentIntent(pendingIntent)
                                        .setContentTitle("Activity 2")
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .addAction(action)
                                        .setPriority(Notification.PRIORITY_HIGH)
                                        .setSound(sonido)
                                        .setLights(Color.BLUE, 100, 50)
                                        .build();
        notificationManager.notify(ID, notification);
    }

    public void onNotificationAction2ButtonClicked(View view) {
        intent.setClass(this, Main2Activity.class);
        intent.putExtra("NotificationID", ID);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Action action = new Notification.Action.Builder(android.R.drawable.ic_menu_share, "Click2", pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification =
                new Notification.Builder(this)
                        .addAction(action)
                        .setSmallIcon(android.R.drawable.arrow_down_float)
                        .build();
        notificationManager.notify(ID, notification);
    }

    public void onNotificationAction3ButtonClicked(View view) {
        intent.setClass(this, Main2Activity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification.Action action = new Notification.Action.Builder(android.R.drawable.btn_default, "Click3", pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(this).setSmallIcon(android.R.drawable.alert_dark_frame).addAction(action).build();

        notificationManager.notify(ID, notification);

    }
}
