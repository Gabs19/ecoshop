package com.example.ecoshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ecoshop.R;
import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.Product;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AddProducts extends Fragment {

    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    EditText nameProduct,descProduct,price;
    Button registerProduct;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_add_product,container,false);

        nameProduct = view.findViewById(R.id.product_name);
        descProduct = view.findViewById(R.id.product_desc);
        price = view.findViewById(R.id.product_price);

        registerProduct = view.findViewById(R.id.btn_register_product);

        registerProduct.setOnClickListener(v -> registerProduct());
        return view;
    }
    public void onStart() {
        super.onStart();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    void registerProduct(){
        if (user != null) {
            Product product = new Product();
            product.setId(UUID.randomUUID().toString());
            product.setName(nameProduct.getText().toString().toLowerCase());
            product.setDescription(descProduct.getText().toString().toLowerCase());
            product.setPrice(Float.parseFloat(price.getText().toString()));
            product.setSeller(user.getUid());

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            product.setDate(dateFormat.format(date));

            databaseReference.child("Product").child(product.getId()).setValue(product);
            alert("Produto cadastado com sucesso!");

            nameProduct.setText("");
            descProduct.setText("");
            price.setText("");
        }
    }

    private void alert(String msg) {
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }

}
