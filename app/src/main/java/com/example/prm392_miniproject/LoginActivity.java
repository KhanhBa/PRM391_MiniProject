package com.example.prm392_miniproject;

import android.content.Intent;
import android.media.MediaPlayer;
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

import com.example.prm392_miniproject.Object.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnSignIn;
    private TextView tvSignUp;
    private ImageView ivEye1;
    private boolean isPasswordVisible = false;
    private MediaPlayer media;
    // Hard-coded users list
    private List<User> hardCodedUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        media = MediaPlayer.create(this,R.raw.game_music_loop_3);
        media.setLooping(true);
        media.start();
        // Initialize views
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUp = findViewById(R.id.tvSignUp);
        ivEye1 = findViewById(R.id.ivEye1);

        // Initialize hard-coded users
        hardCodedUsers = new ArrayList<>();
        hardCodedUsers.add(new User("user1", "123456", 100));
        hardCodedUsers.add(new User("user2", "123456", 200));
        hardCodedUsers.add(new User("user3", "123456", 300));

        // Set click listener for the Sign In button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        // Set click listener for the Sign Up text
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToSignUp();
            }
        });

        // Set click listener for toggling password visibility
        ivEye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility();
            }
        });
    }

    private void signIn() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        // Basic input validation
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError("Username is required");
            edtUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }

        // Check against hard-coded users first
        for (User user : hardCodedUsers) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                // Navigate to the main activity after successful login
                navigateToMain();
                return;
            }
        }

        // If not found in hard-coded users, check the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if (dbHelper.checkUser(username, password)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            // Navigate to the main activity after successful login
            navigateToMain();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ivEye1.setImageResource(R.drawable.baseline_remove_red_eye_24);
        } else {
            edtPassword.setTransformationMethod(null);
        }
        edtPassword.setSelection(edtPassword.getText().length());
        isPasswordVisible = !isPasswordVisible;
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void navigateToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
