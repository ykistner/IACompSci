package com.example.ibcompsciia.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ibcompsciia.R;

public class NavigationActivity extends AppCompatActivity {

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
        Intent nextScreen = new Intent(getBaseContext(), ChatActivity.class);
        startActivity(nextScreen);
    }

    public void goToEvents(View v) {
        Intent nextScreen = new Intent(getBaseContext(), ViewEventActivity.class);
        startActivity(nextScreen);
    }

    public void goToAddEvents(View v){
        Intent nextScreen = new Intent(getBaseContext(), AddEventActivity.class);
        startActivity(nextScreen);
    }
}