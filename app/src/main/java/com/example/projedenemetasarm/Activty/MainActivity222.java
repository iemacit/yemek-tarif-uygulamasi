package com.example.projedenemetasarm.Activty;

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
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.projedenemetasarm.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity222 extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private NavigationView navim;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    public static String gelenEmail;
    public static String gelenSifre;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main222);
        //XML verilerinin çekilmesi
        navim=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolbar);
        drawer=findViewById(R.id.drawer);
        //Navigation ve Toolbar drawer bağlantısının yapıldığı alan
        NavHostFragment navHostFragment=
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.hostFragment);
        NavigationUI.setupWithNavController(navim,navHostFragment.getNavController());

        ActionBarDrawerToggle toggle=
                new ActionBarDrawerToggle(this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);

// ActionBarDrawerToggle ile ilgili işlemleri yönlendir
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

// ActionBarDrawerToggle'ın durumunu senkronize et
        toggle.syncState();
        setSupportActionBar(toolbar);



        //tasarlanan xml drawer başlangıç menunun bağlanması
        View baslik=navim.inflateHeaderView(R.layout.drawer_baslik);

       gelenEmail=getIntent().getStringExtra("email");
       gelenSifre=getIntent().getStringExtra("sifresi");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toolbara searchview in bağlanması
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
            //Üç noktaya basıp çıkmak istediği zaman çıkış butonunun çalışması
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