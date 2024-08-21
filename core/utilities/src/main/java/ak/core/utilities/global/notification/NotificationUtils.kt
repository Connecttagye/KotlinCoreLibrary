package com.core.utils.utilities.notification

import android.app.NotificationManager
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.app.NotificationCompat
import kotlin.random.Random

object NotificationUtils {

    fun showSimpleNotification(context: Context) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notificationChannelID = "notification_channel_id"

        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle("Simple Notification")
            .setContentText("Message or text with notification")
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()  // finalizes the creation

        notificationManager.notify(Random.nextInt(), notification)
    }
}