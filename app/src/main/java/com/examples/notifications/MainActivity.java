package com.examples.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.examples.notifications.receiver.NotificationReceiver;
import com.examples.notifications.shcedule.NotificationWorker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    private static final String workTag = "notificationWork";

    private Button btnSimpleNotification;
    private Button btnActionNotification;
    private Button btnScheduledNotification;

    NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        bindUI();
    }

    private void bindUI() {

        btnSimpleNotification = findViewById(R.id.btnSimple);
        btnActionNotification = findViewById(R.id.btnAction);
        btnScheduledNotification = findViewById(R.id.btnScheduled);

        btnSimpleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification(createSimpleNotification());
            }
        });

        btnActionNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification(createActionNotification());
            }
        });

        btnScheduledNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleNotification();
            }
        });
    }

    private Notification createSimpleNotification() {

        String text = "This is a simple notification fmadsl kgsdkg nasfoas nfoaw nfgawnvg asoivnasov  nsanvas nvsakvn asovne ebvndskv";

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, App.CH_1);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Simple Notification")
                .setContentText(text)
                .setContentIntent(getSimplePendingIntent())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        return builder.build();
    }

    private Notification createActionNotification() {

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String text = "This is a simple notification fmadsl kgsdkg nasfoas nfoaw nfgawnvg asoivnasov  nsanvas nvsakvn asovne ebvndskv";

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, App.CH_1);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Simple Notification")
                .setContentText(text)
                .setContentIntent(getSimplePendingIntent())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.mipmap.ic_launcher, "Toast", pendingIntent)
                .setAutoCancel(true);

        return builder.build();
    }

    private void scheduleNotification() {

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .addTag(workTag)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance(this).enqueue(request);

        Toast.makeText(this, "Notification schedule success", Toast.LENGTH_SHORT).show();

    }

    private PendingIntent getSimplePendingIntent() {

        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void showNotification(Notification notification){

        notificationManager.notify(1001, notification);
    }
}
