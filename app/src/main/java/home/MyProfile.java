package home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kulartist.foodbuddy.LoginActivity;
import com.kulartist.foodbuddy.R;


public class MyProfile extends Home {
    LinearLayout dynamicContent, bottonNavBar;
    EditText email, phone, usr_bio, age;
    TextView edit_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listing);


        getLayout(R.layout.activity_my_profile);
        getMenuIcon(R.drawable.profile__gray,R.id.my_profile);
        setActionBarTitle("My Profile");


        email = findViewById(R.id.email_text);
        phone = findViewById(R.id.phone_text);
        age = findViewById(R.id.dob_text);
        usr_bio = findViewById(R.id.user_bio_text);
        edit_save = findViewById(R.id.edit_profile_text);


        setProfileDisabled();


    }


    public void editProfileEnabled() {


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

        usr_bio.setEnabled(true);
        usr_bio.setClickable(true);
        usr_bio.setFocusable(true);
        usr_bio.setFocusableInTouchMode(true);
        usr_bio.setBackgroundResource(android.R.color.transparent);


    }


    public void setProfileDisabled() {
        //edit_save.setText("edit");

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

        usr_bio.setEnabled(false);
        usr_bio.setClickable(false);
        usr_bio.setFocusable(false);
        usr_bio.setFocusableInTouchMode(false);
        usr_bio.setBackgroundResource(android.R.color.transparent);


    }


    public void editSaveProfile(View view) {


        edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (email.isFocusable() == true) {
                    edit_save.setText("edit");
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