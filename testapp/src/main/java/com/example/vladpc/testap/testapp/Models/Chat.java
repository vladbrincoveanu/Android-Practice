package com.example.vladpc.testap.testapp.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Chat {
    private String Name;
    private String Message;
    private Date Date;

    public Chat(String name, String message, java.util.Date date) {
        Name = name;
        Message = message;
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public String getMessage() {
        return Message;
    }

    public java.util.Date getDate() {
        return Date;
    }
}