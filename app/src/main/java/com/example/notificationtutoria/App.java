package com.example.notificationtutoria;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.core.app.NotificationCompat;

public class App  extends Application {
    public static final String channel_Id="id";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotification();

    }

    private void createNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channel_Id,"messages", NotificationManager.IMPORTANCE_HIGH);
 channel.enableVibration(true);
 channel.enableLights(true);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}
