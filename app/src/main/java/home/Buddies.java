package home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kulartist.foodbuddy.R;

public class Buddies extends Home {
    LinearLayout dynamicContent,bottonNavBar;

    ListView simpleList;

    String signsDescription[] = {
            "Kuldeep.",
            "Jack",
            "John",
            "Jerry",
            "Janar",
            "Kana",
            "Kani",
            "Kaanu",
            "Kama",
            "Karma",
            "Karmi",
            "Bhan",
            "Kod",
            "Bhopdi"};

    String restaurantTiming[] = {
            "Available",
            "Available",
            "Available",
            "Available",
            "Available",
            "Available",
            "Available",
            "Available",
            "Available",
            "Available",
            "Available",
            "Not Available",
            "Not Available",
            "Not Available"};

    int signsImage[] = {R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya,
            R.drawable.user_pratikshya};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_rates);



        dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        bottonNavBar= (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_buddies, null);
        dynamicContent.addView(wizard);


        setActionBarTitle("Buddies");



        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb=(RadioButton)findViewById(R.id.buddies);
        rb.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.home_gray, 0,0);
        rb.setTextColor(Color.parseColor("#cccccc"));




        simpleList = (ListView)findViewById(R.id.simpleListView);
        RegulatoryCustomAdapter customAdapter = new RegulatoryCustomAdapter(getApplicationContext(), signsDescription,restaurantTiming, signsImage);
        simpleList.setAdapter(customAdapter);




        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "selected Item Name is " , Toast.LENGTH_LONG).show();


                Intent in = new Intent(Buddies.this, BuddyProfile.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                //finish();

                switch(position+1)
                {
                    case 1:
                       // Intent b = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/guide/components/activities/activity-lifecycle"));
                       // startActivity(b);
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
}