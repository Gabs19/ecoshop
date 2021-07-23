package com.example.ecoshop.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.model.Product;
import com.google.firebase.auth.FirebaseUser;

public class DetailsProducts extends Fragment {

    FirebaseUser user;

    Product product;
    Utils utils = new Utils();

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

        return view;
    }


}
