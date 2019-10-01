package home;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kulartist.foodbuddy.R;

//extends our custom BaseActivity
public class Settings extends Home {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_matching);


        //dynamically include the  current activity      layout into  baseActivity layout.now all the view of baseactivity is   accessible in current activity.

        getLayout(R.layout.activity_settings);
        getMenuIcon(R.drawable.settings_gray,R.id.settings);
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


}