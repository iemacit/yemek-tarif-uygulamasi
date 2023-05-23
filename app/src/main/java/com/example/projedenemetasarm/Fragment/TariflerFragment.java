package com.example.projedenemetasarm.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projedenemetasarm.Activty.AyrintiliActivity;
import com.example.projedenemetasarm.R;
import com.example.projedenemetasarm.RecylerViewAdaptor;
import com.example.projedenemetasarm.RexyclerViewInterface;
import com.example.projedenemetasarm.YemekListesi;

import java.util.ArrayList;


public class TariflerFragment extends Fragment implements RexyclerViewInterface {
    private RecyclerView myres;
    private ArrayList<YemekListesi> listem;
    private RecylerViewAdaptor myreycAdaptor;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tasarim=inflater.inflate(R.layout.fragment_tarifler, container, false);
        myres=tasarim.findViewById(R.id.yemekReyclist);
        listem=new ArrayList<>();
        myreycAdaptor=new RecylerViewAdaptor(listem,this);
        myres.setAdapter(myreycAdaptor);
        myres.setLayoutManager(new LinearLayoutManager(getActivity()));
        diziolustur();
        myreycAdaptor.notifyDataSetChanged();
        return tasarim;
    }
    private void diziolustur() {
        listem.add(new YemekListesi("PASALİNA","TOGG",R.drawable.amblemm,"5 DK","4 kisi"));
        listem.add(new YemekListesi("PİZZA","MAHMUT",R.drawable.patates,"110 DK","24 kisi"));
        listem.add(new YemekListesi("PASTA","FATMA",R.drawable.pasta,"25 DK","2 kisi"));
        listem.add(new YemekListesi("PASALİNA","TOGG",R.drawable.amblemm,"55 DK","4 kisi"));
        listem.add(new YemekListesi("PASALİNA","TOGG",R.drawable.amblemm,"5 DK","4 kisi"));
    }

    @Override
    public void itemOnclick(int position) {
        //recyClearviewde ekranda olan verilerin Ayrıntılı actıvty classına gönderilmesi
        Intent intent=new Intent(getActivity(), AyrintiliActivity.class);
        intent.putExtra("resim",listem.get(position).getYemekGorseli());
        intent.putExtra("yemekadi",listem.get(position).getYemekAdi());
        intent.putExtra("yemekyazar",listem.get(position).getYemekPaylasan());
        intent.putExtra("yemekSuresi",listem.get(position).getYemekSuresi());
        intent.putExtra("kisiSayisi",listem.get(position).getKisiSayisi());
        startActivity(intent);


    }
}