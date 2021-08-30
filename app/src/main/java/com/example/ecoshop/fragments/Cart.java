package com.example.ecoshop.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.adapter.AdapterSearch;
import com.example.ecoshop.authentication.Conection;
import com.example.ecoshop.model.Product;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends Fragment {


    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    RecyclerView cart_list;
    public static ArrayList<Product> products = new ArrayList<Product>();
    double valor = 0;

    Button att_address;
    Spinner payment;

    TextView valorTotal,show_address;
    Utils utils = new Utils();

    FusedLocationProviderClient fusedLocationProviderClient;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragments_cart_2,container,false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        cart_list = view.findViewById(R.id.cart_products);
        cart_list.setLayoutManager(layoutManager);

        valorTotal = view.findViewById(R.id.valor);
        att_address = view.findViewById(R.id.att_address);
        show_address = view.findViewById(R.id.show_address);
        payment = view.findViewById(R.id.payment);

        ArrayAdapter<String> types = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.payment));
        types.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payment.setAdapter(types);

        //inicializando fusedLocation
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //checando permissão
        att_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }
        });
        return view;
    }

    public void onStart() {
        super.onStart();
        user = Conection.getFirebaseUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        cartViewList();
    }

    void cartViewList(){
        if(user != null){
            DatabaseReference cartReference = database.getReference().child("Cart");

            cartReference.child(user.getUid()).child("products").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    products.clear();
                    for (DataSnapshot snap : snapshot.getChildren()){
                        products.add(snap.getValue(Product.class));

                        valor = valor + snap.getValue(Product.class).getPrice();
                        valorTotal.setText(Double.toString(utils.decimalFormat(valor)));

                        if (getActivity() != null){
                            AdapterSearch adapter = new AdapterSearch(getContext(),products);
                            cart_list.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //inializar localização
                Location location = task.getResult();
                if (location != null ){
                    try {
                        //inicializar Geocoder
                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                        //inicializar endereço
                        List<Address> address = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        show_address.setText(address.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
