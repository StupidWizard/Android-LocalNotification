package com.example.localnotifyexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * This stupid code is created by thantieuhodo on 4/24/17.
 */
public class LocalNotificationUtils {

    public static final int REQUEST_CODE = 13579;

    public static int MORNING_NOTIFICATION_TIME_HOUR     = 7;
    public static int MORNING_NOTIFICATION_TIME_MINUTE   = 30;

    public static void clearLocalNotification(Context context) {
        Intent intent = new Intent(context, LocalNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pendingIntent);
        // Cancel the `PendingIntent` after you've canceled the alarm
        pendingIntent.cancel();
    }

    public static void scheduleLocalNotification(Context context, String title, String msg) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context.getApplicationContext(),
                LocalNotificationReceiver.class);
        intent.putExtra(LocalNotificationReceiver.TITLE_EXTRA, title);
        intent.putExtra(LocalNotificationReceiver.TEXT_EXTRA, msg);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(),
                REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        // schedule
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, MORNING_NOTIFICATION_TIME_HOUR);
        calendar.set(Calendar.MINUTE, MORNING_NOTIFICATION_TIME_MINUTE);
        calendar.set(Calendar.SECOND, 0);
        long alarmTime = calendar.getTimeInMillis();
        if (alarmTime < System.currentTimeMillis()) {
            alarmTime += AlarmManager.INTERVAL_DAY;
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, alarmIntent);
    }
}
