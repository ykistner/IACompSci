package com.example.ibcompsciia.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ibcompsciia.Models.Event.BakeSale;
import com.example.ibcompsciia.Utils.Constants;
import com.example.ibcompsciia.Models.Event.Event;
import com.example.ibcompsciia.Models.Event.Meeting;
import com.example.ibcompsciia.Models.Event.Presentation;
import com.example.ibcompsciia.Models.Event.VolunteerWork;
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
    private static int uidGenerator = 1;
    private EditText eventNameField;
    private EditText eventStartField;
    private EditText eventEndField;
    private EditText eventLocationField;
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
    }

    private void setupSpinner() {
        String[] eventTypes = {Constants.BAKESALE, Constants.MEETING, Constants.PRESENTATION, Constants.VOLUNTEER};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(AddEventActivity.this,
                android.R.layout.simple_spinner_item, eventTypes);
        langArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userRoleSpinner.setAdapter(langArrAdapter);

        //triggered whenever user selects something different
        userRoleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRole = parent.getItemAtPosition(position).toString();
                addFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void addFields() {
        commonFields();
        if (selectedRole.equals(Constants.BAKESALE)) {
            requiredBakedGoods = new EditText(this);
            requiredBakedGoods.setHint(Constants.BAKED);
            layout.addView(requiredBakedGoods);

        }
        if (selectedRole.equals(Constants.MEETING)) {
            topic = new EditText(this);
            topic.setHint(Constants.TOPIC);
            layout.addView(topic);

            organizer = new EditText(this);
            organizer.setHint(Constants.ORGANIZER);
            layout.addView(organizer);
        }
        if (selectedRole.equals(Constants.PRESENTATION)) {
            topic = new EditText(this);
            topic.setHint(Constants.TOPIC);
            layout.addView(topic);

            organizer = new EditText(this);
            organizer.setHint(Constants.ORGANIZER);
            layout.addView(organizer);
        }
        if (selectedRole.equals(Constants.VOLUNTEER)) {
            cause = new EditText(this);
            cause.setHint(Constants.CAUSE);
            layout.addView(cause);
        }
    }

    private void commonFields() {
        layout.removeAllViewsInLayout();

        eventNameField = new EditText(this);
        eventNameField.setHint(Constants.EVENTNAME);
        layout.addView(eventNameField);

        eventStartField = new EditText(this);
        eventStartField.setHint(Constants.STARTTIME);
        layout.addView(eventStartField);

        eventEndField = new EditText(this);
        eventEndField.setHint(Constants.ENDTIME);
        layout.addView(eventEndField);

        eventCapacityField = new EditText(this);
        eventCapacityField.setHint(Constants.CAPACITY);
        layout.addView(eventCapacityField);

        eventLocationField = new EditText(this);
        eventLocationField.setHint(Constants.LOCATION);
        layout.addView(eventLocationField);
    }

    public void addEvents(View v) {
        //generate + get new key
        DocumentReference newEventRef = firestore.collection(Constants.EVENT_COLLECTION).document();
        String eventId = newEventRef.getId();

        //make new event according to selected event type
        Event newEvent = null;


        //get data from form
        String eventNameString = eventNameField.getText().toString();
        String eventStartString = eventStartField.getText().toString();
        String eventEndString = eventEndField.getText().toString();
        String eventLocation = eventLocationField.getText().toString();
        int eventCapacity = Integer.parseInt(eventCapacityField.getText().toString());


        if (selectedRole.equals(Constants.BAKESALE)) {
            System.out.println("CREATING BAKESALE");
            String requiredBakedGoodsString = new String(requiredBakedGoods.getText().toString());
            newEvent = new BakeSale(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, eventId);
            System.out.println(newEvent);
        } else if (selectedRole.equals(Constants.MEETING)) {
            String topicString = new String(topic.getText().toString());
            String organizerString = new String(organizer.getText().toString());
            newEvent = new Meeting(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, topicString, organizerString, eventId);
        } else if (selectedRole.equals(Constants.PRESENTATION)) {
            String topicString = new String(topic.getText().toString());
            String organizerString = new String(organizer.getText().toString());
            newEvent = new Presentation(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, topicString, organizerString, eventId);
        } else if (selectedRole.equals(Constants.VOLUNTEER)) {
            String causeString = new String(cause.getText().toString());
            newEvent = new VolunteerWork(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, causeString, eventId);
        } else {
            newEvent = new Event(eventNameString, eventStartString, eventEndString, eventLocation, eventCapacity, cause, eventId);
        }
        System.out.println(newEvent);
        //add the new event to the database
        try {
            System.out.println("HAPPENS");
            newEventRef.set(newEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Toast.makeText(AddEventActivity.this, "Successfully Added Event", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
}