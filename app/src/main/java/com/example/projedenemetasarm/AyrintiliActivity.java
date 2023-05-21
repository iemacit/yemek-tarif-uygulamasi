package com.example.projedenemetasarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AyrintiliActivity extends AppCompatActivity {
    ImageView resim;
    TextView yemekadi,yapan,yemekSuresi,kisiSayisi;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayrintili);
        //Ayrıntılı ekran panelinde tutulması planlanan alanların oluşturulması
        yemekadi=findViewById(R.id.yemekadi);
        yapan=findViewById(R.id.yemekyapani);
        resim=findViewById(R.id.imageView);
        yemekSuresi=findViewById(R.id.yemekSuresi);
        kisiSayisi=findViewById(R.id.kisiSayisi);
        //30 .satıra kadar Adaptor dan veri çekilmesi
        String adi=getIntent().getStringExtra("yemekadi");
        String paylasan=getIntent().getStringExtra("yemekyazar");
        int resmi=getIntent().getIntExtra("resim",0);
        String sure=getIntent().getStringExtra("yemekSuresi");
        String kisi=getIntent().getStringExtra("kisiSayisi");

        //Verilerin ayrıntılı ekran paneline gönderilmesi
        yemekadi.setText(adi);
        yapan.setText(paylasan);
        resim.setImageResource(resmi);
        yemekSuresi.setText(sure);
        kisiSayisi.setText(kisi);
    }
}