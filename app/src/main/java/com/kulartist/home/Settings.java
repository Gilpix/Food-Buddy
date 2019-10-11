package com.kulartist.home;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

import com.kulartist.foodbuddy.R;

//extends our custom BaseActivity
public class Settings extends Home {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_matching);


        //dynamically include the  current activity      layout into  baseActivity layout.now all the view of baseactivity is   accessible in current activity.

        getLayout(R.layout.activity_settings);
        getMenuIcon(R.drawable.settings,R.id.settings);
        setActionBarTitle("Settings");


        //get the reference of RadioGroup.


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Settings.this, Buddies.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    public void rateUs(View view) {

        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }

    }


    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }


    public void shareUs(View view) {

        Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Install This Application");
        String app_url = "https://play.google.com/store/apps/details?id=com.vid_lancer.trafficroadsignscanada";
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
        startActivity(Intent.createChooser(shareIntent, "Share via"));

    }
}