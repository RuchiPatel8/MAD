package com.example.notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Changed channel ID to create a new channel with HIGH importance (old channel importance cannot be updated)
    private static final String CHANNEL_ID = "notification_channel_high_priority";
    private static final int PERMISSION_REQUEST_CODE = 100;

    private EditText etCustomTitle;
    private EditText etCustomMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createNotificationChannel();

        Button btnSimple = findViewById(R.id.btnSimpleNotification);
        Button btnCustom = findViewById(R.id.btnCustomNotification);
        etCustomTitle = findViewById(R.id.etCustomTitle);
        etCustomMessage = findViewById(R.id.etCustomMessage);

        btnSimple.setOnClickListener(v -> checkPermissionAndSendNotification(false));
        btnCustom.setOnClickListener(v -> checkPermissionAndSendNotification(true));
    }

    private void checkPermissionAndSendNotification(boolean isCustom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, PERMISSION_REQUEST_CODE);
                return;
            }
        }
        if (isCustom) {
            sendCustomNotification();
        } else {
            sendSimpleNotification();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "High Priority Channel";
            String description = "Channel for pop-up notifications";
            // Setting importance to HIGH to allow Heads-Up (pop-up) notifications
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void sendSimpleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Simple Notification")
                .setContentText("This is a simple notification example.")
                // Set priority HIGH for Android 7.1 and lower
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, builder.build());
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
             notificationManager.notify(1, builder.build());
        }
    }

    private void sendCustomNotification() {
        String title = etCustomTitle.getText().toString();
        String message = etCustomMessage.getText().toString();

        if (title.isEmpty()) title = "Default Custom Title";
        if (message.isEmpty()) message = "Default Custom Message";

        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.custom_notification);
        customView.setTextViewText(R.id.notification_title, title);
        customView.setTextViewText(R.id.notification_text, message);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(customView)
                // Set priority HIGH for Android 7.1 and lower
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(2, builder.build());
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
             notificationManager.notify(2, builder.build());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can ask the user to click the button again.
                Toast.makeText(this, "Permission granted. Try again.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notification permission required.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}