package com.example.pjte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private String channelId = "mynotify";
    private String channeName = "mynotifyname";
    private Bitmap mBitmap;
    private NotificationManagerCompat mNotificationManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: . test log");
        findViewById(R.id.show_noify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommon();
            }
        });
        //        int x = 1080/0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: . test log");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: . test log");
        initChannel();

    }

    private void initChannel() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        mNotificationManager = NotificationManagerCompat.from(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// 8.0 适配
            NotificationChannel channel = new NotificationChannel(channelId, channeName, NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }

    }

    private void showCommon() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        this, channelId);
        Intent intent = new Intent(
                this, NotifactionInfo.class);
        int flag = PendingIntent.FLAG_UPDATE_CURRENT;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flag = flag | PendingIntent.FLAG_IMMUTABLE;
        }
        intent.putExtra("info", System.currentTimeMillis());
        PendingIntent
                pendingIntent = PendingIntent.getActivity(this, 0,
                intent, flag);
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "继续阅读", pendingIntent)
                .addAction(R.drawable.ic_launcher_background, "领金币", pendingIntent)
                .setContentTitle("水浒传")
                .setSubText("阅读提示")
//                .setSettingsText("setting")
//                .setContentInfo("conteninfo ")
                .setContentText("第三十回 景阳冈武松打虎 \n" )
                .setColor(Color.rgb(0, 0, 255))
//                .setStyle(new NotificationCompat.BigTextStyle())
//                .setStyle(new NotificationCompat.InboxStyle())
//                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
//                .setStyle(new NotificationCompat.BigPictureStyle())
                .setLargeIcon(mBitmap)
                .setSmallIcon(R.drawable.ic_launcher_background);

        Notification notification = builder.build();
        if (ActivityCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mNotificationManager.notify(888, notification);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: . test log");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: . test log");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: . test log");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: . test log");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: . test log");
    }


    //

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(888);
        }
    }


}