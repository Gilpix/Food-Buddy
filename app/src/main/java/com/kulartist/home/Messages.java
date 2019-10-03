package com.kulartist.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.kulartist.foodbuddy.CommonMethods;
import com.kulartist.foodbuddy.R;

public class Messages extends CommonMethods {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);


        setActionBarTitle("Chat With Your Buddy");

    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, BuddyProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
