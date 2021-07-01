package com.example.ecoshop.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Fragment {

    //    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    ListView product_list;

    private List<Product> products = new ArrayList<Product>();
    private ArrayAdapter<Product> productArrayAdapter;

    Product productSelected;

    Utils utils = new Utils();

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_shop, container, false);

        product_list = (ListView) view.findViewById(R.id.products);

        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productSelected = (Product) parent.getItemAtPosition(position);
                String productName = productSelected.getName();
                String productdesc = productSelected.getDescription();
                double productprice = utils.decimalFormat(productSelected.getPrice());

                read(productName, productdesc, productprice);
            }
        });

        return view;
    }

    public void onStart() {
        super.onStart();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        ProductListView();
    }


    private void ProductListView() {

        if (user != null) {
            DatabaseReference productReference = database.getReference().child("Product");
            productReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    products.clear();
                    for (DataSnapshot productObj : snapshot.getChildren()) {
                        products.add(productObj.getValue(Product.class));
                        productArrayAdapter = new ArrayAdapter<Product>(getActivity(), android.R.layout.simple_list_item_1, products);
                        product_list.setAdapter(productArrayAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

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