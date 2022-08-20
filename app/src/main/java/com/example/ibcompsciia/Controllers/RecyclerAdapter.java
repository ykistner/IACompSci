package com.example.ibcompsciia.Controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibcompsciia.Event.Event;
import com.example.ibcompsciia.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<Event> eventList;
    private OnViewClickListener onViewClickListener;

    public RecyclerAdapter(ArrayList<Event> eventList, OnViewClickListener onViewClickListener) {
        this.eventList = eventList;
        this.onViewClickListener = onViewClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_view, parent, false);

        RecyclerViewHolder holder = new RecyclerViewHolder(myView, onViewClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.eventNameText(eventList.get(position).getEventName());
        holder.eventStartTimeText(eventList.get(position).getStartTime());
        holder.eventCapacityText("Capacity: "+ eventList.get(position).getCapacity());
        holder.eventLocationText("€"+ eventList.get(position).getEventLocation());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public interface OnViewClickListener {
        void onViewClick(int position);
    }



}
