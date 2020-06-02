package com.example.instatest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText email,password;
    private Button login;
    private TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        email = findViewById(R.id.Email);
        password = findViewById(R.id.PasswordText);

        login = findViewById(R.id.btnLogIn);
        signup = findViewById(R.id.signUp);
        email.setOnClickListener(this);
        password.setOnClickListener(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        if(ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogIn:
                try {
                    if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
                        FancyToast.makeText(MainActivity.this, "Email , Password both are required", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                    } else {
                        final ProgressDialog progressDialog = new ProgressDialog(this);
                        progressDialog.setMessage("Logning up");
                        progressDialog.show();
                        ParseUser.logInInBackground(email.getText().toString(), password.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user != null && e == null) {
                                    FancyToast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                } else {
                                    FancyToast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
                    }
                } catch(Exception e) {
                    FancyToast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
                break;
            case R.id.signUp:
                try {
                    Intent intent = new Intent(MainActivity.this, SignUpInstagram.class);
                    startActivity(intent);
                }catch(Exception e){
                    FancyToast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
                break;
        }
    }
}