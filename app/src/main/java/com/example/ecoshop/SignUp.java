package com.example.ecoshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecoshop.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class SignUp extends AppCompatActivity {

    private TextView btnEntre;

    private EditText inputName, inputEmail, inputPassword;
    private Button btnSignUp;
//    private CheckBox inputType;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        btnEntre = (TextView) findViewById(R.id.entre);

        inputName = findViewById(R.id.name);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);

        btnSignUp = findViewById(R.id.cadastrar);

        inicializarFirebase();

        btnEntre.setOnClickListener(v -> entre());

        btnSignUp.setOnClickListener(v -> cadastro());


    }

    private void entre(){
        Intent intentLoginPage = new Intent(SignUp.this,Login.class);
        startActivity(intentLoginPage);
        finish();
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(SignUp.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cadastro() {
        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setName(inputName.getText().toString());
        user.setEmail(inputEmail.getText().toString());
        user.setPassword(inputPassword.getText().toString());

        databaseReference.child("User").child(user.getId()).setValue(user);

        Intent home = new Intent(SignUp.this, MainActivity.class);
        startActivity(home);
        finish();
    }


}