package com.example.projedenemetasarm.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projedenemetasarm.R;

public class FavorilerFragment extends Fragment {

    DrawerLayout drawerLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favoriler, container, false);
        drawerLayout=view.findViewById(R.id.drawer);
        return view;
    }
    public void onBackPressed() {
        /*
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            // Ana Fragment'e geçiş yapmak için gerekli işlemleri burada gerçekleştirin
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            ProfilFragment fragment = new ProfilFragment();
            transaction.replace(R.id.rela, fragment);
            transaction.commit();
        }*/
        }


}