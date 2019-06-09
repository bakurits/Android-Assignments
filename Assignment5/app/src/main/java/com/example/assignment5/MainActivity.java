package com.example.assignment5;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import static com.example.assignment5.Constants.NOTIFICATION_CHANNEL_ID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Constants.MAIN_ACTION.equals(getIntent().getAction())) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "Channel F name";
                String description = "This is a description";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
                channel.setDescription(description);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
            Intent startIntent = new Intent(this, PlayMusicService.class);
            startIntent.setAction(Constants.INIT_FOREGROUND_ACTION);
            startService(startIntent);
        }

        ImageButton button = findViewById(R.id.play_pause);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(MainActivity.this, PlayMusicService.class);
                stopIntent.setAction(Constants.STOP_FOREGROUND_ACTION);
                startService(stopIntent);
                finish();
            }
        });


    }

}
