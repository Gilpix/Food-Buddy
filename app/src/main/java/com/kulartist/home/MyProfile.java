package com.kulartist.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kulartist.database_connection.DatabaseConnection;
import com.kulartist.foodbuddy.CommonMethods;
import com.kulartist.foodbuddy.LoginActivity;
import com.kulartist.foodbuddy.R;
import com.kulartist.foodbuddy.Registration;
import com.kulartist.foodbuddy.ResetPassAuthenticate;
import com.kulartist.foodbuddy.ResetPassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyProfile extends Home {
      EditText email, phone, bio, age;
    TextView edit_save;
    EditText name,best_food;
    String usr_name,usr_bioo,usr_age,z,usr_phone,usr_best_food;

    ProgressDialog progressDialog;
    DatabaseConnection connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listing);


        getLayout(R.layout.activity_my_profile);
        getMenuIcon(R.drawable.profile,R.id.my_profile);
        setActionBarTitle("My Profile");


        name=findViewById(R.id.user_profile_name);
        email = findViewById(R.id.my_email_text);
        phone = findViewById(R.id.my_phone_text);
        age = findViewById(R.id.my_dob_text);
        bio = findViewById(R.id.my_bio_text);
        best_food=findViewById(R.id.user_profile_best_food);

        edit_save = findViewById(R.id.edit_profile_text);

        connectionClass = new DatabaseConnection();

        progressDialog=new ProgressDialog(this);


        email.setText(CommonMethods.currentUserId);
        displayUserProfile(email.getText().toString());


        setProfileDisabled();



    }


    public void displayUserProfile(String userId)
    {

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {


                String query=" select * from userRegistered where userId =? ";

                PreparedStatement stm = con.prepareStatement(query);
                stm.setString(1,userId);

                ResultSet rs=stm.executeQuery();

                while(rs.next()) {
                    usr_name = rs.getString("userName");
                    usr_age = rs.getString("age");
                    usr_bioo = (rs.getString("userBio"));
                    //usr = (rs.getString("userGender"));
                    usr_phone = (rs.getString("mobile_no"));
                    usr_best_food=(rs.getString("best_food"));
                }

               name.setText(usr_name);
               phone.setText(usr_phone);
               age.setText(usr_age);
                bio.setText(usr_bioo);
                best_food.setText("I love "+usr_best_food);

                stm.close();
                rs.close();
            }
            con.close();

        }
        catch (Exception ex)
        {

            Toast.makeText(getBaseContext(),"Status : "+ex.getMessage()+"/n "+z,Toast.LENGTH_LONG).show();
        }


    }





    public void editUserProfile(String userId)
    {

       String change_name= name.getText().toString();
        String change_age= age.getText().toString();
        String change_bio= bio.getText().toString();
        String change_phone= phone.getText().toString();



        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {


                String query=" UPDATE userRegistered SET userName=?, age=?, userBio =? WHERE userId=? ";

                PreparedStatement stm = con.prepareStatement(query);

                stm.setString(1,change_name);
                stm.setString(4,userId);
                stm.setString(3,change_bio);
                stm.setString(2,change_age);

                int s =stm.executeUpdate();


                stm.close();
                //rs.close();
            }
            con.close();

        }
        catch (Exception ex)
        {

            Toast.makeText(getBaseContext(),"Status : "+ex.getMessage()+"/n "+z,Toast.LENGTH_LONG).show();
        }


    }






    public void editProfileEnabled() {


        name.setEnabled(true);
        name.setClickable(true);
        name.setFocusable(true);
        name.setFocusableInTouchMode(true);


        best_food.setEnabled(true);
        best_food.setClickable(true);
        best_food.setFocusable(true);
        best_food.setFocusableInTouchMode(true);



        email.setEnabled(true);
        email.setClickable(true);
        email.setFocusable(true);
        email.setFocusableInTouchMode(true);


        phone.setEnabled(true);
        phone.setClickable(true);
        phone.setFocusable(true);
        phone.setFocusableInTouchMode(true);
        phone.setBackgroundResource(android.R.color.transparent);

        age.setEnabled(true);
        age.setClickable(true);
        age.setFocusable(true);
        age.setFocusableInTouchMode(true);
        age.setBackgroundResource(android.R.color.transparent);

        bio.setEnabled(true);
        bio.setClickable(true);
        bio.setFocusable(true);
        bio.setFocusableInTouchMode(true);
        bio.setBackgroundResource(android.R.color.transparent);


    }


    public void setProfileDisabled() {
        //edit_save.setText("edit");


        name.setEnabled(false);
        name.setClickable(false);
        name.setFocusable(false);
        name.setFocusableInTouchMode(false);
        name.setBackgroundResource(android.R.color.transparent);



        best_food.setEnabled(false);
        best_food.setClickable(false);
        best_food.setFocusable(false);
        best_food.setFocusableInTouchMode(false);
        best_food.setBackgroundResource(android.R.color.transparent);




        email.setEnabled(false);
        email.setClickable(false);
        email.setFocusable(false);
        email.setFocusableInTouchMode(false);
        email.setBackgroundResource(android.R.color.transparent);


        phone.setEnabled(false);
        phone.setClickable(false);
        phone.setFocusable(false);
        phone.setFocusableInTouchMode(false);
        phone.setBackgroundResource(android.R.color.transparent);

        age.setEnabled(false);
        age.setClickable(false);
        age.setFocusable(false);
        age.setFocusableInTouchMode(false);
        age.setBackgroundResource(android.R.color.transparent);

        bio.setEnabled(false);
        bio.setClickable(false);
        bio.setFocusable(false);
        bio.setFocusableInTouchMode(false);
        bio.setBackgroundResource(android.R.color.transparent);


    }


    public void editSaveProfile(View view) {


        edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (email.isFocusable() == true) {
                    edit_save.setText("edit");
                    editUserProfile(CommonMethods.currentUserId);
                    setProfileDisabled();
                    Toast.makeText(MyProfile.this, " Profile Saved ", Toast.LENGTH_LONG).show();
                } else if (email.isFocusable() == false) {
                    edit_save.setText("save");
                    editProfileEnabled();
                }
            }
        });

    }

    public void signOut(View view) {

        CommonMethods.currentUserId="";

        Toast.makeText(MyProfile.this, " Sign Out ", Toast.LENGTH_LONG).show();

        Intent in = new Intent(MyProfile.this, LoginActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MyProfile.this, Buddies.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }














}

