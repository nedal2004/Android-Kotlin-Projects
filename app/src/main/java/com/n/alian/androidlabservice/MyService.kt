package com.n.alian.androidlabservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    lateinit var mediaPlayer: MediaPlayer
    lateinit var myNotificationManager: MyNotificationManager

    override fun onCreate() {
        super.onCreate()
        myNotificationManager = MyNotificationManager(this)
        
        myNotificationManager = MyNotificationManager(this)
        Log.e("hzm", "onCreate")
        mediaPlayer = MediaPlayer.create(this, R.raw.fff)
        mediaPlayer.isLooping = true
        startForeground(
            myNotificationManager.getNotificationId(),
            myNotificationManager.getNotification(this)
        )

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("hzm", "onStartCommand")
        mediaPlayer.start()
        var i = 0
        while (i <= 10) {
            Thread.sleep(1000)
            Log.e("hzm", "Time is $i your name is ${intent?.getStringExtra("name")}")
            i++

        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
