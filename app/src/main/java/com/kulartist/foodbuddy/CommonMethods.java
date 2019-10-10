package com.kulartist.foodbuddy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kulartist.database_connection.DatabaseConnection;
import com.kulartist.home.Buddies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class CommonMethods extends AppCompatActivity {

    public static String currentUserId;
    //    ProgressDialog progressDialog=new ProgressDialog(this);
    private boolean userExist;
    DatabaseConnection connectionClass = new DatabaseConnection();


    public void setActionBarTitle(String title) {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.LEFT);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(textView);


    }

    public static void hideSoftKeyboard(Activity activity) {

        try {


            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e)
        {

        }
    }


    public void setUserExist(boolean exist)
    {
        this.userExist=exist;
    }

    public boolean getUserExist()
    {
        return userExist;
    }

    public boolean checkUserId(String emailchk)
    {

        DoCheckUserId doCheckUserId=new DoCheckUserId(emailchk);
        doCheckUserId.execute();
        Toast.makeText(getBaseContext(),getUserExist()+"  ? "+doCheckUserId.getIsSucess(),Toast.LENGTH_LONG).show();
       // return userExist;
       // if(doCheckUserId.z.equals("User Found"))
            //return getUserExist();
        return doCheckUserId.getIsSucess();

    }





    public class DoCheckUserId extends AsyncTask<String,String,String> {

        String emailstr;
        //CommonMethods commonMethods=new CommonMethods();



        public DoCheckUserId(String email)
        {
            this.emailstr=email;
            setUserExist(false);
        }



        String z="";
        boolean isSuccess=false;

        public boolean getIsSucess()
        {
            return this.isSuccess;
        }


        String em;


        @Override
        protected void onPreExecute() {


            //progressDialog.setMessage("Loading...");
            //progressDialog.show();


            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            setUserExist(false);
            if( emailstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {


                        String query=" select userId from userRegistered";


                        Statement stmt = con.createStatement();


                        ResultSet rs=stmt.executeQuery(query);

                        while (rs.next())

                        {
                            //nm= rs.getString(1);
                            em=rs.getString("userId");




                            if(em.equals(emailstr))
                            {
                                setUserExist(true);

                                isSuccess=true;
                                z = "User Found";
                                userExist=true;
                                break;
                            }

                            else {

                                isSuccess = false;
                                z = "User does not exist ";
                            }



                        }


                        stmt.close();
                        rs.close();
                    }
                    con.close();

                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;        }

        @Override
     protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),isSuccess+" 0 "+z,Toast.LENGTH_LONG).show();
            if(isSuccess)
                setUserExist(true);


            //progressDialog.hide();

        }
    }



}

