package com.kulartist.foodbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import home.Buddies;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
//    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private TextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (TextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

 //       mLoginFormView = findViewById(R.id.login_form);

        // Store values at the time of the login attempt.
         email = mEmailView.getText().toString();
         password = mPasswordView.getText().toString();



    }

    public void signIn(View view) {

       if(mEmailView.getText().toString().equals("a") && mPasswordView.getText().toString().equals("a"))
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
}

