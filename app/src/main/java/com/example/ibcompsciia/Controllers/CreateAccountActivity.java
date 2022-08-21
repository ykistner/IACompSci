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

import com.example.ibcompsciia.Models.User.Admin;
import com.example.ibcompsciia.Models.User.Alumni;
import com.example.ibcompsciia.Models.User.Parent;
import com.example.ibcompsciia.Models.User.Student;
import com.example.ibcompsciia.Models.User.Teacher;
import com.example.ibcompsciia.Utils.Constants;
import com.example.ibcompsciia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore firestore;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private LinearLayout layout;
    private EditText emailField;
    private EditText passwordField;
    private EditText nameField;
    private EditText gradYearField;
    private Spinner userRoleSpinner;
    private String selectedRole;
    private String uid;
    private EditText adminCodeField;
    private String nameString;
    private EditText textInSchoolTitle;
    private EditText textChildrenUIDs;
    private EditText graduatingYearField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        layout = findViewById(R.id.createAccountLinearLayout);
        userRoleSpinner = findViewById(R.id.createAccountSpinner);
        setupSpinner();
    }

    // setup spinner where user selects what user type they want to make an account for
    private void setupSpinner() {
        String[] userTypes = {Constants.STUDENT, Constants.TEACHER, Constants.ALUMNI, Constants.PARENT, Constants.ADMIN};
        // add user types to spinner
        ArrayAdapter<String> langArrAdapter = new ArrayAdapter<String>(CreateAccountActivity.this,
                android.R.layout.simple_spinner_item, userTypes);
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

    public void addFields() {
        commonFields();
        if (selectedRole.equals("Alumni")) {
            gradYearField = new EditText(this);
            gradYearField.setHint("Graduation year");
            layout.addView(gradYearField);
        }
        if(selectedRole.equals("Student")){
            graduatingYearField = new EditText(this);
            graduatingYearField.setHint("Graduating Year");
            layout.addView(graduatingYearField);
        }
        if (selectedRole.equals("Admin")) {
            adminCodeField = new EditText(this);
            adminCodeField.setHint("Admin Code");
            layout.addView(adminCodeField);
        }
        if(selectedRole.equals("Teacher")){
            textInSchoolTitle = new EditText(this);
            textInSchoolTitle.setHint("In School Title");
            layout.addView(textInSchoolTitle);
        }
        if(selectedRole.equals("Parent")){
            textChildrenUIDs = new EditText(this);
            textChildrenUIDs.setHint("Children User ID");
            layout.addView(textChildrenUIDs);
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
                            //saveUser(emailString,passwordString,nameString);
                            Log.d(Constants.SIGN_UP, Constants.SIGN_UP_SUCCESS);
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                            updateUI(user);
                            mUser = mAuth.getCurrentUser();
                            uid = mUser.getUid();
                            addUserToDatabase(emailString);
                        }
                        else {
                            Log.d(Constants.SIGN_UP, Constants.SIGN_UP_FAILURE, task.getException());
                            Toast.makeText(CreateAccountActivity.this,Constants.SIGN_UP_FAILURE_TOAST, Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void addUserToDatabase(String emailString) {
        if(selectedRole.equals(Constants.ALUMNI)) {
            int gradYearInt = Integer.parseInt(gradYearField.getText().toString());
            Alumni newUser = new Alumni(uid, nameString, emailString, "Alumni", 10, gradYearInt);
            //add the new user to the database
            firestore.collection(Constants.USER_COLLECTION).document(uid).set(newUser);
        }
        if(selectedRole.equals(Constants.TEACHER)) {
            String inSchoolTitle = textInSchoolTitle.getText().toString();
            Teacher newUser = new Teacher(uid, nameString, emailString, "Teacher", 10, inSchoolTitle);
            //add the new user to the database
            firestore.collection(Constants.USER_COLLECTION).document(uid).set(newUser);
        }
        if(selectedRole.equals(Constants.ADMIN)){
            String adminCodeString = adminCodeField.getText().toString();
            Admin newUser = new Admin(uid, nameString, emailString, "Admin", 10, adminCodeString);
            firestore.collection(Constants.USER_COLLECTION).document(uid).set(newUser);
        }
        if(selectedRole.equals(Constants.PARENT)){
            ArrayList<String> childrenUIDs = new ArrayList<>();
            String childrenUID = textChildrenUIDs.getText().toString();
            childrenUIDs.add(childrenUID);
        }
        if(selectedRole.equals(Constants.STUDENT)){
            int graduatingYearInt = Integer.parseInt(graduatingYearField.getText().toString());
            Student newUser = new Student(uid, nameString, emailString, "Student", 10, graduatingYearInt, null);
        }
    }

    public void updateUI(FirebaseUser currentUser) {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(nameString).build();
                    user.updateProfile(profileUpdates);
                } else {
                    finish();
                }
            }
        };
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

