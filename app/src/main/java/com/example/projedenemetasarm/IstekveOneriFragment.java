package com.example.projedenemetasarm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class IstekveOneriFragment extends Fragment {

    TextView istek_yonlendirme,telefon_yonlendirme;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tasarim=inflater.inflate(R.layout.fragment_istekve_oneri, container, false);
        istek_yonlendirme=tasarim.findViewById(R.id.istek_bilgi);
        telefon_yonlendirme=tasarim.findViewById(R.id.iletişim);

        //kod çalışmıyor
        /*
        telefon_yonlendirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel"+telefon_yonlendirme.getText().toString()));
                startActivity(callIntent);
            }
        });*/
        //kullaniciların meal adresine yönlendirilmesi
        istek_yonlendirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("yakuphan.isik@gmail.com") + "?subject=" +
                        Uri.encode("your email id ") + "&body=" + Uri.encode("");

                Uri uri = Uri.parse(uriText);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        return tasarim;
    }

}