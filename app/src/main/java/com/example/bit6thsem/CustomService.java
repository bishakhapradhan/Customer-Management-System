package com.example.bit6thsem;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomService extends Service {

    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        try{
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            Toast.makeText(this, "Service Started Successfully !", Toast.LENGTH_SHORT).show();
        } catch (Exception exc){
            exc.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service stopped Successfully !", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
    }
}
