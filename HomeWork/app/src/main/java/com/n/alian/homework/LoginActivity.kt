package com.n.alian.homework

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.n.alian.homework.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var islogin = R.drawable.ic_error
    lateinit var db: UserDatabase
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = UserDatabase(this)

        binding.btnLogin.setOnClickListener {
            val username = binding.tvUsername.text.toString()
            val password = binding.tvPassword.text.toString()
            if (username.toString().isNotEmpty() && password.toString().isNotEmpty()) {
                AsyncTaskLogin(username, password).execute()
            }

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

    inner class AsyncTaskLogin(val userName: String, val password: String) :
        AsyncTask<String, String, Boolean>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@LoginActivity)
            progressDialog.setMessage("Checking Password and User Name")
            progressDialog.setCancelable(false)
            progressDialog.show()

        }

        override fun doInBackground(vararg p0: String): Boolean {
            Thread.sleep(1500)
            return db.isLogIn(userName, password)

        }

        override fun onPostExecute(result: Boolean) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            if (result) {
                showNotification("Success", "Login Successfully")
                Toast.makeText(this@LoginActivity, "Login Successfully", Toast.LENGTH_SHORT).show()

            } else {
                showNotification("Login User", "Failed Login")
                Toast.makeText(this@LoginActivity, "Invalid Entered", Toast.LENGTH_SHORT).show()

            }
        }

    }
}