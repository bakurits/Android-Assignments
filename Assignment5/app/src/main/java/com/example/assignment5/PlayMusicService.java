package com.example.assignment5;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import static com.example.assignment5.Constants.FOREGROUND_SERVICE;
import static com.example.assignment5.Constants.NEXT_ACTION;
import static com.example.assignment5.Constants.NOTIFICATION_CHANNEL_ID;
import static com.example.assignment5.Constants.PREV_ACTION;

public class PlayMusicService extends Service {


    private NotificationCompat.Builder notification;

    private MediaPlayer player;
    private int[] musicFiles;
    private int currentMusic;
    AirplaneModeBroadcastReceiver receiver;

    public PlayMusicService() {
        musicFiles = new int[]{R.raw.tone1, R.raw.tone2};
        currentMusic = 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        if (receiver == null) receiver = new AirplaneModeBroadcastReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() == null) return START_STICKY;
        switch (intent.getAction()) {
            case Constants.START_FOREGROUND_ACTION:
                startPlayingWithNotification();
                break;
            case Constants.STOP_FOREGROUND_ACTION:
                stopForeground(true);
                releasePlayer();
                break;
            case PREV_ACTION:
                playPrevious();
                break;
            case NEXT_ACTION:
                playNext();
                break;
            default:
                return START_STICKY;
        }
        return START_STICKY;
    }

    private void startPlayingWithNotification() {
        Intent mainNotificationIntent = new Intent(this, MainActivity.class);
        mainNotificationIntent.setAction(Constants.MAIN_ACTION);
        mainNotificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent mainPangingIntent = PendingIntent.getActivity(this, 0, mainNotificationIntent, 0);


        Intent previouseIntent = new Intent(this, PlayMusicService.class);
        previouseIntent.setAction(PREV_ACTION);
        PendingIntent pendingIntent = PendingIntent.getService(this,
                0,
                previouseIntent,
                0);


        Intent nextIntent = new Intent(this, PlayMusicService.class);
        nextIntent.setAction(NEXT_ACTION);
        PendingIntent pendingIntent3 = PendingIntent.getService(this,
                0,
                nextIntent,
                0);


        NotificationCompat.Action prev = new NotificationCompat.Action.Builder(0, "Previous", pendingIntent).build();
        NotificationCompat.Action next = new NotificationCompat.Action.Builder(0, "Next", pendingIntent3).build();
        notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setAutoCancel(false)
                .setContentTitle("Music Player")
                .setTicker("Awesome ticker")
                .setContentText("Song is playing")
                .setOngoing(true)
                .setContentIntent(mainPangingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .addAction(prev)
                .addAction(next);


        startForeground(FOREGROUND_SERVICE, notification.build());

        currentMusic = 0;
        play();
    }

    void play() {
        releasePlayer();

        player = MediaPlayer.create(this, musicFiles[currentMusic]);
        player.setLooping(false);
        player.start();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(FOREGROUND_SERVICE, notification.build());

    }

    private void playPrevious() {
        currentMusic = (currentMusic - 1 + musicFiles.length) % musicFiles.length;
        play();
    }

    private void playNext() {
        currentMusic = (currentMusic + 1) % musicFiles.length;
        play();
    }

    private void releasePlayer() {
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onDestroy() {
        releasePlayer();
        player = null;
    }

}
