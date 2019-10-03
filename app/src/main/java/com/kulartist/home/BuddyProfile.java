package com.kulartist.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.kulartist.foodbuddy.CommonMethods;
import com.kulartist.foodbuddy.R;

public class BuddyProfile extends CommonMethods {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buddy_profile);


        setActionBarTitle("Buddy Profile");



    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, Buddies.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void chatWithBuddy(View view) {
        Intent in = new Intent(BuddyProfile.this, Messages.class);
        startActivity(in);
        //finish();
    }

    public void sendRequest(View view) {

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(BuddyProfile.this,R.style.AlertDialogCustom);
        //AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);


        dlgAlert.setMessage("Your invitation has been sent to your buddy");
        dlgAlert.setTitle("Invitation sent...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

        dlgAlert.setPositiveButton("DISMISS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(BuddyProfile.this, Buddies.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
