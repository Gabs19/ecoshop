package com.example.ecoshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class TypeProducts extends Fragment {

    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private RecyclerView product_list;
    public static ArrayList<Product> products = new ArrayList<Product>();

    Utils utils = new Utils();
    String productType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_type_products_view, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        product_list = view.findViewById(R.id.products_list);
        product_list.setLayoutManager(layoutManager);

        productType = getArguments().getString("type");

        return view;
    }

    public void onStart() {
        super.onStart();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        productViewList();
    }

    private void productViewList() {


        if (user != null) {
            DatabaseReference productReference = database.getReference().child("Product");
            productReference.orderByChild("type").equalTo(productType).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    products.clear();
                    for (DataSnapshot productObj : snapshot.getChildren()) {
                        products.add(productObj.getValue(Product.class));
                        if (getActivity() != null){
                            AdapterSearch adapter = new AdapterSearch(getContext(),products);
                            product_list.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onCancelled( DatabaseError error) {

                }
            });
        }
    }
}
