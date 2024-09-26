package com.example.prm392_miniproject;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtNewUsername, edtNewPassword, edtConfirm;
    private Button btnSignUp;
    private TextView tvLogin;
    private ImageView ivEye1, ivEye;
    private boolean isPasswordVisible1 = false;
    private boolean isPasswordVisible2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtNewUsername = findViewById(R.id.edtNewUsername);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirm = findViewById(R.id.edtConfirm);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLogin = findViewById(R.id.tvLogin);
        ivEye1 = findViewById(R.id.ivEye1);
        ivEye = findViewById(R.id.ivEye);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToLogin();
            }
        });

        ivEye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(edtNewPassword, ivEye1, isPasswordVisible1);
                isPasswordVisible1 = !isPasswordVisible1;
            }
        });

        ivEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(edtConfirm, ivEye, isPasswordVisible2);
                isPasswordVisible2 = !isPasswordVisible2;
            }
        });
    }

    private void signUp() {
        String username = edtNewUsername.getText().toString().trim();
        String password = edtNewPassword.getText().toString().trim();
        String confirmPassword = edtConfirm.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            edtNewUsername.setError("Username is required");
            edtNewUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtNewPassword.setError("Password is required");
            edtNewPassword.requestFocus();
            return;
        } else if (password.length() < 6) {
            edtNewPassword.setError("Password must be at least 6 characters long");
            edtNewPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            edtConfirm.setError("Passwords do not match");
            edtConfirm.requestFocus();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        if (dbHelper.checkIfUserExists(username)) {
            edtNewUsername.setError("Username already exists");
            edtNewUsername.requestFocus();
            return;
        }
        boolean isInserted = dbHelper.addUser(username, password);
        if (isInserted) {
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
            navigateToLogin();
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void togglePasswordVisibility(EditText editText, ImageView imageView, boolean isPasswordVisible) {
        if (isPasswordVisible) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imageView.setImageResource(R.drawable.baseline_remove_red_eye_24);
        } else {
            editText.setTransformationMethod(null);
        }
        editText.setSelection(editText.getText().length());
    }
}


