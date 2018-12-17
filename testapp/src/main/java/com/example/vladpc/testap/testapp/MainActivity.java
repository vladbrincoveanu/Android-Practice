package com.example.vladpc.testap.testapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vladpc.testap.testapp.Adapter.API;
import com.example.vladpc.testap.testapp.Models.User;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView errorTextUsername;
    TextView errorTextPassword;
    EditText userNameInput;
    EditText passwordInput;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        errorTextUsername = findViewById(R.id.error);
        errorTextPassword = findViewById(R.id.error1);
        userNameInput = findViewById(R.id.editText2);
        passwordInput = findViewById(R.id.editText);
        loginText = findViewById(R.id.loginText);
    }

    public	void SignInClick(View view)
    {
        boolean userIsRight = true;
        boolean passwordIsRight = true;

        Log.w("LOGIN INFO",userNameInput.getText() + " " + passwordInput.getText());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Intent intent = new Intent(this,ActivityOffers.class);

        API userService = retrofit.create(API.class);
        Call<Void> loginInfo = userService.login(new User(userNameInput.getText().toString(),passwordInput.getText().toString()));
        loginInfo.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
//                    try {
////                        Toast.makeText(MainActivity.super.getApplicationContext(),response.raw().body().string(),Toast.LENGTH_SHORT).show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    JsonObject json = new JsonObject();
//                    intent.putExtra("name", json.get("display").toString());
                    startActivity(intent);
                } else {
                    int errorStatusCode = response.code();
                    String errorMessage;
                    try {
                        assert response.errorBody() != null;
                        errorMessage = response.errorBody().string();
                    } catch (IOException e) {
                        errorMessage = "Error message cannot be obtained!";
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.super.getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        if(userNameInput.getText().toString().isEmpty()){
            errorTextUsername.setText(getString(R.string.usernempty));
            errorTextUsername.setVisibility(View.VISIBLE);
            userIsRight = false;
        }
        else if(userNameInput.getText().toString().length() < 4){
            errorTextUsername.setText(getString(R.string.usertoshort));
            errorTextUsername.setVisibility(View.VISIBLE);
            userIsRight = false;
        }
        else {
            errorTextUsername.setVisibility(View.INVISIBLE);
        }

        if(passwordInput.getText().toString().isEmpty()){
            errorTextPassword.setText(getString(R.string.passempty));
            errorTextPassword.setVisibility(View.VISIBLE);
            passwordIsRight = false;
        }
        else if(passwordInput.getText().toString().length() < 4){
            errorTextPassword.setText(getString(R.string.passtoshort));
            errorTextPassword.setVisibility(View.VISIBLE);
            passwordIsRight = false;
        }
        else {
            errorTextPassword.setVisibility(View.INVISIBLE);
        }

        if(passwordIsRight && userIsRight){
            if(userNameInput.getText().toString().equals("admin") &&
                    passwordInput.getText().toString().equals("admin")){
                loginText.setTextColor(Color.GREEN);
                loginText.setText(getString(R.string.loginsucc));
                loginText.setVisibility(View.VISIBLE);
//                Intent intent = new Intent(this,ActivityOffers.class);
                intent.putExtra("username",userNameInput.getText().toString());
                startActivity(intent);
            }
            else {
                loginText.setText(getString(R.string.loginfail));
                loginText.setTextColor(Color.RED);
                loginText.setVisibility(View.VISIBLE);
            }
        }
    }
}
