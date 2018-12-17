package com.example.vladpc.testap.testapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.vladpc.testap.testapp.Adapter.AdapterList;
import com.example.vladpc.testap.testapp.Models.Offer;
import com.example.vladpc.testap.testapp.Models.Offers;

import java.util.List;
import java.util.Objects;

public class ActivityOffers extends AppCompatActivity {
    ListView listView;
    List<Offer> offerList;
    AdapterList adapterList;
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_offers);

        Offers offers = new Offers();
        listView = findViewById(R.id.ListView);
        offerList = offers.getOffers();
        adapterList = new AdapterList(this,offerList);

        final Intent intent = new Intent(ActivityOffers.this,ActivityDetails.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String img = String.valueOf(adapterList.getItem(i).getImagine());
                intent.putExtra("titlu", adapterList.getItem(i).getTitlu());
                intent.putExtra("descriere", adapterList.getItem(i).getDescriere());
                intent.putExtra("imagine", img);
                intent.putExtra("pret", adapterList.getItem(i).getPret());
                intent.putExtra("timer", adapterList.getItem(i).getPret());
                intent.putExtra("favorite",adapterList.getItem(i).getFavorite());
                startActivityForResult(intent,i);
            }
        });

        adapterList.notifyDataSetChanged();
        listView.setAdapter(adapterList);
        registerForContextMenu(listView);
    }

    protected	void	onActivityResult(int	requestCode,	int	resultCode,	Intent	data)
    {
            if(resultCode	==	Activity.RESULT_OK){
                int	result = data.getIntExtra("result",0);
                    offerList.get(requestCode).setFavorite(result);
            }
    }

    public	boolean	onCreateOptionsMenu(Menu menu)
    {
//	Inflate the	menu;	this	adds	items	to	the	action	bar	if	it	is	present.
        getMenuInflater().inflate(R.menu.header,	menu);
        return	true;
    }

    @Override
    public void onBackPressed() {
        logout();
    }

    public	boolean	onOptionsItemSelected(MenuItem	item)
    {
//	Handle	action	bar	item	clicks	here.	The	action	bar	will
//	automatically	handle	clicks	on	the	Home/Up	button,	so	long
//	as	you	specify	a	parent	activity	in	AndroidManifest.xml.
        int	id	=	item.getItemId();
        if	(id	==	R.id.sign_out)
        {
            logout();
            return	true;
        }
        else	if(id	==	R.id.resetList)
        {
            offerList.clear();
            Offers offers = new Offers();
            offerList.addAll(offers.getOffers());
            adapterList.notifyDataSetChanged();
            Toast.makeText(this,"List has been reseted",Toast.LENGTH_LONG).show();
            return	true;
        }else	if(id	==	R.id.clearFavorites){
            return true;
        }else if (id == R.id.chat){
            Intent intent = new Intent(this,ChatActivity.class);
            startActivity(intent);
        }
        return	super.onOptionsItemSelected(item);
    }

    public	void	onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo	menuInfo) {

        super.onCreateContextMenu(menu,	v,	menuInfo);
        //	verificăm	dacă	meniul	este	creat	pentru	lista	vizată
        if	(v.getId()==R.id.ListView)
        {
            //	identificăm	elementul	selectat	din	listă
            AdapterView.AdapterContextMenuInfo	info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            int index = info.position;
            AdapterList adapter = (AdapterList) ((ListView) v).getAdapter();
            menu.setHeaderTitle(Objects.requireNonNull(adapter.getItem(index)).getTitlu());
            //	încărcăm	structura	vizuală	a	meniului
            getMenuInflater().inflate(R.menu.menu,	menu);
        }
    }

    public boolean onContextItemSelected(MenuItem item){

        //	accesarea	informației	atașate meniului contextual
        AdapterView.AdapterContextMenuInfo	info	= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        //	identificarea	elementului	selectat	din	meniu,	folosind	ID-urile	predefin
        if(item.getItemId()	==	R.id.add)
        {
            offerList.add(info.position,new Offer("New offer", "Barcelona has many venues for live music and theatre, including the world-renowend Gran Teatre del Liceu Opera.", "300 EUR" , R.drawable.offer_1, 0));
            adapterList.notifyDataSetChanged();
        }
        else	if(item.getItemId()	==	R.id.remove)
        {
            offerList.remove(info.position);
            adapterList.notifyDataSetChanged();
//            finish();
        }
        return	super.onContextItemSelected(item);
    }
    public void logout() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Confirm")
                .setMessage("Please confirm logout intention?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}

