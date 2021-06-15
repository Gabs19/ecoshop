package com.example.ecoshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private TextView btnEntre;

    private EditText inputName, inputEmail, inputPassword;
    private Button btnSignUp;
//    private CheckBox inputType;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

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

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conection.getFirebaseAuth();
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
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        cadastroEmaileSenha(email,password);
    }

    private void cadastroEmaileSenha(String email, String password){

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User();

                            user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            user.setName(inputName.getText().toString());
                            user.setEmail(inputEmail.getText().toString());
                            user.setPassword(inputPassword.getText().toString());

                            databaseReference.child("User").child(user.getId()).setValue(user);

                            alert("Cadastrar feito com sucesso");

                            Intent home = new Intent(SignUp.this, MainActivity.class);
                            startActivity(home);
                            finish();
                        } else {
                            alert("Erro ao Cadastrar");
                        }
                    }
                });
    }

    private void alert(String msg) {
        Toast.makeText(SignUp.this,msg, Toast.LENGTH_SHORT).show();
    }
}