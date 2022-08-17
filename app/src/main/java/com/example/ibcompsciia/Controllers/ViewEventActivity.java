package com.example.ibcompsciia.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.example.ibcompsciia.Models.Constants;
import com.example.ibcompsciia.Models.Event;
import com.example.ibcompsciia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewEventActivity extends AppCompatActivity implements RecyclerAdapter.OnViewClickListener{

    private static final String TAG = "";
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    //added for testing
    private ArrayList<Event> eventsList;
    private RecyclerView vehicleRecView;
    private RecyclerAdapter myAdapter;
    private Context context;

    ArrayList allTheStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        vehicleRecView = findViewById(R.id.eventRecView);
        context = this;

        eventsList = new ArrayList<Event>();
    }

    public void getEvent(View v) {
//      eventList.clear();
        TaskCompletionSource<String> getAllEventsTask = new TaskCompletionSource<>();
        firestore.collection(Constants.EVENT_COLLECTION).whereEqualTo("open", true)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        eventsList.add(document.toObject(Event.class));
                    }
                    getAllEventsTask.setResult(null);
                }
                else {
                    Log.d("VehiclesInfoActivity", "Error getting documents from db: ", task.getException());
                }
            }
        });
        // when all events have been retrieved, update RecyclerView
        getAllEventsTask.getTask().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                myAdapter = new RecyclerAdapter(eventsList, ViewEventActivity.this);
                vehicleRecView.setAdapter(myAdapter);
                vehicleRecView.setLayoutManager(new LinearLayoutManager(ViewEventActivity.this));
            }
        });
    }

//    public void gotoUserProfile(View v) {
//        Intent intent = new Intent(this, UserProfileActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onViewClick(int position) {
        System.out.println(position);
        Intent intent = new Intent(this, EventProfileActivity.class);
        intent.putExtra("Selected Vehicle" ,(Parcelable) eventsList.get(position));
        startActivity(intent);

    }
}