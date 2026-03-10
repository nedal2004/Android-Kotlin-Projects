package com.n.alian.androidlabservice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.n.alian.androidlabservice.databinding.ActivityNotificationChannelBinding

class NotificationChannel : AppCompatActivity() {
    lateinit var binding: ActivityNotificationChannelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotificationChannelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.note.setText(intent.getStringExtra("data"))
    }
}