package com.n.alian.androidlabservice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.n.alian.androidlabservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            val i = Intent(this, MyService::class.java)

            i.putExtra("name", binding.editTextText.text.toString())
            startService(i)
        }
        binding.btnStop.setOnClickListener {
            val i = Intent(this, MyService::class.java)
            Log.e("hzm", "Service is stop")
            stopService(i)
        }
        binding.btnNote.setOnClickListener {
            val intent = Intent(this, NotificationChannel::class.java)
            intent.putExtra("data", "notification Lab")
            val manager = MyNotificationManager(this)
            manager.showSmallNotification(
                1,
                "Notification Lab",
                "Now We Learn How Create Notification Channel", intent
            )
        }


    }
}