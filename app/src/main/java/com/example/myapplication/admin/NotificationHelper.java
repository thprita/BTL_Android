package com.example.myapplication.admin;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.example.myapplication.R;
// Class NotificationHelper
public class NotificationHelper {
    private static final String CHANNEL_ID = "My_Channel_ID";
    private static final String CHANNEL_NAME = "My_Channel";
    private static final int NOTIFICATION_ID = 1;

    public static void showNotification(Context context, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Tạo channel cho notification (chỉ cần làm một lần)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        // Tạo notification
        Notification.Builder builder = new Notification.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.thongbao) // Icon của thông báo
                .setContentTitle(title) // Tiêu đề của thông báo
                .setContentText(message) // Nội dung của thông báo
                .setAutoCancel(true); // Tự động huỷ thông báo khi được nhấp

        // Hiển thị notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

