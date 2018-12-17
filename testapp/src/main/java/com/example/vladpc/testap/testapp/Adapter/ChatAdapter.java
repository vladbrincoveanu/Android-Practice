package com.example.vladpc.testap.testapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.vladpc.testap.testapp.Models.Message;
import com.example.vladpc.testap.testapp.R;
import com.example.vladpc.testap.testapp.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ViewHolder>{
    private List<Message> messages;
    private Context context;
    public ChatAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(layoutInflater.inflate(R.layout.char_layout, viewGroup, false)); // returns the view holder which is created from the view
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Message message = messages.get(position);
        Log.w("da",message.toString());
        viewHolder.username.setText(message.getUsername());
        viewHolder.inputMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        if(messages==null)
        {
            messages = new ArrayList<Message>();
        }
        return messages.size(); // returns the number of objects from the list
    }
}

