package com.kulartist.foodbuddy;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kulartist.home.Buddies;

public class Registration extends CommonMethods {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setActionBarTitle("Sign Up");


    }

    public void SignUpResult(View view) {

        Intent i =new Intent(Registration.this, Buddies.class);
        Toast.makeText(this," Sign Up Sucessfully ",Toast.LENGTH_LONG).show();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();

    }



    public void signInAlreadyAccount(View view) {
        Intent i =new Intent(Registration.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
