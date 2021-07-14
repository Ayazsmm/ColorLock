package com.example.advancelock;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenOnService extends Service {
    ScreenOnReceiver receiver = new ScreenOnReceiver();

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter i = new IntentFilter(Intent.ACTION_USER_PRESENT);
        registerReceiver(receiver, i);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}