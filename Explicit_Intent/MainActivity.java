package com.example.explicit;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    Button loginBtn;   // ✅ STEP 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // STEP 2: connect button
        loginBtn = findViewById(R.id.loginBtn);

        // STEP 3: explicit intent on click
        loginBtn.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);

        });
    }
}