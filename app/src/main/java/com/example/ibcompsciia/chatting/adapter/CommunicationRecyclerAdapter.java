package com.example.ibcompsciia.chatting.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ibcompsciia.R;
import com.example.ibcompsciia.chatting.dataCommunication;
import com.example.ibcompsciia.chatting.session.preferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunicationRecyclerAdapter extends RecyclerView.Adapter<CommunicationRecyclerAdapter.CommunicationViewHolder> {

    Context context;
    List<dataCommunication>listCommunication;
    List<String> listData;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public CommunicationRecyclerAdapter(Context context, List<dataCommunication> listCommunication, List<String> listData) {
        this.context = context;
        this.listCommunication = listCommunication;
        this.listData = listData;
    }

    @NonNull
    @Override
    public CommunicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_communication, parent, false);
        return new CommunicationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunicationViewHolder holder, int position) {
        String itemData = listData.get(position);
        dataCommunication itemNormal = listCommunication.get(position);

        Date date = new Date();
        Locale locale = new Locale("in", "ID");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", locale);
        long yesterday = date.getTime() - (1000 * 60 * 60 * 24);

        if(itemNormal.getDate().equals(itemData)){
            holder.linearDate.setVisibility(View.GONE);
        }

        if(itemNormal.getDate().equals(simpleDateFormat.format(yesterday))){
            holder.textDate.setText("Yesterday");
        }
        else if(itemNormal.getDate().equals(simpleDateFormat.format((date.getTime())))){
            holder.textDate.setText("Today");
        }
        else{
            holder.textDate.setText(itemNormal.getDate());
        }
        holder.bindView(listCommunication.get(position));
    }

    @Override
    public int getItemCount() {
        return listCommunication.size();
    }

    public class CommunicationViewHolder extends RecyclerView.ViewHolder {
        TextView from, message, time, textDate;
        CircleImageView imageView;
        LinearLayout linear, linear2, linearDate;
        CardView cardView, cardDate;


        public CommunicationViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.textDate);
            from = itemView.findViewById(R.id.from);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageView);
            linear = itemView.findViewById(R.id.linear);
            linear2 = itemView.findViewById(R.id.linear2);
            cardView = itemView.findViewById(R.id.cardView);
            linearDate = itemView.findViewById(R.id.linearDate);
            cardDate = itemView.findViewById(R.id.cardDate);
        }

        public void bindView(dataCommunication dataCommunication) {
            Locale locale = new Locale("in", "ID");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa", locale);

            from.setText(dataCommunication.getFrom());
            message.setText(dataCommunication.getMessage());
            time.setText(simpleDateFormat.format(dataCommunication.getTime()));

            database.child("login").child(dataCommunication.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String srcImage = dataSnapshot.child("image").getValue(String.class);
                    Glide.with(context).load(srcImage).placeholder(R.drawable.profile).into(imageView);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            if(dataCommunication.getKey().equals(preferences.getKeyData(context))){
                from.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                linear.setGravity(Gravity.CENTER|Gravity.END);
                linear2.setGravity(Gravity.CENTER|Gravity.END);
                message.setTextColor(context.getResources().getColor(android.R.color.white));
                cardView.setCardBackgroundColor(context.getResources().getColor(R.color.design_default_color_primary_dark));
            }
        }
    }
}