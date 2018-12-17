package com.example.vladpc.testap.testapp.Models;

import com.example.vladpc.testap.testapp.R;

import java.util.ArrayList;
import java.util.List;

public class Offers {
    private List<Offer> offers;

    public List<Offer> getOffers() {
        this.offers = new ArrayList<Offer>();
        this.offers.add(new Offer("Barcelona, 3 nights", "Barcelona has many venues for live music and theatre, including the world-renowend Gran Teatre del Liceu Opera.", "300 EUR" , R.drawable.offer_1, 0));
        this.offers.add(new Offer("Maldive, 7 nights", "The fisrt Maldivians did not leave any archaeological artifacts.", "1050 EUR" , R.drawable.offer_2, 0));
        this.offers.add(new Offer("Thailand, 10 nights", "The Andaman Sea is a precious natural resource as it hosts the most popular and luxurious resorts in Asia." , "1250 EUR", R.drawable.offer_3, 0));
        return offers;
    }
}
