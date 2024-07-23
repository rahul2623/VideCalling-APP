package com.example.videomeet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText codeBox;
    private Button button, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        codeBox = findViewById(R.id.codebox);
        button = findViewById(R.id.button);
        share = findViewById(R.id.share);

        // Initialize Jitsi Meet SDK
        initializeJitsiMeet();

        // Set onClick listener for the 'Join' button
        button.setOnClickListener(v -> {
            String roomCode = codeBox.getText().toString().trim();
            if (!roomCode.isEmpty()) {
                joinMeeting(roomCode);
            } else {
                Toast.makeText(MainActivity.this, "Room code cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        // Set onClick listener for the 'Share' button
        share.setOnClickListener(v -> {
            shareMeetingInfo();
        });
    }

    private void initializeJitsiMeet() {
        try {
            URL serverUrl = new URL("https://meet.jit.si"); // Replace with your server URL if needed
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverUrl)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Invalid server URL", Toast.LENGTH_SHORT).show();
        }
    }

    private void joinMeeting(String roomCode) {
        JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                .setRoom(roomCode)
                .setFeatureFlag("invite.enabled", false)
                .build();
        JitsiMeetActivity.launch(MainActivity.this, options);
    }

    private void shareMeetingInfo() {
        String roomCode = codeBox.getText().toString().trim();
        if (!roomCode.isEmpty()) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Enter the room code to join the meeting: " + roomCode);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share meeting info"));
        } else {
            Toast.makeText(this, "Room code is empty, nothing to share", Toast.LENGTH_SHORT).show();
        }
    }
}
