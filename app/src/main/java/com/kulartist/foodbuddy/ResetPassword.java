package com.kulartist.foodbuddy;

import android.app.Activity;
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

import com.kulartist.database_connection.DatabaseConnection;
import com.kulartist.home.Buddies;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResetPassword extends CommonMethods  {


   public EditText newPass,confPass;

    DatabaseConnection connectionClass;
    ProgressDialog progressDialog;
    String restPassEmail;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        newPass =findViewById(R.id.newpassword);
        confPass=findViewById(R.id.confirmPassword);

        connectionClass = new com.kulartist.database_connection.DatabaseConnection();
        progressDialog=new ProgressDialog(this);


        setActionBarTitle("Reset Password");


        restPassEmail= getIntent().getStringExtra("restPassEmail");




    }







    public void resetPassword(View view) {
        CommonMethods.hideSoftKeyboard(this);
/*
        Intent i =new Intent(ResetPassword.this,LoginActivity.class);
        Toast.makeText(this," Password Reset Sucessfully ",Toast.LENGTH_LONG).show();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

        finish();
  */

        restPassEmail= getIntent().getStringExtra("restPassEmail");
    DoResetPassword doResetPassword= new DoResetPassword();
    doResetPassword.execute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CommonMethods.hideSoftKeyboard(this);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, ResetPassAuthenticate.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public class DoResetPassword extends AsyncTask<String,String,String>
    {

        ResetPassword rp=new ResetPassword();
        String newPassString=newPass.getText().toString();
        String conPassString=confPass.getText().toString();
        String back_result;
        boolean isResetSuccess=false;



        @Override
        protected void onPreExecute() {


            progressDialog.setMessage("Loading...");
            progressDialog.show();


            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params)
        {
            try {


                if(newPassString.trim().equals("") || conPassString.trim().equals("")) {
                    if (!newPassString.trim().equals(conPassString.trim()))
                        back_result = "Both Passwords do not match";
                    else
                        back_result="Please enter important fields";
                }


                else  if (!newPassString.trim().equals(conPassString.trim()))
                    back_result = "Both Passwords do not match";

                else
                {

                    Connection con= connectionClass.CONN();

                    //String query="INSERT INTO userRegistered ('userName', 'userId', 'userPassword', 'age') VALUES" +
                    // " ('"+usr_name+"','"+usr_id+"','"+usr_password+"','"+usr_age+"')";

                    String query="UPDATE userRegistered SET userPassword =? WHERE userId =?";


                    PreparedStatement stm;
                    //stm.executeUpdate(query);

                    stm=con.prepareStatement(query);

                    //ResultSet rs =stm.executeQuery(query);

                    stm.setString(1,newPassString);
                    stm.setString(2,restPassEmail);



                    int a=stm.executeUpdate();
                    if(a>0)
                        isResetSuccess=true;
                    else {

                        isResetSuccess = false;}






                }



            }
            catch (Exception e)
            {
                isResetSuccess = false;
                back_result = "Exceptions"+e;
            }
            return back_result;

        }

        @Override
        protected void onPostExecute(String s) {



            if(isResetSuccess) {

                Intent i =new Intent(ResetPassword.this,LoginActivity.class);
                //Toast.makeText(this,"",Toast.LENGTH_LONG).show();
                Toast.makeText(ResetPassword.this," Pasword Reset Sucessfully",Toast.LENGTH_LONG).show();
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                finish();
            }
            else
                Toast.makeText(getBaseContext(),""+back_result,Toast.LENGTH_LONG).show();


            progressDialog.hide();

        }


    }




}
