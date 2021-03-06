package com.example.ecoshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.fragments.DetailsProducts;
import com.example.ecoshop.model.Product;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    private ArrayList<Product> products = new ArrayList<>();
    private Context context;

    public AdapterProduct(Context context ,ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_view,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AdapterProduct.ViewHolder holder, int position) {
        Product product = products.get(position);
        Utils utils = new Utils();

        holder.nameProduct.setText(product.getName());
        holder.priceProduct.setText(Double.toString(utils.decimalFormat(product.getPrice())));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                Bundle bundle = new Bundle();
                bundle.putParcelable("product", product);

                DetailsProducts detailsProducts = new DetailsProducts();
                detailsProducts.setArguments(bundle);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailsProducts).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameProduct;
        TextView priceProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            nameProduct = (TextView) itemView.findViewById(R.id.product_name);
            priceProduct = (TextView) itemView.findViewById(R.id.product_price);
        }
    }
}
