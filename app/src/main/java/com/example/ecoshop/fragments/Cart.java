package com.example.ecoshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.adapter.AdapterSearch;
import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.Product;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends Fragment {


    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    RecyclerView cart_list;
    public static ArrayList<Product> products = new ArrayList<Product>();
    double valor = 0;

    TextView valorTotal;
    Utils utils = new Utils();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragments_cart,container,false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        cart_list = view.findViewById(R.id.cart_products);
        cart_list.setLayoutManager(layoutManager);

        valorTotal = view.findViewById(R.id.valor);

        return view;
    }

    public void onStart() {
        super.onStart();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        cartViewList();
    }

    void cartViewList(){
        if(user != null){
            DatabaseReference cartReference = database.getReference().child("Cart");

            cartReference.child(user.getUid()).child("products").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    products.clear();
                    for (DataSnapshot snap : snapshot.getChildren()){
                        products.add(snap.getValue(Product.class));

                        valor = valor + snap.getValue(Product.class).getPrice();
                        valorTotal.setText(Double.toString(utils.decimalFormat(valor)));

                        if (getActivity() != null){
                            AdapterSearch adapter = new AdapterSearch(getContext(),products);
                            cart_list.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
        }
    }
}
