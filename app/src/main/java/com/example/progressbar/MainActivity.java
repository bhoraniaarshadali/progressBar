package com.example.progressbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    // Define the progress values
    public static final int[] progressValues = {0, 20, 60, 80, 100};
    private int currentProgressIndex = 0; // Index to track current progress

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        progressBar = findViewById(R.id.progressBar);
        Button backButton = findViewById(R.id.backButton);
        Button nextButton = findViewById(R.id.nextButton);
        Button clearAllButton = findViewById(R.id.clearAll);
        Button showStatusButton = findViewById(R.id.GoBack);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Get the saved progress index (0 by default)
        currentProgressIndex = sharedPreferences.getInt("progressIndex", 0);

        // Set the initial progress based on the current progress index
        progressBar.setProgress(progressValues[currentProgressIndex]);

        // Set an onClickListener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentProgressIndex > 0) {
                    currentProgressIndex--; // Decrease the progress index
                    progressBar.setProgress(progressValues[currentProgressIndex]);
                    saveProgressIndex(currentProgressIndex);
                }
            }
        });

        // Set an onClickListener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentProgressIndex < progressValues.length - 1) {
                    currentProgressIndex++; // Increase the progress index
                    progressBar.setProgress(progressValues[currentProgressIndex]);
                    saveProgressIndex(currentProgressIndex);
                } else {
                    Toast.makeText(MainActivity.this, "Progress is already at 100%", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set an onClickListener for the Clear All button
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentProgressIndex = 0;
                progressBar.setProgress(progressValues[currentProgressIndex]);
                saveProgressIndex(currentProgressIndex);
                Toast.makeText(MainActivity.this, "Status cleared", Toast.LENGTH_SHORT).show();
            }
        });

        // Set an onClickListener for the Show Status button
        showStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProgressStatusActivity.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to save progress index using SharedPreferences
    private void saveProgressIndex(int index) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("progressIndex", index);
        editor.apply();
    }
}
