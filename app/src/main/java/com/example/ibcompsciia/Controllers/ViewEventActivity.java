package com.example.ibcompsciia.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.ibcompsciia.Models.Event;
import com.example.ibcompsciia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewEventActivity extends AppCompatActivity {

    private static final String TAG = "";
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    //added for testing
    private ArrayList<Event> vehiclesList;
    private RecyclerView vehicleRecView;
    private RecyclerAdapter myAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
    }
}