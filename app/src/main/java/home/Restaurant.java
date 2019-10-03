package home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kulartist.foodbuddy.R;

//extends our custom BaseActivity
public class Restaurant extends Home {

    TextView headerText;
    ImageButton backButton;
    ListView simpleList;

    String signsDescription[] = {
            "Pizza.",
            "Burger",
            "Parantha",
            "Parantha",
            "Parantha",
            "Parantha",
            "Parantha",
            "Parantha",
            "Parantha",
            "Parantha",
            "Parantha",
            "Parantha",
            "Pizza",
            "Beer"};

    String restaurantTiming[] = {
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM",
            "11:00 AM - 9:00 PM"};

    int signsImage[] = {R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza,
            R.drawable.pizza};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_matching);


        getLayout(R.layout.activity_restaurant);
        getMenuIcon(R.drawable.rest,R.id.restaurents);
        setActionBarTitle("Restaurant");





        simpleList = (ListView)findViewById(R.id.simpleListView);
        RegulatoryCustomAdapter customAdapter = new RegulatoryCustomAdapter(getApplicationContext(), signsDescription,restaurantTiming, signsImage);
        simpleList.setAdapter(customAdapter);




        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "selected Item Name is " , Toast.LENGTH_LONG).show();


                 Intent in = new Intent(Restaurant.this, BuddyProfile.class);
                startActivity(in);
                finish();

                switch(position+1)
                {
                    case 1:
                        //Intent b = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/guide/components/activities/activity-lifecycle"));
                        //startActivity(b);
                        break;


                    case 5:
                       // Intent c = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/reference/android/content/Intent"));
                        //startActivity(c);


                        break;

                    default:
                        break;


                }


            }
        });







    }

    public void viewRestaurantDetails(View view) {

       // Intent in = new Intent(Restaurant.this, BuddyProfile.class);
        //startActivity(in);
        //finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Restaurant.this, Buddies.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}