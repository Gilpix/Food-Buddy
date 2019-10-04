package com.kulartist.foodbuddy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
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

    EditText uName,uId,uPassword,uAge,uBio;
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
        uAge=findViewById(R.id.signup_input_age);
        uBio=findViewById(R.id.sign_usr_bio);
        rMale=findViewById(R.id.male_radio_btn);
        rFemale=findViewById(R.id.female_radio_btn);


        if(rMale.isChecked())
            gender="M";
        else
            gender="F";

        connectionClass = new com.kulartist.database_connection.DatabaseConnection();
        progressDialog=new ProgressDialog(this);






        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setActionBarTitle("Sign Up");


    }

    public void SignUpResult(View view) {

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


                if(usr_name.trim().equals("") && usr_id.trim().equals("") && usr_password.trim().equals(""))
                back_result="Please enter important fields";



            else
            {

                Connection con= connectionClass.CONN();

                //String query="INSERT INTO userRegistered ('userName', 'userId', 'userPassword', 'age') VALUES" +
                       // " ('"+usr_name+"','"+usr_id+"','"+usr_password+"','"+usr_age+"')";

                String query="INSERT INTO userRegistered VALUES (?,?,?,?,?,?)";


                PreparedStatement stm;
                //stm.executeUpdate(query);

                stm=con.prepareStatement(query);

                //ResultSet rs =stm.executeQuery(query);
                stm.setString(1,usr_name);
                stm.setString(2,usr_id);
                stm.setString(3,usr_password);
                stm.setInt(4,usr_age);
                stm.setString(5,usr_bio);
                stm.setString(6,gender);


                int a=stm.executeUpdate();
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
            Toast.makeText(getBaseContext(),""+back_result,Toast.LENGTH_LONG).show();


            if(isSuccess) {

                Intent intent=new Intent(Registration.this,Buddies.class);




                startActivity(intent);
            }


            progressDialog.hide();

        }



}}
