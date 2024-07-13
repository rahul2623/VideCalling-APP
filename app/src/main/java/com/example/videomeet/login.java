package com.example.videomeet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    FirebaseAuth auth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        auth1 = FirebaseAuth.getInstance();

        // Set click listener for the login button
        buttonLogin.setOnClickListener(view -> {
            String email1 = editTextEmail.getText().toString().trim();
            String password1 = editTextPassword.getText().toString().trim();

            // For demonstration, display a toast message with entered data
            login1(email1, password1);
        });
    }

    private void login1(String email, String password) {
        auth1.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(this, authResult -> {
                    Toast.makeText(login.this, "Login In Successful", Toast.LENGTH_SHORT).show();
                    Intent n = new Intent(login.this, MainActivity.class);
                    startActivity(n);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(login.this, "Login Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}