package com.example.ecoshop.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailsProducts extends Fragment {

    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    Product product;
    Utils utils = new Utils();

    Button addCart;

    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragments_details_product,container,false);

        product = getArguments().getParcelable("product");

        TextView name = view.findViewById(R.id.name);
        name.setText(product.getName());

        TextView desc = view.findViewById(R.id.product_desc);
        desc.setText(product.getDescription());

        TextView price = view.findViewById(R.id.price);
        String price_product = Double.toString(utils.decimalFormat(product.getPrice()));
        price.setText(price_product);

        ImageButton favorites = view.findViewById(R.id.favorites);
        ImageButton nonFavorites = view.findViewById(R.id.non_favorites);

        addCart = view.findViewById(R.id.buy_product);

        addCart.setOnClickListener(v -> add());

        favorites.setOnClickListener(v -> {
            favorites.setVisibility(View.GONE);
            nonFavorites.setVisibility(View.VISIBLE);
        });

        nonFavorites.setOnClickListener(v -> {
            nonFavorites.setVisibility(View.GONE);
            favorites.setVisibility(View.VISIBLE);
        });

        return view;
    }

    public void onStart() {
        super.onStart();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }


    void add(){

        String saveCurrentTime, saveCurrentDate;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM, dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calendar.getTime());

        DatabaseReference cartlist = FirebaseDatabase.getInstance().getReference().child("Cart");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("id", product.getId());
        cartMap.put("name", product.getName());
        cartMap.put("description", product.getDescription());
        cartMap.put("price", product.getPrice());
        cartMap.put("date", product.getDate());
        cartMap.put("seller", product.getSeller());

        cartlist.child(user.getUid()).child("products").child(product.getId())
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            alert("Produto adicionado a seu carrinho");

                        }
                    }
                });
    }

    private void alert(String msg) {
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }


}
