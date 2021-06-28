package com.example.ecoshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ecoshop.R;
import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Fragment {

//    FirebaseAuth auth;
//    FirebaseUser user;
//    FirebaseDatabase database;
//    DatabaseReference databaseReference;
//
//    ListView product_list;
//
//    private List<Product> product = new ArrayList<Product>();
//    private ArrayAdapter<Product> productArrayAdapter;
//
//    Product productSelected;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_shop,container,false);

//        product_list = (ListView) view.findViewById(R.id.products);

        return view;
    }

//    public void onStart() {
//        super.onStart();
//        auth = Conection.getFirebaseAuth();
//        user = Conection.getFirebaseUser();
//        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference();
//    }
//
//    private  void UserListView(){
//
//        if (user != null) {
//            DatabaseReference productReference = database.getReference().child("User")
//        }
    }



