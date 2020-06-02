package com.example.instatest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.drm.ProcessedData;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpInstagram extends AppCompatActivity implements View.OnClickListener {
    private EditText usernamesignup,emailsignup,passwordsignup;
    private Button signup;
    private TextView loginsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_instagram);
        setTitle("Sign Up");
        usernamesignup = findViewById(R.id.EmailSignup);
        emailsignup = findViewById(R.id.UsernameSignup);
        passwordsignup = findViewById(R.id.passwordSignup);
        signup = findViewById(R.id.btnSignup);
        loginsignup = findViewById(R.id.loginSignup);
        passwordsignup.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(loginsignup);
                }
                return false;
            }
        });
        signup.setOnClickListener(this);
        loginsignup.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignup:
                try{
                    if(emailsignup.getText().toString().equals("")||usernamesignup.getText().toString().equals("")||passwordsignup.getText().toString().equals("")){
                        FancyToast.makeText(SignUpInstagram.this,"Email , Username , Password all are required", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                    }else {
                        ParseUser User = new ParseUser();
                        User.setEmail(emailsignup.getText().toString());
                        User.setUsername(usernamesignup.getText().toString());
                        User.setPassword(passwordsignup.getText().toString());
                        final ProgressDialog progressDialog = new ProgressDialog(this);
                        progressDialog.setMessage("Signing up " + usernamesignup.getText().toString());
                        progressDialog.show();
                        User.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    FancyToast.makeText(SignUpInstagram.this, "Signup successfully.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                } else {
                                    FancyToast.makeText(SignUpInstagram.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                }
                                progressDialog.dismiss();
                            }
                        });
                    }
                }catch(Exception e){
                    FancyToast.makeText(SignUpInstagram.this,e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
                break;
            case R.id.loginSignup:
                try {
                    finish();
                }catch(Exception e){
                    FancyToast.makeText(SignUpInstagram.this,e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
                break;
        }
    }
    public void rootLayoutTapped(View v){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch(Exception e){
            e.printStackTrace();
            //FancyToast.makeText(SignUpInstagram.this,e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}