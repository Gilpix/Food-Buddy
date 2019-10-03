package com.kulartist.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kulartist.foodbuddy.R;

public class Notification extends Home {

    LinearLayout dynamicContent,bottonNavBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_deals);


        getLayout(R.layout.activity_notification);
        getMenuIcon(R.drawable.notification,R.id.notification);
        setActionBarTitle("Notifications");





    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Notification.this, Buddies.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}