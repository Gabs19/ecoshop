package com.example.ecoshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.model.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdpaterProductList extends RecyclerView.Adapter<AdpaterProductList.ViewHolder> {

    private ArrayList<Product> products = new ArrayList<>();
    private Context context;

    public AdpaterProductList(Context context ,ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_products,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        Utils utils = new Utils();

        holder.nameProduct.setText(product.getName());
        holder.priceProduct.setText(Double.toString(utils.decimalFormat(product.getPrice())));

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference productdelete = FirebaseDatabase.getInstance().getReference("Product").child(product.getId());
                productdelete.removeValue();

                Toast.makeText(v.getContext(), "Produto Deletado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameProduct;
        TextView priceProduct;
        Button deletebtn;

        public ViewHolder(View itemView) {
            super(itemView);
            nameProduct = (TextView) itemView.findViewById(R.id.product_name);
            priceProduct = (TextView) itemView.findViewById(R.id.product_price);
            deletebtn = itemView.findViewById(R.id.btnDelete);
        }
    }
}
