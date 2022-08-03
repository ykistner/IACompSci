package com.example.ibcompsciia.Controllers;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibcompsciia.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected TextView eventNameText;
    protected TextView eventStartTimeText;
    protected TextView eventCapacityText;
    protected TextView eventLocationText;

    private RecyclerAdapter.OnViewClickListener onViewClickListener;

    public RecyclerViewHolder(@NonNull View itemView, RecyclerAdapter.OnViewClickListener onViewClickListner) {
        super(itemView);
        this.onViewClickListener = onViewClickListener;

        eventNameText = itemView.findViewById(R.id.eventNameTextView);
        eventStartTimeText = itemView.findViewById(R.id.eventStartTextView);
        eventCapacityText = itemView.findViewById(R.id.eventCapacityTextView);
        eventLocationText = itemView.findViewById(R.id.eventLocationTextView);

        itemView.setOnClickListener(this);
    }

    public void eventNameText(String model) {
        eventNameText.setText(model);
    }

    public void eventStartTimeText(String vehicleType) {
        eventStartTimeText.setText(vehicleType);
    }

    public void eventCapacityText(String s) {
        eventCapacityText.setText(s);
    }

    public void eventLocationText(String s) {
        eventLocationText.setText(s);
    }

    @Override
    public void onClick(View v) {
        onViewClickListener.onViewClick(getAdapterPosition());
    }
}