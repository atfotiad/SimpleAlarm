package com.atfotiad.simplealarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlertReceiver extends BroadcastReceiver {
    private static final String TAG = "AlertReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        String id = intent.getStringExtra("day");
        Log.d(TAG, "onReceive: Alarm fired" );
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Remind")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Reminder")
                .setContentText("Hello from :"+ id)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(""))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManagerCompat.notify(1, builder.build());

    }
}
