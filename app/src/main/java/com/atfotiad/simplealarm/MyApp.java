package com.atfotiad.simplealarm;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class MyApp extends Application {
    public static final String SERVICE_CHANNEL = "Remind";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels(){
        NotificationChannel channel = new NotificationChannel(
                SERVICE_CHANNEL,"Alarm", NotificationManager.IMPORTANCE_HIGH
        );
        channel.setDescription("Notifications to remind.");
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (manager!= null){
            manager.createNotificationChannel(channel);
        }
    }
}
