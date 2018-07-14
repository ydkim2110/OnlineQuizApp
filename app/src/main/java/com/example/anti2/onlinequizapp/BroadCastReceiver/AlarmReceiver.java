package com.example.anti2.onlinequizapp.BroadCastReceiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.anti2.onlinequizapp.MainActivity;
import com.example.anti2.onlinequizapp.R;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        long when = System.currentTimeMillis();
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("ydkim","ydkim", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

            Notification.Builder builder = new Notification.Builder(context, channel.getId())
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Online Quiz App")
                    .setContentText("Hey! Try to solve question today!")
                    .setAutoCancel(true)
                    .setWhen(when)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[] {1000, 1000, 1000, 1000});
            notificationManager.notify(0, builder.build());


        } else {
            Notification.Builder builder = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Online Quiz App")
                    .setContentText("Hey! Try to solve question today!")
                    .setAutoCancel(true)
                    .setWhen(when)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[] {1000, 1000, 1000, 1000});
            notificationManager.notify(0, builder.build());

        }

    }
}
