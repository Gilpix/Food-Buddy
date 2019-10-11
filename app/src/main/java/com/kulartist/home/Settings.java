package com.kulartist.home;


import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

import com.kulartist.database_connection.DatabaseConnection;
import com.kulartist.foodbuddy.CommonMethods;
import com.kulartist.foodbuddy.R;
import com.kulartist.foodbuddy.Registration;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Settings extends Home {

    Switch availability,notification;
    String connStatus="";

    ProgressDialog progressDialog;
    DatabaseConnection connectionClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_matching);


        getLayout(R.layout.activity_settings);
        getMenuIcon(R.drawable.settings,R.id.settings);
        setActionBarTitle("Settings");



        availability=findViewById(R.id.available_switch);
        notification=findViewById(R.id.notifications_switch);

        connectionClass = new DatabaseConnection();
        progressDialog=new ProgressDialog(this);



        getAvailNotifData();




    }

    private void getAvailNotifData() {

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                connStatus = "Please check your internet connection";
            } else {



                // String query=" select * from userRegistered";

                String query="SELECT * FROM userSettings where userId=?";

                PreparedStatement stm = con.prepareStatement(query);
                stm.setString(1, CommonMethods.currentUserId);

                ResultSet rs=stm.executeQuery();

                while(rs.next()) {
                    if (rs.getString("availability").equals("1"))
                        availability.setChecked(true);
                    else
                        availability.setChecked(false);

                    if (rs.getString("notifications").equals("1"))
                        notification.setChecked(true);
                    else
                        notification.setChecked(false);
                }





                stm.close();
                rs.close();

            }
            con.close();

        }
        catch (Exception ex)
        {

            Toast.makeText(getBaseContext(),"Status : "+ex.getMessage()+"--- "+connStatus,Toast.LENGTH_LONG).show();
        }


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

    public void changeAvailability(View view) {

        String setAvailability;
        if(availability.isChecked())
            setAvailability="1";
        else
            setAvailability="0";

        changeAvailabilityClass changeAvailabilityClass=new changeAvailabilityClass(setAvailability);
        changeAvailabilityClass.execute();



    }


    public void changeNotifications(View view) {


        String setNotifications;
        if(availability.isChecked())
            setNotifications="1";
        else
            setNotifications="0";


        changeNotificationClass changeNotificationClass=new changeNotificationClass(setNotifications);
        changeNotificationClass.execute();

    }







    public class changeAvailabilityClass extends AsyncTask<String,String,String>
    {

        String setAvailability;


        public changeAvailabilityClass(String Availability) {
            setAvailability= Availability;
        }



        @Override
        protected String doInBackground(String... params)
        {




            try {
                Connection con = connectionClass.CONN();
                if (con == null) {
                    connStatus = "Please check your internet connection";
                } else {




                    // String query=" select * from userRegistered";

                    String query="UPDATE userSettings SET availability=? where userId=?";

                    PreparedStatement stm = con.prepareStatement(query);
                    stm.setString(1, setAvailability);
                    stm.setString(2, CommonMethods.currentUserId);

                    int a=stm.executeUpdate();

                    stm.close();


                }
                con.close();

            }
            catch (Exception ex)
            {

                Toast.makeText(getBaseContext(),"Status : "+ex.getMessage()+"--- "+connStatus,Toast.LENGTH_LONG).show();
            }

            return connStatus;


        }





    }










    public class changeNotificationClass extends AsyncTask<String,String,String>
    {

        String setNotification;


        public changeNotificationClass(String notification) {
            setNotification= notification;
        }



        @Override
        protected String doInBackground(String... params)
        {




            try {
                Connection con = connectionClass.CONN();
                if (con == null) {
                    connStatus = "Please check your internet connection";
                } else {




                    // String query=" select * from userRegistered";

                    String query="UPDATE userSettings SET notifications=? where userId=?";

                    PreparedStatement stm = con.prepareStatement(query);
                    stm.setString(1, setNotification);
                    stm.setString(2, CommonMethods.currentUserId);

                    int a=stm.executeUpdate();

                    stm.close();


                }
                con.close();

            }
            catch (Exception ex)
            {

                Toast.makeText(getBaseContext(),"Status : "+ex.getMessage()+"--- "+connStatus,Toast.LENGTH_LONG).show();
            }

            return connStatus;


        }





    }





}



