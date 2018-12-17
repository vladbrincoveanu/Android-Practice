package com.example.vladpc.testap.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.vladpc.testap.testapp.Adapter.ChatAdapter;
import com.example.vladpc.testap.testapp.Models.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int nrMessage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //find User
        String currentUser;
        Intent currentIntent = getIntent();
        currentUser = currentIntent.getStringExtra("username");
        // Get RecyclerView object.
        final RecyclerView msgRecyclerView = findViewById(R.id.recView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);

        final List<Message> msgList = new ArrayList<>();
        Message msg = new Message("da","da",new Date().toString());
        msgList.add(msg);

        // Create the data adapter with above data list.
        final ChatAdapter chatAdapter = new ChatAdapter(this, msgList);

        // Set data adapter to RecyclerView.
        msgRecyclerView.setAdapter(chatAdapter);

        TextView username = findViewById(R.id.username);
        username.setText("Username: " + currentUser);
        final EditText msgInput = findViewById(R.id.msgInput);

        Button sendButton = findViewById(R.id.sendMsg);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgContent = msgInput.getText().toString();
                if (!TextUtils.isEmpty(msgContent)) {
                    Date currentTime = Calendar.getInstance().getTime();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat spf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
                    String newDateString = spf.format(currentTime);
                    Message msgComputer = new Message("Computer", "Automated message:" + nrMessage++, newDateString);
                    msgList.add(msgComputer);
                    int newMsgPosition = msgList.size() - 1;
                    chatAdapter.notifyItemInserted(newMsgPosition);
                    msgRecyclerView.scrollToPosition(newMsgPosition);


                    Message msg = new Message("admin", msgContent, newDateString);
                    msgList.add(msg);
                    newMsgPosition = msgList.size() - 1;
                    chatAdapter.notifyItemInserted(newMsgPosition);
                    msgRecyclerView.scrollToPosition(newMsgPosition);
                    msgInput.setText("");
                }
            }
        });
    }
}
