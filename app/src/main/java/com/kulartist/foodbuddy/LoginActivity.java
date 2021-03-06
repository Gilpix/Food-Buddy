package com.kulartist.foodbuddy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kulartist.database_connection.DatabaseConnection;
import com.kulartist.home.Buddies;
import com.kulartist.home.Restaurant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private EditText mEmailView;
    private EditText mPasswordView;

    String email,password;

    ProgressDialog progressDialog;
    com.kulartist.database_connection.DatabaseConnection connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = findViewById(R.id.email);

        mPasswordView = findViewById(R.id.password);





         email = mEmailView.getText().toString();
         password = mPasswordView.getText().toString();


        connectionClass = new com.kulartist.database_connection.DatabaseConnection();

        progressDialog=new ProgressDialog(this);


//
//        Intent intent=new Intent(LoginActivity.this, Restaurant.class);
//        startActivity(intent);



    }







   public void signIn(View view) {


       CommonMethods.hideSoftKeyboard(this);


       Dologin dologin=new Dologin();
       dologin.execute();

       /* if(mEmailView.getText().toString().equals("a") && mPasswordView.getText().toString().equals("a"))
        {
            Intent i = new Intent(LoginActivity.this, Buddies.class);
            startActivity(i);
            finish();
        }
        else
       {
           AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialogCustom);
           //AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);


           dlgAlert.setMessage("You have entered wrong password or username");
           dlgAlert.setTitle("Error Message...");
           dlgAlert.setPositiveButton("OK", null);
           dlgAlert.setCancelable(true);
           dlgAlert.create().show();

           dlgAlert.setPositiveButton("DISMISS",
                   new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int which)
                       {

                       }});
       }
           // Toast.makeText(this,password+" Wrong "+email,Toast.LENGTH_LONG).show();

        */
    }

    public void signUp(View view) {
        Intent i =new Intent(LoginActivity.this, Registration.class);
        startActivity(i);
        //finish();
    }

    public void resetPasswordAuth(View view) {
        Intent i =new Intent(LoginActivity.this,ResetPassAuthenticate.class);
        startActivity(i);
        //finish();


    }






    public class Dologin extends AsyncTask<String,String,String> {


        String emailstr=mEmailView.getText().toString();
        String passstr=mPasswordView.getText().toString();
        String z="";
        boolean isSuccess=false;

        String em,password;


        @Override
        protected void onPreExecute() {


            progressDialog.setMessage("Loading...");
            progressDialog.show();


            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if( emailstr.trim().equals("") ||passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {


                        String query=" select userId,userPassword from userRegistered";


                        Statement stmt = con.createStatement();


                        ResultSet rs=stmt.executeQuery(query);

                        while (rs.next())

                        {
                            //nm= rs.getString(1);
                            em=rs.getString(1);
                            password=rs.getString(2);




                            if(em.equals(emailstr)&&password.equals(passstr))
                            {

                                isSuccess=true;
                                z = "Login successfull";
                                CommonMethods.currentUserId=emailstr;
                                break;

                            }

                            else {

                                isSuccess = false;
                                z = "Login Unsuccessfull "+em+password;
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
           // Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {

                Intent intent=new Intent(LoginActivity.this,Buddies.class);




                startActivity(intent);
            }
            else
            {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialogCustom);
                //AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);


                dlgAlert.setMessage("You have entered wrong password or username");
                dlgAlert.setTitle("Error Message...");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();

                dlgAlert.setPositiveButton("DISMISS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }});
            }

        }


            //progressDialog.hide();

        }
    }

