package com.example.videomeet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.logging.Logger;

public class One extends AppCompatActivity {
Button log,reg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_one);
        log=findViewById(R.id.log1);
        reg=findViewById(R.id.btreg);
        log.setOnClickListener(view -> {
            Intent a=new Intent(One.this, login.class);
            startActivity(a);
        });
        reg.setOnClickListener(view -> {
            Intent ab=new Intent(One.this, Register.class);
            startActivity(ab);
        });
    }
}