package com.example.progressbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button backButton;
    private Button nextButton;
    private Button clearAllButton;
    private Button showStatusButton;
    private SharedPreferences sharedPreferences;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        backButton = findViewById(R.id.backButton);
        nextButton = findViewById(R.id.nextButton);
        clearAllButton = findViewById(R.id.clearAll);
        showStatusButton = findViewById(R.id.GoBack);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Get the saved progress value
        progress = sharedPreferences.getInt("progress", 0);
        progressBar.setProgress(progress);

        // Set an onClickListener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progress > 0) {
                    progress -= 20; // Decrease progress by 20%
                    progressBar.setProgress(progress);
                    saveProgress(progress);
                }
            }
        });

        // Set an onClickListener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progress < 100) {
                    progress += 20; // Increase progress by 20%
                    progressBar.setProgress(progress);
                    saveProgress(progress);
                } else {
                    Toast.makeText(MainActivity.this, "Progress is already at 100%", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set an onClickListener for the Clear All button
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 0;
                progressBar.setProgress(progress);
                saveProgress(progress);
                Toast.makeText(MainActivity.this, "Status cleared", Toast.LENGTH_SHORT).show();
            }
        });

        // Set an onClickListener for the Show Status button
        showStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Status: " + progress + "%", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helper method to save progress using SharedPreferences
    private void saveProgress(int progress) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("progress", progress);
        editor.apply();
    }
}
