package com.example.inputcontrols;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    RadioGroup radioGroup;
    CheckBox hobby1, hobby2, hobby3, hobby4, hobby5;
    ToggleButton toggleBtn;
    RatingBar ratingBar;
    ProgressBar progressBar, circularProgress;
    Button submitBtn;

    int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        editName = findViewById(R.id.editName);
        radioGroup = findViewById(R.id.radioGroup);

        hobby1 = findViewById(R.id.hobby1);
        hobby2 = findViewById(R.id.hobby2);
        hobby3 = findViewById(R.id.hobby3);
        hobby4 = findViewById(R.id.hobby4);
        hobby5 = findViewById(R.id.hobby5);

        toggleBtn = findViewById(R.id.toggleBtn);
        ratingBar = findViewById(R.id.ratingBar);

        progressBar = findViewById(R.id.progressBar);
        circularProgress = findViewById(R.id.circularProgress);

        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(view -> {

            // Validation (optional but good)
            String name = editName.getText().toString().trim();
            if (name.isEmpty()) {
                editName.setError("Please enter your name");
                return;
            }

            circularProgress.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            progressStatus = 0;

            int selectedId = radioGroup.getCheckedRadioButtonId();
            final String gender;
            if (selectedId != -1) {
                RadioButton rb = findViewById(selectedId);
                gender = rb.getText().toString();
            } else {
                gender = "Not selected";
            }

            final String hobbies =
                    (hobby1.isChecked() ? "Dance " : "") +
                            (hobby2.isChecked() ? "Music " : "") +
                            (hobby3.isChecked() ? "Travel " : "") +
                            (hobby4.isChecked() ? "Reading " : "") +
                            (hobby5.isChecked() ? "Sports " : "");

            String updates = toggleBtn.isChecked() ? "Yes" : "No";

            float rating = ratingBar.getRating();
            String ratingText = String.format("%.1f", rating); // ⭐ IMPORTANT CHANGE

            new Thread(() -> {
                while (progressStatus < 100) {
                    progressStatus += 10;

                    runOnUiThread(() -> progressBar.setProgress(progressStatus));

                    try {
                        Thread.sleep(200);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(() -> {
                    circularProgress.setVisibility(View.GONE);

                    Toast.makeText(MainActivity.this,
                            "Name: " + name +
                                    "\nGender: " + gender +
                                    "\nHobbies: " + hobbies +
                                    "\nReceive Updates: " + updates +
                                    "\nRating: " + ratingText,
                            Toast.LENGTH_LONG).show();
                });

            }).start();
        });
    }
}