package com.example.ibcompsciia.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ibcompsciia.Models.Event;
import com.example.ibcompsciia.Models.User;
import com.example.ibcompsciia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    private EditText emailField;
    private EditText passwordField;

    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
    }

    public void signIn(View v) {
        System.out.println("Login");
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();
        System.out.println(String.format("Email: %s and Password %s", emailString, passwordString));

        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login: ", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login: ", "signInWithEmail:failure", task.getException());
                    updateUI(null);
                }
            }
        });


        ArrayList<String> myCars = new ArrayList<>();
        myCars.add("Tesla"); myCars.add("Toyota"); myCars.add("BMW");
        myCars.add("Audi"); myCars.add("Mercedes"); myCars.add("VW");

        ArrayList<String> myEventsUID = new ArrayList<>();
        myEventsUID.add(UUID.randomUUID().toString());

        User thisUser = new User(UUID.randomUUID().toString(),"Yuuto", "Yuuto_Kistner@fis.edu", 2023);
        Event thisEvent = new Event("Annual Bake Sale", "10:00", "11:00", "Auditorium", 4, "Cookies", UUID.randomUUID().toString());

        User thatUser = new User(UUID.randomUUID().toString(),"Abc", "Abc@fis.edu", 2024);
        Event thatEvent = new Event("Annual Bake Sale", "11:00", "12:00", "Cafeteria", 2, "Brownies", UUID.randomUUID().toString());


        firestore.collection("everyones-items").document("kistner").collection("User").document(thisUser.getUid()).set(thisUser);
        firestore.collection("everyones-items").document("kistner").collection("Event").document(thisEvent.getEventId()).set(thisEvent);

        firestore.collection("everyones-items/kistner/User").document(thatUser.getUid()).set(thatUser);
        firestore.collection("everyones-items/kistner/Event").document(thatEvent.getEventId()).set(thatEvent);

        firestore.collection("everyones-items").document("chandler").collection("books").document(thisUser.getUid()).set(thisUser);

        Log.d("KISTNER_TEST", "ID to look for: " + thatUser.getUid());

        firestore.collection("everyones-items/kistner/User").document(thatUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot ds = task.getResult();

                    Event myEvent = ds.toObject(Event.class);
                    User myUser = ds.toObject(User.class);
                }
                else{

                }
            }
        });
    }

    public void loginSignUpButton(View v) {
        Intent nextScreen = new Intent(getBaseContext(), CreateAccountActivity.class);
        startActivity(nextScreen);
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, NavigationActivity.class);
            startActivity(intent);
        }
    }
}