package com.example.ibcompsciia.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.ibcompsciia.Models.BakeSale;
import com.example.ibcompsciia.Models.Constants;
import com.example.ibcompsciia.Models.Event;
import com.example.ibcompsciia.Models.Meeting;
import com.example.ibcompsciia.Models.Presentation;
import com.example.ibcompsciia.Models.VolunteerWork;
import com.example.ibcompsciia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddEventActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String uid;
    private static int uidGenerator = 1;
    private EditText eventNameField;
    private EditText eventStartField;
    private EditText eventEndField;
    private EditText eventLocationField;
    private EditText eventIdField;
    private EditText eventType;
    private EditText eventCapacityField;
    private EditText requiredBakedGoods;
    private EditText organizer;
    private EditText topic;
    private EditText cause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.createAccountLinearLayout);
        userRoleSpinner = findViewById(R.id.createAccountSpinner);

        setupSpinner();
        uid = "" + uidGenerator;
        uidGenerator++;
    }

    private void setupSpinner() {
        String[] eventTypes = {"Bake Sale", "Meeting", "Presentation", "Volunteer Work"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AddEventActivity.this,
                android.R.layout.simple_spinner_item, eventTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void addFields(){
        commonFields();
        if(selectedRole.equals("Bake Sale")) {
            requiredBakedGoods = new EditText(this);
            requiredBakedGoods.setHint("Baked Goods That Are Needed");
            layout.addView(requiredBakedGoods);

        }
        if(selectedRole.equals("Meeting")) {
            topic = new EditText(this);
            topic.setHint("Topic");
            layout.addView(topic);

            organizer = new EditText(this);
            organizer.setHint("Organizer");
            layout.addView(organizer);
        }
        if(selectedRole.equals("Presentation")) {
            topic = new EditText(this);
            topic.setHint("Topic");
            layout.addView(topic);

            organizer = new EditText(this);
            organizer.setHint("Organizer");
            layout.addView(organizer);
        }
        if(selectedRole.equals("Volunteer Work")) {
            cause = new EditText(this);
            cause.setHint("Cause");
            layout.addView(cause);
        }
    }

    private void commonFields() {
        layout.removeAllViewsInLayout();

        eventNameField = new EditText(this);
        eventNameField.setHint("Event Name");
        layout.addView(eventNameField);

        eventStartField = new EditText(this);
        eventStartField.setHint("Event Start Time");
        layout.addView(eventStartField);

        eventEndField = new EditText(this);
        eventEndField.setHint("Event End Time");
        layout.addView(eventEndField);

        eventCapacityField = new EditText(this);
        eventCapacityField.setHint("Event Capacity");
        layout.addView(eventCapacityField);

        eventLocationField = new EditText(this);
        eventLocationField.setHint("Event Location");
        layout.addView(eventLocationField);
    }

    public void addVehicle(View v) {

        //generate + get new key
        DocumentReference newRideRef = firestore.collection(Constants.EVENT_COLLECTION).document();
        String eventId = newRideRef.getId();

        //make new event according to selected vehicle type
        Event newEvent = null;

        //get data from form
        String eventNameString = eventNameField.getText().toString();
        String eventStartString = eventStartField.getText().toString();
        String eventEndString = eventEndField.getText().toString();
        String eventLocation = eventLocationField.getText().toString();
        int eventCapacity = Integer.parseInt(eventCapacityField.getText().toString());

        if(selectedRole.equals(Constants.BAKESALE)) {
            String requiredBakedGoodsString = new String(requiredBakedGoods.getText().toString());
            newEvent = new BakeSale(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, requiredBakedGoodsString, eventId);
        }
        else if(selectedRole.equals(Constants.MEETING)) {
            String topicString = new String(topic.getText().toString());
            String organizerString = new String(organizer.getText().toString());
            newEvent = new Meeting(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, topic, organizer, eventId);
        }
        else if(selectedRole.equals(Constants.PRESENTATION)) {
            String topicString = new String(topic.getText().toString());
            String organizerString = new String(organizer.getText().toString());
            newEvent = new Presentation(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, topic, organizer, eventId);
        }
        else if(selectedRole.equals(Constants.VOLUNTEER)) {
            String causeString = new String(cause.getText().toString());
            newEvent = new VolunteerWork(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, cause, eventId);
        }
        System.out.println(newEvent);
        //add the new event to the database
        newRideRef.set(newEvent);
    }
}