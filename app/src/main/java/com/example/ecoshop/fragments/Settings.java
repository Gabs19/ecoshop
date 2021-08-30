package com.example.ecoshop.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoshop.R;
import com.example.ecoshop.Utils;
import com.example.ecoshop.WelcomeActivity;
import com.example.ecoshop.adapter.AdpaterProductList;
import com.example.ecoshop.model.Product;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

public class Settings extends Fragment {

    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    Button sellerBtn, logoutBtn, getLocation;

    TextView addresstxt;

    FusedLocationProviderClient fusedLocationProviderClient;

    private RecyclerView product_list;
    public static ArrayList<Product> products = new ArrayList<Product>();

    Utils utils = new Utils();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view  =  inflater.inflate(R.layout.fragments_settings,container,false);

        sellerBtn = view.findViewById(R.id.seller);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        getLocation = view.findViewById(R.id.get_location);

        addresstxt = view.findViewById(R.id.show_address);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        product_list = view.findViewById(R.id.list_product);
        product_list.setLayoutManager(layoutManager);

        sellerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seller();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getActivity(), WelcomeActivity.class));
            }
        });

        //inicializando fusedLocation
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //checando permissão
        getLocation.setOnClickListener(new View.OnClickListener() {
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
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        productViewList();
    }

    public void seller(){
        //consertar esse código
        if(user != null) {
            if (sellerBtn.getText().equals("Torne-se Vendedor")){
                databaseReference.child("User").child(user.getUid()).child("seller").setValue(true);
                sellerBtn.setText("Deixe de ser Vendedor");
            } else if (sellerBtn.getText().equals("Deixe de ser Vendedor")) {
                databaseReference.child("User").child(user.getUid()).child("seller").setValue(false);
                sellerBtn.setText("Torne-se Vendedor");
            }
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
                        addresstxt.setText(address.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void productViewList(){
        if (user != null) {

            DatabaseReference productReference = database.getReference().child("Product");
            productReference.orderByChild("seller").equalTo(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    products.clear();
                    for (DataSnapshot productObj : snapshot.getChildren()) {
                        products.add(productObj.getValue(Product.class));
                        if (getActivity() != null){
                            AdpaterProductList adapter = new AdpaterProductList(getContext(),products);
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
}
