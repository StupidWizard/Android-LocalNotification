package com.example.localnotifyexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * This stupid code is created by thantieuhodo on 4/24/17.
 */
public class LocalNotificationReceiver extends BroadcastReceiver {
    public static final String INTENT_EXTRA = "from_local_notification_utils";

    public static final String TITLE_EXTRA = "TITLE_EXTRA";
    public static final String TEXT_EXTRA = "TEXT_EXTRA";

    @Override
    public void onReceive(Context context, Intent intent) {
        final Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, LocalNotificationUtils.REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String title = intent.getStringExtra(TITLE_EXTRA);
        if (title == null || title.length() <= 1) {
            PackageManager packageManager = context.getPackageManager();
            try {
                title = packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0)).toString();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        String msg = intent.getStringExtra(TEXT_EXTRA);
        if (msg == null || msg.length() <= 1) {
            // TODO - default msg = ??
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(msg);


        // TODO - icon???
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        } else {
            builder.setSmallIcon(R.mipmap.ic_launcher);
        }
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, builder.build());
    }


}
