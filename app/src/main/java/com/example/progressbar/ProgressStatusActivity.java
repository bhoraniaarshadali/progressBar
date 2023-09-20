package com.example.progressbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressStatusActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_status);

        progressBar = findViewById(R.id.progressStatusBar);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close this activity and go back to the previous one (MainActivity)
            }
        });

        // Retrieve progress status from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int progressStatus = prefs.getInt("progressStatus", 0);

        // Set the progress of the ProgressBar
        progressBar.setProgress(progressStatus);
    }
}
