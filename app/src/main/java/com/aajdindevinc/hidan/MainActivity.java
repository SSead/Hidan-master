package com.aajdindevinc.hidan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    WebView getSource;
    WebView view;
    WebView v;
    //String username = "mbikeshop.marketing@gmail.com";
    //String password = "mbikeshop13";
    Button button;
    Button login;

    EditText username;
    EditText password;

    String web;
    CheckBox box;
    Boolean firstTime;
    TinyDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new TinyDB(this);
        //isFirstTime();
        final Intent intent = new Intent(this, ObnoviActivity.class);


        if(database.getBoolean("isUserSaved")){
            startActivity(intent);
        }
       // database.clear();

        box = findViewById(R.id.check);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(box.isChecked()){

                    database.putString("username", username.getText().toString());
                    database.putString("password", password.getText().toString());
                    database.putBoolean("isUserSaved", true);


                }
                else {

                    database.putBoolean("isUserSaved", false);
                    intent.putExtra("username", username.getText().toString());
                    intent.putExtra("password", password.getText().toString());

                }
                startActivity(intent);
            }
        });



    }
}