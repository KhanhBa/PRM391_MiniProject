package com.example.prm392_miniproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_miniproject.Object.User;

import java.util.List;
import java.util.Optional;

public class LoginActivity extends AppCompatActivity {
    List<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        list.add(new User("user1","123456",100));
        list.add(new User("user2","123456",200));
        list.add(new User("user3","123456",300));
        // lam` trang login cho 3 tai khoan nay`
        Optional<User> check = list.stream().filter(s->s.getUserName()=="user1" && s.getPassword()=="123456").findFirst();
    }
}
