package com.example.lightsout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailInput, passwordInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (email.equals("admin@gmail.com") && password.equals("12345")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("username", "Alex");
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}