package com.n.alian.homework

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.n.alian.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var islogin = R.drawable.ic_error

    lateinit var binding: ActivityMainBinding
    lateinit var db: UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = UserDatabase(this)
        binding.btnSignup.setOnClickListener {
            if (binding.idUsername.text.isNotEmpty() && binding.idPass.text.isNotEmpty()) {
                db = UserDatabase(this)
                val user = Users(
                    userName = binding.idUsername.text.toString(),
                    email = binding.idEmail.text.toString(),
                    phone = binding.idPhone.text.toString().toInt(),
                    password = binding.idPass.text.toString()
                )
                if (db.insertUser(user)) {
                    showNotification("Success", "User registered successfully")
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                } else
                    showNotification("Error", "Registration failed")
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.idLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }

    private fun showNotification(title: String, message: String) {
        val channelId = "user_notification_channel"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "User Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        if (title.equals("Success")) {
            islogin = R.drawable.ic_success
        } else {
            islogin = R.drawable.ic_error
        }
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(islogin)
            .build()

        notificationManager.notify(1, notification)
    }

}
