package com.example.ecoshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoshop.authentication.Conection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText editEmail, editSenha;
    private Button btnLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView btnCadastreSe = (TextView) findViewById(R.id.cadastre_se);
        editEmail = findViewById(R.id.email);
        editSenha = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);

        btnCadastreSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastro();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                login(email, senha);
            }
        });
    }

    private void cadastro() {
        Intent cadastroIntent = new Intent(Login.this, SignUp.class);
        startActivity(cadastroIntent);
        finish();
    }
    private void login(String email, String senha){
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent home = new Intent(Login.this, MainActivity.class);
                            startActivity(home);
                            finish();
                        }else{
                            alert("Email ou senha incorretos");
                        }
                    }
                });
    }
    private void alert(String mensagem){
        Toast.makeText(Login.this, mensagem, Toast.LENGTH_SHORT).show();
    }

    protected void onStart(){
        super.onStart();
        auth = Conection.getFirebaseAuth();
    }
}