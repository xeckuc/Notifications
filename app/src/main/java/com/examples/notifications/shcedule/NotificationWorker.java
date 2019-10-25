package com.examples.notifications.shcedule;

import android.content.Context;

import com.examples.notifications.App;
import com.examples.notifications.R;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationWorker extends Worker {

    private Context context;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {

        triggerNotification();

        return Result.success();
    }

    private void triggerNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, App.CH_1);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Scheduled")
                .setContentText("This notification was scheduled");

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(10000, builder.build());
    }
}
