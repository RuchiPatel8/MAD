package com.example.arithmetic;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button addBtn, subBtn, mulBtn, divBtn;
    TextView resultText;

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

        // Link UI with Java
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        addBtn = findViewById(R.id.addBtn);
        subBtn = findViewById(R.id.subBtn);
        mulBtn = findViewById(R.id.mulBtn);
        divBtn = findViewById(R.id.divBtn);
        resultText = findViewById(R.id.resultText);

        // Button Clicks
        addBtn.setOnClickListener(v -> calculate("+"));
        subBtn.setOnClickListener(v -> calculate("-"));
        mulBtn.setOnClickListener(v -> calculate("*"));
        divBtn.setOnClickListener(v -> calculate("/"));
    }

    private void calculate(String op) {

        if(num1.getText().toString().isEmpty() || num2.getText().toString().isEmpty()){
            resultText.setText("Enter both numbers");
            return;
        }

        double n1 = Double.parseDouble(num1.getText().toString());
        double n2 = Double.parseDouble(num2.getText().toString());
        double result = 0;

        switch (op){
            case "+":
                result = n1 + n2;
                break;

            case "-":
                result = n1 - n2;
                break;

            case "*":
                result = n1 * n2;
                break;

            case "/":
                if(n2 == 0){
                    resultText.setText("Cannot divide by zero");
                    return;
                }
                result = n1 / n2;
                break;
        }

        resultText.setText("Result = " + result);
    }
}