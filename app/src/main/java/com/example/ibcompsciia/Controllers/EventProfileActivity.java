package com.example.ibcompsciia.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibcompsciia.Models.Event.Event;
import com.example.ibcompsciia.R;
import com.example.ibcompsciia.Utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class EventProfileActivity extends AppCompatActivity /*implements View.onClickListener*/{
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser mUser;

    private LinearLayout linearLayout;

    private TextView textName;
    private TextView textStart;
    private TextView textEnd;
    private TextView textType;
    private TextView textCapacity;
    private TextView textLocation;

    private ImageView imageView;
    private StorageReference storageReference;

    private Button bookButton;
    private Event event;
    private float floatSize=24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_profile);

        firestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        linearLayout = findViewById(R.id.VehicleProfile_linearLayout);
        imageView = findViewById(R.id.VehicleProfile_imageView);
        bookButton = findViewById(R.id.VehicleProfile_bookButton);

//        if(getIntent().hasExtra(Constants.EVENT_PATH)){
//            event = getIntent().getParcelableExtra(Constants.EVENT_PATH);
//        }
    }

    public void book(View view) {

        try {
            firestore.collection(Constants.EVENT_PATH).document(event.getEventId());
            decrementCapacity();
            Toast.makeText(this, "Booked Successfully", Toast.LENGTH_SHORT).show();
            bookButton.setEnabled(false);
            bookButton.setText("Booked");
        }
        catch (Exception e) {
            Toast.makeText(this, "Error booking vehicle", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void decrementCapacity() {
        firestore.collection(Constants.EVENT_PATH).document(event.getEventId()).get()
                .addOnCompleteListener(
                        (task) -> {
                            if(task.getResult() == null) {
                            }
                            else if(task.isSuccessful()) {

                                try {
                                    int capacity = task.getResult().get(Constants.CAPACITY, Integer.class);
                                    capacity--;
                                    event.setCapacity(capacity);
                                    textCapacity.setText("Capacity: "+capacity);
                                    firestore.collection(Constants.EVENT_PATH).document(event.getEventId()).update(Constants.CAPACITY, capacity);

                                    if(capacity == 0) {
                                        firestore.collection(Constants.EVENT_PATH).document(event.getEventId()).update(Constants.OPEN, false);
                                    }
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else{
                                task.getException().printStackTrace();
                                Toast.makeText(this, "Error Signing Up To Event", Toast.LENGTH_SHORT).show();
                            }
                        });
    }
}