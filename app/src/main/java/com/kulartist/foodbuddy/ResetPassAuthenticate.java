package com.kulartist.foodbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResetPassAuthenticate extends CommonMethods {
    //boolean codeVerified=true;
    String restPassEmail;
    EditText emailPassToReset;
    ProgressDialog progressDialog;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_authenticate);


        emailPassToReset=findViewById(R.id.email_pass_reset);




        setActionBarTitle("Validate User");
        progressDialog=new ProgressDialog(this);




    }



    public void resetPasswordAuthenticate(View view) {
        CommonMethods.hideSoftKeyboard(this);
        restPassEmail=emailPassToReset.getText().toString();


        DoCheckUserId doCheckUserId=new DoCheckUserId(restPassEmail);
        doCheckUserId.execute();

        //restPassEmail=emailPassToReset.getText().toString();
        //Toast.makeText(getBaseContext(),restPassEmail+" -- "+checkUserId(restPassEmail),Toast.LENGTH_LONG).show();




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CommonMethods.hideSoftKeyboard(this);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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


            progressDialog.setMessage("Loading...");
            progressDialog.show();


            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            setUserExist(false);
            if( emailstr.trim().equals("")) {
                z = "Please enter all fields....";
               // Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            }
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                        //Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
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
                                //Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
                                //userExist=true;
                                break;
                            }

                            else {

                                isSuccess = false;
                                z = "User does not exist ";
                                //Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
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
                    //Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
                }
            }
            return z;        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext()," Status : "+z,Toast.LENGTH_LONG).show();
            if(isSuccess==true)
            {

                    Intent i = new Intent(ResetPassAuthenticate.this, ResetPassword.class);
                    i.putExtra("restPassEmail", restPassEmail);
                //Toast.makeText(getBaseContext(),isSuccess+" 1 "+z,Toast.LENGTH_LONG).show();

                startActivity(i);
                    finish();

            }

            progressDialog.hide();

        }
    }



}

