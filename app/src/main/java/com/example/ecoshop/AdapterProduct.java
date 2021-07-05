package com.example.ecoshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ecoshop.model.Product;

import java.util.List;

public class AdapterProduct extends ArrayAdapter<Product> {

    public AdapterProduct(Context context, int resource, List<Product> productList){
        super(context,resource,productList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view,parent,false);
        }

        TextView nameProduct = (TextView) convertView.findViewById(R.id.name_product);
        TextView descProduct = (TextView) convertView.findViewById(R.id.desc_product);

        nameProduct.setText(product.getName());
        descProduct.setText(product.getDescription());

        return convertView;
    }
}
