package com.example.vladpc.testap.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDetails extends AppCompatActivity {
    int favoriteOk = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //  referință la  componenta Intent
        Intent myIntent =   getIntent();

        //  extragere valoare asociată cu "nume_cheie”
        TextView titlu = (TextView) findViewById(R.id.titluD);
        titlu.setText(myIntent.getStringExtra("titlu"));
        TextView pret = (TextView) findViewById(R.id.pretD);
        pret.setText(myIntent.getStringExtra("pret"));
        TextView descriere = (TextView) findViewById(R.id.descriereD);
        descriere.setText(myIntent.getStringExtra("descriere"));
        TextView timer = (TextView) findViewById(R.id.timer);
        timer.setText(myIntent.getStringExtra("timer"));
        ImageView image = (ImageView)findViewById(R.id.imageView);
        image.setImageResource(Integer.parseInt(myIntent.getStringExtra("imagine")));
        int favorite = myIntent.getIntExtra("favorite",0);
        this.favoriteOk = favorite;
    }

    @Override
    public void onBackPressed() {
        Intent	returnIntent	=	new	Intent();
        returnIntent.putExtra("result",this.favoriteOk);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public  boolean onCreateOptionsMenu(Menu menu)
    {
        //  Inflate the menu;   this    adds    items   to  the action  bar if  it  is  present.
        getMenuInflater().inflate(R.menu.favorites , menu);
        MenuItem menuItem = menu.getItem(0);
        if    (favoriteOk == 0)
        {
            menuItem.setTitle("Add to favorites");
            Toast.makeText(this,  "Remove to favorites",   Toast.LENGTH_LONG).show();
        }
        else
        {
            menuItem.setTitle("Remove from favorites");
            Toast.makeText(this,  "Added to favorites",    Toast.LENGTH_LONG).show();
        }
        return  true;
    }

    public    boolean onOptionsItemSelected(MenuItem item)
    {
        //    Handle  action  bar item    clicks  here.   The action  bar will
        //    automatically   handle  clicks  on  the Home/Up button, so  long
        //    as  you specify a   parent  activity    in  AndroidManifest.xml.
        this.favoriteOk = (favoriteOk + 1) % 2;
        if    (favoriteOk == 0)
        {
            item.setTitle("Add to favorites");
            Toast.makeText(this,  "Remove to favorites",   Toast.LENGTH_LONG).show();
        }
        else
        {
            item.setTitle("Remove from favorites");
            Toast.makeText(this,  "Added to favorites",    Toast.LENGTH_LONG).show();
        }
        return    super.onOptionsItemSelected(item);
    }
}