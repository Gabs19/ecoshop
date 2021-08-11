package com.example.ecoshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.adapter.AdapterProduct;
import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private EditText searchinput;
    private FloatingActionButton addButton;

    public static ArrayList<Product> products = new ArrayList<Product>();

    Utils utils = new Utils();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_shop, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        product_list = view.findViewById(R.id.products);
        product_list.setLayoutManager(layoutManager);

        searchinput = view.findViewById(R.id.search);
        ImageButton searchbtn = view.findViewById(R.id.btn_search);

        addButton = view.findViewById(R.id.btn_add_prod);

        addButton.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddProducts()).commit());

        searchbtn.setOnClickListener(v -> { search(); });
        return view;
    }

    public void onStart() {
        super.onStart();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        verificarVendedor();
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
                            AdapterProduct adapter = new AdapterProduct(getContext(), products);
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

    private void search(){

        String searchProduct = searchinput.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("product", searchProduct);

        Search search = new Search();
        search.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,search).commit();
    }

    private void verificarVendedor() {

        if (user != null) {

            DatabaseReference verificar = database.getReference().child("User");


            verificar.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot snap : snapshot.getChildren()) {
                        if (snap.child("id").getValue(String.class).equals(user.getUid())) {

                            if (snap.child("seller").getValue(Boolean.class) == true) {
                                addButton.setVisibility(View.VISIBLE);
                                addButton.setEnabled(true);

                            } else {
                                addButton.setVisibility(View.GONE);
                                addButton.setEnabled(false);
                            }
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