package com.example.ecoshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(() -> login(), 2000);
    }

    private void login() {
        Intent intentLogin = new Intent(SplashScreen.this, Login.class);
        startActivity(intentLogin);
        finish();
    }

}