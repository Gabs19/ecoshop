package com.example.ecoshop.adapter;

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

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {

    private ArrayList<Product> products = new ArrayList<>();
    private Context context;

    public AdapterSearch(Context context,ArrayList<Product> products) {
        this.products = products;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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
            nameProduct = (TextView) itemView.findViewById(R.id.search_name);
            priceProduct = (TextView) itemView.findViewById(R.id.search_price);
        }
    }
}
