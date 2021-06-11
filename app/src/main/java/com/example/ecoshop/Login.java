package com.example.ecoshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView btnCadastreSe = (TextView) findViewById(R.id.cadastre_se);

        btnCadastreSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastro();
            }
        });
    }

    private void cadastro() {
        Intent cadastroIntent = new Intent(Login.this, SignUp.class);
        startActivity(cadastroIntent);
        finish();
    }
}