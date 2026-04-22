package com.example.intent;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    // Youtube
    public void openYoutube(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(android.net.Uri.parse("https://www.youtube.com"));
        startActivity(intent);
    }

    // Chrome
    public void openChrome(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(android.net.Uri.parse("https://www.google.com"));
        startActivity(intent);
    }

    // Camera
    public void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    // Contacts
    public void openContacts(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android.cursor.dir/contact");
        startActivity(intent);
    }
}