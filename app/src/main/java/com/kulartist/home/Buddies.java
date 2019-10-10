package com.kulartist.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kulartist.database_connection.DatabaseConnection;
import com.kulartist.foodbuddy.CommonMethods;
import com.kulartist.foodbuddy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Buddies extends Home {
    LinearLayout dynamicContent,bottonNavBar;
    String connStatus="";
    JSONObject mainObject=new JSONObject();
    JSONArray mainArray=new JSONArray();


    ProgressDialog progressDialog;
    DatabaseConnection connectionClass;




    ListView simpleList;


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






        connectionClass = new DatabaseConnection();
        progressDialog=new ProgressDialog(this);



//        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb=(RadioButton)findViewById(R.id.buddies);
        rb.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.home, 0,0);
        rb.setTextColor(Color.parseColor("#cccccc"));


        displayBuddiesList();




    }




    public void displayBuddiesList()
    {

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                connStatus = "Please check your internet connection";
            } else {



               // String query=" select * from userRegistered";

                String query="SELECT userName,userRegistered.userId,age,userBio,userGender,availability FROM `userRegistered` \n" +
                        " JOIN userAvailability \n" +
                        " ON userAvailability.userId=userRegistered.userId where availability=1 and " +
                        " userRegistered.userId !=?";

                PreparedStatement stm = con.prepareStatement(query);
                stm.setString(1,CommonMethods.currentUserId);

                ResultSet rs=stm.executeQuery();

                while(rs.next()) {
                    mainObject=new JSONObject();
                     //usr_name = rs.getString("userName");
                    mainObject.accumulate("userName",rs.getString("userName"));
                    mainObject.accumulate("userId",rs.getString("userId"));
                    mainObject.accumulate("age",rs.getString("age"));
                    mainObject.accumulate("userBio",rs.getString("userBio"));
                    mainObject.accumulate("userGender",rs.getString("userGender"));
                    mainObject.accumulate("userAvailability",rs.getString("availability"));

                    mainArray.put(mainObject);

                }


                stm.close();
                rs.close();
                getRecyclerData(mainArray);
            }
            con.close();

        }
        catch (Exception ex)
        {

            Toast.makeText(getBaseContext(),"Status : "+ex.getMessage()+"--- "+connStatus,Toast.LENGTH_LONG).show();
        }


    }








    public void getRecyclerData(final  JSONArray mainArray) throws JSONException {



        final String[] userName=new String[mainArray.length()];
        final String[] userId=new String[mainArray.length()];
        String[] age=new String[mainArray.length()];
        String[] userBio=new String[mainArray.length()];
        String[] userGender=new String[mainArray.length()];
        String[] userAvailability=new String[mainArray.length()];
        for(int j=0;j<mainArray.length();j++)
        {
            JSONObject a =new JSONObject();
            a=mainArray.getJSONObject(j);
            userName[j]=a.getString("userName");
            userId[j]=a.getString("userId");
            age[j]=a.getString("age");
            userBio[j]=a.getString("userBio");
            userGender[j]=a.getString("userGender");
            if(a.getString("userAvailability").equals("1"))
                userAvailability[j]="Available";
            else
                userAvailability[j]="Not Available";



        }







        simpleList = (ListView)findViewById(R.id.simpleListView);
        RecyclerCustomAdapter customAdapter = new RecyclerCustomAdapter(getApplicationContext(), userName,userAvailability, signsImage);
        simpleList.setAdapter(customAdapter);




        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "selected Item Name is " , Toast.LENGTH_LONG).show();


                Intent in = new Intent(Buddies.this, BuddyProfile.class);
                in.putExtra("uName",userName[position]);
                in.putExtra("userId",userId[position]);
                try {
                    in.putExtra("buddyProfileObject",mainArray.getJSONObject(position).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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













    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Confirm Exit..!!!");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.food_buddy);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Are you sure, You want to exit");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i=new Intent();
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                finishAffinity();
                finish();
            }
        });

       alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(Buddies.this,"You clicked over No",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(),"You clicked on Cancel",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }











    }