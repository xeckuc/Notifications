package com.examples.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CH_1 = "Channel 1";
    public static final String CH_2 = "Channel 2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence ch1Name = getString(R.string.channel_1_name);
            CharSequence ch2Name = getString(R.string.channel_2_name);

            String ch1Description = getString(R.string.channel_1_desc);
            String ch2Description = getString(R.string.channel_2_desc);

            int ch1Importance = NotificationManager.IMPORTANCE_HIGH;
            int ch2Importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel1 =
                    new NotificationChannel(
                            CH_1, ch1Name, ch1Importance
                    );

            notificationChannel1.setDescription(ch1Description);

            NotificationChannel notificationChannel2 =
                    new NotificationChannel(
                            CH_2, ch2Name, ch2Importance
                    );

            notificationChannel2.setDescription(ch2Description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel1);
            notificationManager.createNotificationChannel(notificationChannel2);

        }

    }
}
