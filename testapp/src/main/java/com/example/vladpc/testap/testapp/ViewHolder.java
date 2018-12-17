package com.example.vladpc.testap.testapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vladpc.testap.testapp.Models.Message;
import org.w3c.dom.Text;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView inputMessage;
    public TextView username;
    public TextView data;

    public ViewHolder(View itemView) {
        super(itemView);
        inputMessage = itemView.findViewById(R.id.inputMessageLay);
        username = itemView.findViewById(R.id.usernameLay);
    }
}

