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
    private TextView epUID;
    private Button buttonReserveEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_profile);
//
//        Bundle extras = getIntent().getExtras();
//
//        //    String currId = extras.getString("id");
//        //  System.out.println(extras.getString("id"));
//
        Event event = null;
        if(getIntent().hasExtra("Event")) {
            event = (Event) getIntent().getSerializableExtra("Event");
        }



        System.out.println(event);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        epName = findViewById(R.id.eventProfileName);
        epType = findViewById(R.id.eventProfileType);
        epCapacity = findViewById(R.id.eventProfileCapacity);
        epRemaining = findViewById(R.id.eventProfileRemaining);
        epUID = findViewById(R.id.eventProfileUID);


        epName.setText(event.getEventName());
        epType.setText(event.getEventType());
        epCapacity.setText(""+event.getCapacity());
        epRemaining.setText(""+event.getRemainingCapacity());
        epUID.setText(event.getEventId());

//        // check to see if there are any extras attached
//        if (getIntent().hasExtra("selected_event")) {
//            System.out.println("HAS EXTRA");
//            // retrieve the parcel and type case it to a Event object
//            selectedEvent = (Event) getIntent().getParcelableExtra("selected_event");
//
//            // retrieve the data for the event
//            eventMaxCapacityDataTextView = findViewById(R.id.MaxCapacityDataTextView);
//            eventRemainingCapacityDataTextView = findViewById(R.id.RemainingCapacityDataTextView);
//            bookedUIDs = findViewById(R.id.BookedUIDsDataTextView);
//            eventNameTextView = findViewById(R.id.EventNameTextView);
//            eventTypeTextView = findViewById(R.id.EventTypeTextView);
//
//            // update the TextViews with the event information
//            eventMaxCapacityDataTextView.setText(String.valueOf(selectedEvent.getCapacity()));
//            eventRemainingCapacityDataTextView.setText(String.valueOf(selectedEvent.getRemainingCapacity()));
//            bookedUIDs.setText(selectedEvent.getParticipantsUIDs().toString());
//            eventNameTextView.setText(selectedEvent.getEventName());
//            eventTypeTextView.setText(selectedEvent.getEventType());
//        }
//
//        // find the button and attach a listener
//        buttonReserveEvent = findViewById(R.id.buttonReserveEvent);
//        buttonReserveEvent.setOnClickListener(this);
    }

//    public void bookEvent() {
//        System.out.println("BOOK EVENT");
//        System.out.println(selectedEvent.getRemainingCapacity());
//        //close event if user took last seat available
//        if (selectedEvent.getRemainingCapacity() == 1) {
//            firestore.collection("events").document(selectedEvent.getEventId())
//                    .update("open", false);
//        }
//
//        // update capacity
//        firestore.collection("events").document(selectedEvent.getEventId())
//                .update("remainingCapacity", selectedEvent.getRemainingCapacity() - 1);
//
//        // add user's uid to the list of reservedUids
//        selectedEvent.addReservedUid(mAuth.getUid());
//        firestore.collection("events").document(selectedEvent.getEventId())
//                .update("reservedUids", selectedEvent.getParticipantsUIDs())
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // go back to ViewEventActivity
//                        Intent intent = new Intent(getApplicationContext(), ViewEventActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//        // right here
//        System.out.println("EVENTS UIDS" + selectedEvent.getParticipantsUIDs());
//    }
//    //

//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
//        if (i == buttonReserveEvent.getId()) {
//            bookEvent();
//        }
//        /*
//        else if(i == buttonCancelEvent.getID()) {
//            cancelEvent();
//        }
//        */
//    }
}
