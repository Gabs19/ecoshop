package com.example.ecoshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        checkUser();
    }

    //Método verifica se o há algum usuário logado
    public void checkUser() {
        auth = FirebaseAuth.getInstance();
        //se sim, acesso a loja, caso contrário tela de login
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(this, MainActivity.class));
            Log.i("UserLogin", "Usuário está logado!");
            finish();
        }else{
            startActivity(new Intent(this, Login.class));
            Log.i("UserLogin", "Usuário não está logado!");
            finish();
        }
    }
}