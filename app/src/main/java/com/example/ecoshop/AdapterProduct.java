package com.example.ecoshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.model.Product;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    private ArrayList<Product> products = new ArrayList<>();
    private Context context;

    public AdapterProduct(Context context, ArrayList<Product> products) {
        this.products = products;
        this.context = context;
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

//    public AdapterProduct(Context context, int resource, List<Product> productList){
//        super(context,resource,productList);
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Product product = getItem(position);
//
//        if(convertView == null){
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view,parent,false);
//        }
//        Utils utils = new Utils();
//
//        TextView nameProduct = (TextView) convertView.findViewById(R.id.product_name);
//        TextView descProduct = (TextView) convertView.findViewById(R.id.product_price);
//
//        nameProduct.setText(product.getName());
//        descProduct.setText(Double.toString(utils.decimalFormat(product.getPrice())));
//
//        return convertView;
//    }
}
