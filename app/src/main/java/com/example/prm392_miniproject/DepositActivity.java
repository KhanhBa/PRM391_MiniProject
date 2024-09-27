package com.example.prm392_miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class DepositActivity extends AppCompatActivity {

    Button btnDeposit;
    TextView txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recharge);


        btnDeposit = (Button) findViewById(R.id.btn_deposit);
        txtNumber = (TextView) findViewById(R.id.et_deposit_amount);

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DepositActivity.this, MainActivity.class);
                Integer money = Integer.parseInt(txtNumber.getText().toString());
                Intent resultIntent = new Intent();
                resultIntent.putExtra("money", money);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
