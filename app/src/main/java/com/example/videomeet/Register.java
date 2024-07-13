package com.example.videomeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button buttonRegister;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        auth=FirebaseAuth.getInstance();
        // Set click listener for the register button
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle registration logic (e.g., validate input, save user data)
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                regis(email,password);
                // For demonstration, you can add code to save user data to a database or server

                // Assuming registration is successful, navigate to LoginActivity
                // Optional: Close the RegisterActivity to prevent going back to it using the back button
                regis(email,password);
            }
        });
    }

    public void regis(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "You are registered", Toast.LENGTH_SHORT).show();
                            // Optional: Navigate to another activity after successful registration
                            startActivity(new Intent(Register.this, login.class));
                            finish(); // Optional: Close the RegisterActivity
                        } else {
                            Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}