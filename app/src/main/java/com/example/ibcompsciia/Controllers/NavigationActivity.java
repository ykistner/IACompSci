package com.example.ibcompsciia.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ibcompsciia.R;
import com.example.ibcompsciia.Utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class NavigationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void goToHome(View v) {
        Intent nextScreen = new Intent(getBaseContext(), NavigationActivity.class);
        startActivity(nextScreen);
    }

    public void goToChat(View v) {
        Intent nextScreen = new Intent(getBaseContext(), AddChatActivity.class);
        startActivity(nextScreen);
    }

    public void goToEvents(View v) {
        Intent nextScreen = new Intent(getBaseContext(), ViewEventActivity.class);
        startActivity(nextScreen);
    }

    public void goToAddEvents(View v) {
        Intent nextScreen = new Intent(getBaseContext(), AddEventActivity.class);
        startActivity(nextScreen);
    }


}