package com.example.prm392_miniproject;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private SeekBar runner1, runner2, runner3;
    private EditText etCoc1, etCoc2, etCoc3;
    private Handler handler;
    private TextView money;
    private int progressValue1, progressValue2, progressValue3, winnerIndex = 0;
    private boolean isRunning, hasWinner = false;
    private int moneyInWallet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        runner1 = findViewById(R.id.Runner1);
        runner2 = findViewById(R.id.Runner2);
        runner3 = findViewById(R.id.Runner3);
        etCoc1 = findViewById(R.id.etCoc1);
        etCoc2 = findViewById(R.id.etCoc2);
        etCoc3 = findViewById(R.id.etCoc3);
        money = findViewById(R.id.txtMoney);
        moneyInWallet = Integer.parseInt(money.getText().toString());
        handler = new Handler();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int coc1 = getMoney(etCoc1);
                int coc2 = getMoney(etCoc2);
                int coc3 = getMoney(etCoc3);

                int sum = coc1 + coc2 + coc3;

                if (sum > 0 && sum <= moneyInWallet) {
                    if (!isRunning) {
                        moneyInWallet -= sum;
                        money.setText(String.valueOf(moneyInWallet));
                        loadAction();
                        startAction();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Hourses are already running", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadAction() {
        isRunning = false;
        progressValue1 = 0;
        progressValue2 = 0;
        progressValue3 = 0;
        hasWinner = false;
        winnerIndex = 0;
    }

    private void startAction() {
        isRunning = true;

        TurnOnOffFee(false);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (progressValue1 < 100) {
                    runner1.setProgress(progressValue1);
                    progressValue1 = progressValue1 + random();
                } else if (!hasWinner) {
                    onFirstRunnerFinished(1);
                    showWinDialog("Blue Horse");
                }

                if (progressValue2 < 100) {
                    runner2.setProgress(progressValue2);
                    progressValue2 = progressValue2 + random();
                } else if (!hasWinner) {
                    onFirstRunnerFinished(2);
                    showWinDialog("Red Horse");
                }

                if (progressValue3 < 100) {
                    runner3.setProgress(progressValue3);
                    progressValue3 = progressValue3 + random();
                } else if (!hasWinner) {
                    onFirstRunnerFinished(3);
                    showWinDialog("Yellow Horse");
                }

                if (progressValue1 < 100 || progressValue2 < 100 || progressValue3 < 100) {
                    handler.postDelayed(this, 100);
                } else {
                    isRunning = false;
                }
            }
        };
        handler.post(runnable);
    }

    private int random() {
        Random random = new Random();
        return 2 + random.nextInt(2);
    }

    private void onFirstRunnerFinished(int i) {
        isRunning = false;
        handler.removeCallbacksAndMessages(null);
        if (!hasWinner) {
            hasWinner = true;
            winnerIndex = i;
            int coc1 = getMoney(etCoc1);
            int coc2 = getMoney(etCoc2);
            int coc3 = getMoney(etCoc3);
            switch (winnerIndex) {
                case 1:
                    moneyInWallet += (coc1 * 2);
                    break;
                case 2:
                    moneyInWallet += (coc2 * 2);
                    break;
                case 3:
                    moneyInWallet += (coc3 * 2);
                    break;
            }
            money.setText(String.valueOf(moneyInWallet));

            TurnOnOffFee(true);

        }
    }

    private int getMoney(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    private void TurnOnOffFee(boolean value){
        etCoc1.setEnabled(value);
        etCoc3.setEnabled(value);
        etCoc2.setEnabled(value);
    }

    private void showWinDialog(String s) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_race_result);

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }

        TextView messageTextView = dialog.findViewById(R.id.tvWinnerName);
        messageTextView.setText(s);

        Button closeButton = dialog.findViewById(R.id.btnContinue);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
