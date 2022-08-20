package com.example.ibcompsciia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibcompsciia.Controllers.RecyclerAdapter;
import com.example.ibcompsciia.R;
import com.example.ibcompsciia.chatting.dataCommunication;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunicationRecyclerAdapter extends RecyclerView.Adapter<CommunicationRecyclerAdapter.CommunicationViewHolder> {

    Context context;
    List<dataCommunication>listCommunication;

    @NonNull
    @Override
    public CommunicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_communication, parent, false);
        return new CommunicationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunicationViewHolder holder, int position) {
        holder.bindView(listCommunication.get(position));
    }

    @Override
    public int getItemCount() {
        return listCommunication.size();
    }

    public class CommunicationViewHolder extends RecyclerView.ViewHolder {
        EditText from, message, time;
        CircleImageView imageView;
        LinearLayout linear, linear2;
        CardView cardView;


        public CommunicationViewHolder(@NonNull View itemView) {
            super(itemView);
            from = itemView.findViewById(R.id.from);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageView);
            linear = itemView.findViewById(R.id.linear);
            linear2 = itemView.findViewById(R.id.linear2);
            cardView = itemView.findViewById(R.id.cardView);
        }

        public void bindView(dataCommunication dataCommunication) {

        }
    }
}
