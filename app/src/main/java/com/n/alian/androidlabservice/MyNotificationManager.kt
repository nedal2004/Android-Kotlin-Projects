package com.n.alian.androidlabservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class MyNotificationManager(var context: Context) {
    private val ID_NOTIFICATION = 200
    private val channel_id = "channel_id"
    fun showSmallNotification(
        id: Int,
        title: String,
        message: String,
        intent: Intent
    ) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            ID_NOTIFICATION,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(context, channel_id)
        var notification: Notification
        notification =
            builder.setSmallIcon(R.drawable.ic_notifications).setContentIntent(pendingIntent)
                .setContentText(message).setContentTitle(title).setAutoCancel(false)
                .setPriority(NotificationManager.IMPORTANCE_HIGH).build()
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channel_id, "Channel Noti", NotificationManager.IMPORTANCE_HIGH)
            channel.setShowBadge(true)
            channel.description = message
            channel.enableLights(true)
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, notification)
    }

    fun getNotificationId(): Int {
        return ID_NOTIFICATION
    }

    fun getNotification(context: Context): Notification {
        val channel_id = "Channel Lab"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val name: CharSequence = "foreground Service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel =
                NotificationChannel(channel_id, name, importance)
            channel.setShowBadge(true)
            channel.description = "Foreground Service is running"
            channel.enableLights(false)
            channel.enableVibration(false)
            channel.setSound(null, null)
            assert(manager != null)
            manager.createNotificationChannel(channel)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
            val builder =
                Notification.Builder(context, channel_id)
            builder.setContentTitle("Foreground Service")
                .setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setAutoCancel(true)
            val notification = builder.build()
            return notification


        } else {
            val pendingIntent = PendingIntent.getActivity(
                context, 0, Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
            val builder = NotificationCompat.Builder(context)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            return builder.build()


        }
    }
}