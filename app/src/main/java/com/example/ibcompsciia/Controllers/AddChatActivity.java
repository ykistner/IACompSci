package com.example.ibcompsciia.Controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ibcompsciia.R;
import com.example.ibcompsciia.chatting.adapter.CommunicationRecyclerAdapter;
import com.example.ibcompsciia.chatting.dataCommunication;
import com.example.ibcompsciia.chatting.session.preferences;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AddChatActivity extends AppCompatActivity {

    EditText sendMessage;
    FloatingActionButton fab_send;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<dataCommunication> listCommunication = new ArrayList<>();
    ArrayList<String> listData = new ArrayList<>();
    RecyclerView recyclerView;
    CommunicationRecyclerAdapter communicationRecyclerAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chat);

        context = this;
        sendMessage = findViewById(R.id.sendMessage);
        fab_send = findViewById(R.id.fab_send);
        recyclerView = findViewById(R.id.recyclerView);

        fab_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMessage();
            }
        });
        sendDataMessageShow();
    }

    private void inputMessage() {
        String send = sendMessage.getText().toString();
        if (send.isEmpty()) {
            sendMessage.setError("Enter Message");
            sendMessage.requestFocus();
        } else {
            String id = preferences.getKeyData(context);
            String name = preferences.getNameData(context);
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);
            database.child("message")
                    .push()
                    .setValue(new dataCommunication(
                            id,
                            name,
                            send,
                            simpleDateFormat.format(System.currentTimeMillis()),
                            System.currentTimeMillis(),
                            "text"))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(context, "Message Sent Successfully", Toast.LENGTH_SHORT).show();
                            sendMessage.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Message Failed to Send", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendDataMessageShow() {
        database.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCommunication.clear();
                listData.clear();
                listData.add("");
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    dataCommunication communication = item.getValue(dataCommunication.class);
                    listData.add(communication != null ? communication.getDate() : null);
                    listCommunication.add(communication);
                }
                communicationRecyclerAdapter = new CommunicationRecyclerAdapter(context, listCommunication, listData);
                recyclerView.setAdapter(communicationRecyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}