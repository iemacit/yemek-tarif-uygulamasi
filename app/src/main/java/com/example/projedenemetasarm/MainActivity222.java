package com.example.projedenemetasarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;


public class MainActivity222 extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private NavigationView navim;
    private DrawerLayout drawer;
    private Toolbar toolbar;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main222);
        navim=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolbar);
        drawer=findViewById(R.id.drawer);
        NavHostFragment navHostFragment=
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.hostFragment);
        NavigationUI.setupWithNavController(navim,navHostFragment.getNavController());

        ActionBarDrawerToggle toggle=
                new ActionBarDrawerToggle(this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        setSupportActionBar(toolbar);
        toolbar.setTitle("TARİFEM");
        drawer.post(new Runnable() {
            @Override
            public void run() {
                toggle.syncState();
            }
        });


        View baslik=navim.inflateHeaderView(R.layout.drawer_baslik);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //Kelime Yazıldıkca aarama SOnucu Gelir
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //harf harf Yazıldıkcaaaa aarama SOnucu Gelir
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ayarlar:
                Intent inttr=new Intent(MainActivity222.this,MainActivity.class);
                finish();
                startActivity(inttr);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}