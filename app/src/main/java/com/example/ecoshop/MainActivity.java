package com.example.ecoshop;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ecoshop.fragments.Cart;
import com.example.ecoshop.fragments.Favorites;
import com.example.ecoshop.fragments.Settings;
import com.example.ecoshop.fragments.Shop;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{

    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavSelect);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Shop()).commit();
    }

    public void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            Fragment selectFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_shop:
                    selectFragment = new Shop();
                    break;

                case R.id.nav_cart:
                    selectFragment = new Cart();
                    break;

                case R.id.nav_fav:
                    selectFragment = new Favorites();
                    break;

                case R.id.settings:
                    selectFragment = new Settings();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectFragment).commit();

            return true;
        }
    };

    public void alert(String mensagem){
        Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();
    }
}