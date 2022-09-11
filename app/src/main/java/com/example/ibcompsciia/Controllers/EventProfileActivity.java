package com.example.ibcompsciia.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibcompsciia.Models.Event.BakeSale;
import com.example.ibcompsciia.Models.Event.Event;
import com.example.ibcompsciia.R;
import com.example.ibcompsciia.Utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class EventProfileActivity extends AppCompatActivity /*implements View.OnClickListener */{

    //    private ArrayList<Event> eventsList;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private Event selectedEvent;
    private TextView epName;
    private TextView epType;
    private TextView epCapacity;
    private TextView epRemaining;
    private Button buttonReserveEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_profile);

        if(getIntent().hasExtra("Event")) {
            selectedEvent = (Event) getIntent().getSerializableExtra("Event");
        }



        System.out.println(selectedEvent);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        epName = findViewById(R.id.eventProfileName);
        epType = findViewById(R.id.eventProfileType);
        epCapacity = findViewById(R.id.eventProfileCapacity);
        epRemaining = findViewById(R.id.eventProfileRemaining);


        epName.setText(selectedEvent.getEventName());
        epType.setText(selectedEvent.getEventType());
        epCapacity.setText(""+selectedEvent.getCapacity());
        epRemaining.setText(""+selectedEvent.getRemainingCapacity());

        // check to see if there are any extras attached
        if (getIntent().hasExtra("selected_event")) {
            System.out.println("HAS EXTRA");
            // retrieve the parcel and type case it to a Event object
            selectedEvent = (Event) getIntent().getParcelableExtra("selected_event");
        }
    }

    public void bookEvent() {
        System.out.println("BOOK EVENT");
        System.out.println(selectedEvent.getRemainingCapacity());
        //close event if user took last seat available
        if (selectedEvent.getRemainingCapacity() < 1) {
            firestore.collection(Constants.EVENT_PATH).document(selectedEvent.getEventId())
                    .update("open", false);
        }

        // update capacity
        firestore.collection(Constants.EVENT_PATH).document(selectedEvent.getEventId())
                .update("remainingCapacity", selectedEvent.getRemainingCapacity() - 1);

        // add user's uid to the list of reservedUids
        selectedEvent.addReservedUid(mAuth.getUid());
        firestore.collection(Constants.EVENT_PATH).document(selectedEvent.getEventId())
                .update("reservedUids", selectedEvent.getParticipantsUIDs())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // go back to ViewEventActivity
                        Intent intent = new Intent(getApplicationContext(), ViewEventActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        // right here
        System.out.println("EVENTS UIDS" + selectedEvent.getParticipantsUIDs());
    }


    public void decrementCapacity() {
        firestore.collection(Constants.EVENT_PATH).document(selectedEvent.getEventId()).get()
                .addOnCompleteListener(
                        (task) -> {
                            if(task.getResult() == null) {
                            }
                            else if(task.isSuccessful()) {

                                try {
                                    int remainingCapacity = task.getResult().get(Constants.REMAININGCAPACITY, Integer.class);
                                    remainingCapacity--;
                                    selectedEvent.setRemainingCapacity(remainingCapacity);
                                    epRemaining.setText("Remaining Capacity: "+ remainingCapacity);
                                    firestore.collection(Constants.EVENT_PATH).document(selectedEvent.getEventId()).update(Constants.REMAININGCAPACITY, remainingCapacity);

                                    if(remainingCapacity == 0) {
                                        firestore.collection(Constants.EVENT_PATH).document(selectedEvent.getEventId()).update(Constants.OPEN, false);
                                    }
                                }
                                catch(Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            else{
                                task.getException().printStackTrace();
                                Toast.makeText(this, "Error booking event", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    public void onClick(View v) {
        try {
            bookEvent();
            decrementCapacity();
            Toast.makeText(this, "Booked Successfully", Toast.LENGTH_SHORT).show();
            buttonReserveEvent.setEnabled(false);
            buttonReserveEvent.setText("Booked");
        }
        catch (Exception e) {
            Toast.makeText(this, "Error booking vehicle", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
