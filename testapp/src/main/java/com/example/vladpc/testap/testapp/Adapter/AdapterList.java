package com.example.vladpc.testap.testapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.vladpc.testap.testapp.Models.Offer;
import com.example.vladpc.testap.testapp.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterList extends ArrayAdapter<Offer> {
    private Context context;
    private List<Offer> offers = new ArrayList<>();

    public AdapterList(@NonNull Context context, List<Offer> offers) {
        super(context, 0, offers);
        this.context = context;
        this.offers = offers;
    }

    public View getView(int	position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater	= (LayoutInflater)	context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow	=	(convertView	==	null)	? Objects.requireNonNull(inflater).inflate(R.layout.element_list_offers,	parent,	false)	:	convertView;

        TextView titlu = myRow.findViewById(R.id.titlu);
        titlu.setText(offers.get(position).getTitlu());
        TextView descriere = myRow.findViewById(R.id.descriere);
        descriere.setText(offers.get(position).getDescriere());
        TextView pret = myRow.findViewById(R.id.pret);
        pret.setText(offers.get(position).getPret());
        ImageView image = myRow.findViewById(R.id.imageView);
        image.setImageResource(offers.get(position).getImagine());
        return	myRow;
    }
}
