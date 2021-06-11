package com.example.ecoshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cadastro();
    }

    private void cadastro() {
        Intent cadastroIntent = new Intent(Login.this,SingUp.class);
        startActivity(cadastroIntent);
        finish();
    }
}