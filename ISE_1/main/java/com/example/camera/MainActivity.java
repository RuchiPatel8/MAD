package com.example.camera;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RatingBar ratingBar;
    TextView txtRating;

    // Camera Launcher
    ActivityResultLauncher<Void> cameraLauncher;

    // Permission Launcher
    ActivityResultLauncher<String> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);
        ratingBar = findViewById(R.id.ratingBar);
        txtRating = findViewById(R.id.txtRating);

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) ->
                txtRating.setText("Rating of the above image is " + rating));

        // Register Camera Launcher
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicturePreview(),
                bitmap -> {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);

                        // Reset rating
                        ratingBar.setRating(0);
                        txtRating.setText("Rating of the above image is 0");
                    }
                }
        );

        // Register Permission Launcher
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();
                        cameraLauncher.launch(null);
                    } else {
                        Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void openCamera(View view) {
        if (checkSelfPermission(Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(null);
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }
}