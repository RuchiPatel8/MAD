package com.example.absolute_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        btn1.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Button 1 Clicked", Toast.LENGTH_SHORT).show());

        btn2.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Button 2 Clicked", Toast.LENGTH_SHORT).show());

        btn3.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Button 3 Clicked", Toast.LENGTH_SHORT).show());
    }
}