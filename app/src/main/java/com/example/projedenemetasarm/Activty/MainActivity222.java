package com.example.projedenemetasarm.Activty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.projedenemetasarm.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity222 extends AppCompatActivity {
    private NavigationView navim;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    public static String gelenEmail;
    public static String gelenSifre;
    ArrayAdapter<String> arrayAdapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main222);

        // XML verilerinin çekilmesi
        NavigationView navim = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);
         drawer = findViewById(R.id.drawerr);

        // Navigation ve Toolbar drawer bağlantısının yapıldığı alan
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.hostFragment);
        setSupportActionBar(toolbar); // Toolbar'ı ActionBar olarak ayarla
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, drawer); // ActionBar'ı drawer ile ilişkilendir
        NavigationUI.setupWithNavController(navim, navController); // NavigationView ile NavController'ı ilişkilendir

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        // Tasarlanan XML drawer başlangıç menüsünün bağlanması
        View baslik = navim.inflateHeaderView(R.layout.drawer_baslik);
        gelenEmail=getIntent().getStringExtra("email");
        gelenSifre=getIntent().getStringExtra("sifresi");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }


}