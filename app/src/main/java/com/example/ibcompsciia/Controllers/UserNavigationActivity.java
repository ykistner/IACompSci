package com.example.ibcompsciia.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ibcompsciia.R;

public class UserNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_navigation);
    }

    public void goToHomeUser(View v) {
        Intent nextScreen = new Intent(getBaseContext(), UserNavigationActivity.class);
        startActivity(nextScreen);
    }

    public void goToChatUser(View v) {
        Intent nextScreen = new Intent(getBaseContext(), AddChatActivity.class);
        startActivity(nextScreen);
    }

    public void goToEventsUser(View v) {
        Intent nextScreen = new Intent(getBaseContext(), ViewEventActivity.class);
        startActivity(nextScreen);
    }
}