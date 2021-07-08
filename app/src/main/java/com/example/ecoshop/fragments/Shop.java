package com.example.ecoshop.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.AdapterProduct;
import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.Product;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Shop extends Fragment {

    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private RecyclerView product_list;
    public static ArrayList<Product> products = new ArrayList<Product>();

    Utils utils = new Utils();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_shop, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        product_list = view.findViewById(R.id.products);
        product_list.setLayoutManager(layoutManager);

//        product_list.setOnItemClickListener((parent, view1, position, id) -> {
//            Product productSelected = (Product) parent.getItemAtPosition(position);
//            String productName = productSelected.getName();
//            String productdesc = productSelected.getDescription();
//            double productprice = utils.decimalFormat(productSelected.getPrice());
//
//            read(productName, productdesc, productprice);
//        });

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

            productReference.orderByChild("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    products.clear();
                    for (DataSnapshot productObj : snapshot.getChildren()) {
                        products.add(productObj.getValue(Product.class));
                        if (getActivity() != null){
                            AdapterProduct adapter = new AdapterProduct(getContext(),products);
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

    private void read(String name, String description, double price) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Produto");
        builder.setMessage(name + "\n" + description + "\n" + price);
        builder.create();
        builder.show();
    }
}