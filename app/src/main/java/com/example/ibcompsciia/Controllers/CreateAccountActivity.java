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

import com.example.ibcompsciia.Models.Admin;
import com.example.ibcompsciia.Models.Alumni;
import com.example.ibcompsciia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private LinearLayout layout;
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private EditText gradYearField;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String uid;
    private EditText adminCodeField;
    private static int uidGenerator = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.createAccountLinearLayout);
        userRoleSpinner = findViewById(R.id.createAccountSpinner);
        setupSpinner();
        uid = "" + uidGenerator;
        uidGenerator++;
    }

    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] userTypes = {"Student", "Teacher", "Alumni", "Parent", "Admin"};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(CreateAccountActivity.this,
                android.R.layout.simple_spinner_item, userTypes);
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

    public void addFields() {
        commonFields();
        if(selectedRole.equals("Alumni")) {
            gradYearField = new EditText(this);
            gradYearField.setHint("Graduation year");
            layout.addView(gradYearField);
        }
        if(selectedRole.equals("Admin")){
            adminCodeField = new EditText(this);
            adminCodeField.setHint("Admin Code");
            layout.addView(adminCodeField);
        }
    }

    public void commonFields() {
        layout.removeAllViewsInLayout();
        nameField = new EditText(this);
        nameField.setHint("Name");
        layout.addView(nameField);
        emailField = new EditText(this);
        emailField.setHint("Email");
        layout.addView(emailField);
        passwordField = new EditText(this);
        passwordField.setHint("Password");
        layout.addView(passwordField);
    }


    public void signUp(View v) {
        String nameString = nameField.getText().toString();
        String emailString = emailField.getText().toString();
        String passwordString = passwordField.getText().toString();
        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("SIGN UP", "successfully signed up the user");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else {
                            Log.d("SIGN UP", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateAccountActivity.this,"Sign up failed", Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
        if(selectedRole.equals("Alumni")) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            Alumni newUser = new Alumni(uid, nameString, emailString, gradYearInt);
            uidGenerator++;
            firestore.collection("people").document(uid).set(newUser);
        }
        if(selectedRole.equals("Admin")){
            String adminCodeString = adminCodeField.getText().toString();
            Admin newUser = new Admin(uid, nameString, emailString, adminCodeString);
            uidGenerator++;
            firestore.collection("people").document(uid).set(newUser);
        }
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this,NavigationActivity.class);
            startActivity(intent);
        }
    }
}

