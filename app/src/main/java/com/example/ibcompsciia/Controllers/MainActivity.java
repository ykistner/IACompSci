package com.example.ibcompsciia.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ibcompsciia.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mainSignupButton(View v) {
        Intent nextScreen = new Intent(getBaseContext(), CreateAccountActivity.class);
        startActivity(nextScreen);
    }

    public void mainLoginButton(View v) {
        Intent nextScreen = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(nextScreen);
    }
}

