package com.example.ecoshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ecoshop.R;
import com.example.ecoshop.authentication.Conection;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Settings extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    Button sellerBtn;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragments_settings,container,false);

        sellerBtn = view.findViewById(R.id.seller);

        sellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seller();
            }
        });

        return view;
    }

    public void onStart() {
        super.onStart();
        auth = Conection.getFirebaseAuth();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    public void seller(){
        if(user != null) {
            databaseReference.child("User").child(user.getUid()).child("seller").setValue(true);
            sellerBtn.setText("Deixe de ser Vendedor");
        }
    }
}
