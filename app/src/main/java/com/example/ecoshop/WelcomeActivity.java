package com.example.ecoshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecoshop.authentication.Conection;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    protected void onStart(){
        super.onStart();
        auth = Conection.getFirebaseAuth();

        checkUser();
    }

    //Método verifica se o há algum usuário logado
    public void checkUser() {
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