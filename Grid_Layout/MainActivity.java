package com.example.grid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);

        btn1.setOnClickListener(v -> showNumber("1"));
        btn2.setOnClickListener(v -> showNumber("2"));
        btn3.setOnClickListener(v -> showNumber("3"));
        btn4.setOnClickListener(v -> showNumber("4"));
        btn5.setOnClickListener(v -> showNumber("5"));
        btn6.setOnClickListener(v -> showNumber("6"));
    }

    private void showNumber(String number) {
        Toast.makeText(this, "Pressed " + number, Toast.LENGTH_SHORT).show();
    }
}