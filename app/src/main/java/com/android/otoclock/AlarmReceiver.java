package com.android.otoclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mediaPlayer1;
    private AlertDialog alertDialog;
    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent != null) {
            Log.d("broadcast1","闹钟广播响应");
            Intent intent1 = new Intent(context,AlarmActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);

        }

    }
}
