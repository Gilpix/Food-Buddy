package com.kulartist.foodbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.kulartist.database_connection.DatabaseConnection;
import com.kulartist.home.Buddies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Registration extends CommonMethods {

    EditText uName,uId,uPassword,uAge,uBio,uMobile,uBestFood;
    RadioButton rMale,rFemale;
    ProgressDialog progressDialog;
    com.kulartist.database_connection.DatabaseConnection connectionClass;
    String gender;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        uName=findViewById(R.id.signup_input_name);
        uId=findViewById(R.id.signup_input_email);
        uPassword=findViewById(R.id.signup_input_password);
        uMobile=findViewById(R.id.signup_input_phone);
        uAge=findViewById(R.id.signup_input_age);
        uBestFood=findViewById(R.id.signup_input_bestfood);
        uBio=findViewById(R.id.sign_usr_bio);
        rMale=findViewById(R.id.male_radio_btn);
        rFemale=findViewById(R.id.female_radio_btn);




        connectionClass = new com.kulartist.database_connection.DatabaseConnection();
        progressDialog=new ProgressDialog(this);






        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setActionBarTitle("Sign Up");


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








    public void SignUpResult(View view) {

        CommonMethods.hideSoftKeyboard(this);

        if(rMale.isChecked())
            gender="M";
        else if(rFemale.isChecked())
            gender="F";



        DoRegister doRegister=new DoRegister();
        doRegister.execute();





        /*
        Intent i =new Intent(Registration.this, Buddies.class);
        Toast.makeText(this," Sign Up Sucessfully ",Toast.LENGTH_LONG).show();
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
        */

    }












    public void signInAlreadyAccount(View view) {
        CommonMethods.hideSoftKeyboard(this);
        Intent i =new Intent(Registration.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }






    public class DoRegister extends AsyncTask<String,String,String>
    {

        String usr_name=uName.getText().toString();
        String usr_id=uId.getText().toString();
        String usr_password=uPassword.getText().toString();
        String usr_phone=uMobile.getText().toString();
        String usr_bestFood=uBestFood.getText().toString();
        int usr_age=Integer.parseInt(uAge.getText().toString());
        String usr_bio=uBio.getText().toString();
        String back_result;
        //String usrGender;
        boolean isSuccess=false;



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


                if(usr_name.trim().equals("") || usr_id.trim().equals("") || usr_password.trim().equals("") || usr_age<19)
                back_result="Please enter important fields";



            else
            {

                Connection con= connectionClass.CONN();

                //String query="INSERT INTO userRegistered ('userName', 'userId', 'userPassword', 'age') VALUES" +
                       // " ('"+usr_name+"','"+usr_id+"','"+usr_password+"','"+usr_age+"')";

                String query="INSERT INTO userRegistered VALUES (?,?,?,?,?,?,?,?)";
                String query1="INSERT INTO userAvailability(userId) VALUES (?)";


                PreparedStatement stm,stm1;
                //stm.executeUpdate(query);

                stm=con.prepareStatement(query);
                stm1=con.prepareStatement(query1);

                //ResultSet rs =stm.executeQuery(query);
                stm.setString(1,usr_name);
                stm.setString(2,usr_id);
                stm.setString(3,usr_password);
                stm.setString(4,usr_phone);
                stm.setInt(5,usr_age);
                stm.setString(6,usr_bestFood);
                stm.setString(7,usr_bio);
                stm.setString(8,gender);


                stm1.setString(1,usr_id);



                int a=stm.executeUpdate();
                int b=stm1.executeUpdate();
                if(a>0)
                    isSuccess=true;
                else {

                    isSuccess = false;}






            }



        }
        catch (Exception e)
        {
            isSuccess = false;
            back_result = "Exceptions"+e;
        }
            return back_result;


    }


        @Override
        protected void onPostExecute(String s) {



            if(isSuccess) {
                Toast.makeText(getBaseContext(),"Sign Up Sucessfull",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(Registration.this,Buddies.class);
                CommonMethods.currentUserId=usr_id;




                startActivity(intent);
            }


            progressDialog.hide();

        }



}}
