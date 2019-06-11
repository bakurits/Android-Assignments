package com.example.assignment5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            Intent startIntent = new Intent(context, PlayMusicService.class);
            startIntent.setAction(Constants.START_FOREGROUND_ACTION);
            context.startService(startIntent);
        }

    }
}

