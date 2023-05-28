package com.example.projedenemetasarm.Activty;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projedenemetasarm.R;

public class AyrintiliActivity extends AppCompatActivity {
    //Yakup Deneme
    ImageView resim;
    TextView yemekadi,yapan,yemekSuresi,kisiSayisi,yemekAciklama;
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
        yemekAciklama=findViewById(R.id.yemekaciklama);
        //30 .satıra kadar Adaptor dan veri çekilmesi
        String adi=getIntent().getStringExtra("yemekadi");
        String paylasan=getIntent().getStringExtra("yemekyazar");
        String resmi=getIntent().getStringExtra("resim");
        String sure=getIntent().getStringExtra("yemekSuresi");
        String kisi=getIntent().getStringExtra("kisiSayisi");
        String aciklama=getIntent().getStringExtra("yemekAciklama");
        System.out.println(aciklama);
        //Verilerin ayrıntılı ekran paneline gönderilmesi
        yemekadi.setText(adi);
        yapan.setText(paylasan);


// Glide kullanarak resmi indirme ve ImageView'e yerleştirme
        Glide.with(this)
                .load(resmi)
                .into(resim);

        yemekSuresi.setText(sure);
        kisiSayisi.setText(kisi);
        yemekAciklama.setText(aciklama);
    }
}