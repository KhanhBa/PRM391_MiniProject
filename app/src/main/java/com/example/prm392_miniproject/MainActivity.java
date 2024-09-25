package com.example.prm392_miniproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private SeekBar runner1, runner2, runner3;
    private EditText etCoc1, etCoc2, etCoc3;
    private Handler handler;
    private int progressValue1, progressValue2, progressValue3 = 0;
    private boolean isRunning,hasWinner = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use your actual layout file name

        // Initialize views
        btnStart = findViewById(R.id.btnStart);
        runner1 = findViewById(R.id.Runner1);
        runner2 = findViewById(R.id.Runner2);
        runner3 = findViewById(R.id.Runner3);
        etCoc1 = findViewById(R.id.etCoc1);
        etCoc2 = findViewById(R.id.etCoc2);
        etCoc3 = findViewById(R.id.etCoc3);
        handler = new Handler();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    isRunning = true;
                    progressValue1 = 0;
                    progressValue2 = 0;
                    progressValue3 = 0;
                    hasWinner= false;
                    startAction();
                }
            }
        });
    }

        private void startAction () {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (progressValue1 < 100) {
                        runner1.setProgress(progressValue1);
                        progressValue1 = progressValue1 + random();
                    }
                    else{
                        onFirstRunnerFinished(1);

                    }
                    if (progressValue2 < 100) {
                        runner2.setProgress(progressValue2);
                        progressValue2 = progressValue1 + random();
                    }
                    else{
                        onFirstRunnerFinished(2);
                        hasWinner=true;
                    }
                    if (progressValue3 < 100) {
                        runner3.setProgress(progressValue3);
                        progressValue3 = progressValue3 + random();
                    }
                    else{
                        onFirstRunnerFinished(3);
                        hasWinner=true;
                    }
                    if (progressValue1 <= 100 || progressValue2 <= 100 || progressValue3 <= 100) {
                        handler.postDelayed(this, 100);
                    } else {
                        isRunning = false;
                    }
                }
            };
            handler.post(runnable);
        }
        private int random () {
            Random random = new Random();
            return 2 + random.nextInt(2);
        }
    private void onFirstRunnerFinished(int i) {
        isRunning = false;
        handler.removeCallbacksAndMessages(null);
        if (!hasWinner) {
            Toast.makeText(MainActivity.this, "Runner " + i + " win", Toast.LENGTH_SHORT).show();
        }
    }
}