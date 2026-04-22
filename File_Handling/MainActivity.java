package com.example.file_handling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etRollNo, etEmail, etPhone;
    private Button btnSubmit, btnLoad;
    private TableLayout tlData;
    private final String FILE_NAME = "mydata.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etName = findViewById(R.id.etName);
        etRollNo = findViewById(R.id.etRollNo);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLoad = findViewById(R.id.btnLoad);
        tlData = findViewById(R.id.tlData);

        // Submit Button Click Listener
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        // Load Button Click Listener
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void saveData() {
        String name = etName.getText().toString().trim();
        String roll = etRollNo.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (name.isEmpty() || roll.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String data = name + "," + roll + "," + email + "," + phone + "\n";

        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();

            etName.setText("");
            etRollNo.setText("");
            etEmail.setText("");
            etPhone.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        tlData.removeAllViews();
        addTableHeader();

        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    addTableRow(parts[0], parts[1], parts[2], parts[3]);
                }
            }
            reader.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "No data found or error reading file", Toast.LENGTH_SHORT).show();
        }
    }

    private void addTableHeader() {
        TableRow headerRow = new TableRow(this);
        headerRow.setBackgroundColor(0xFFCCCCCC);
        headerRow.setPadding(5, 5, 5, 5);

        headerRow.addView(createTextView("Name", true));
        headerRow.addView(createTextView("Roll No", true));
        headerRow.addView(createTextView("Email", true));
        headerRow.addView(createTextView("Phone", true));

        tlData.addView(headerRow);
    }

    private void addTableRow(String name, String roll, String email, String phone) {
        TableRow row = new TableRow(this);
        row.setPadding(5, 5, 5, 5);

        row.addView(createTextView(name, false));
        row.addView(createTextView(roll, false));
        row.addView(createTextView(email, false));
        row.addView(createTextView(phone, false));

        tlData.addView(row);
    }

    private TextView createTextView(String text, boolean isHeader) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(10, 10, 10, 10);
        if (isHeader) {
            tv.setTypeface(null, android.graphics.Typeface.BOLD);
        }
        return tv;
    }
}