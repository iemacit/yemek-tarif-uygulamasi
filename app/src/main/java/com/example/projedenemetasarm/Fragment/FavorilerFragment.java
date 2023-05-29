package com.example.projedenemetasarm.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.projedenemetasarm.Activty.AyrintiliActivity;
import com.example.projedenemetasarm.R;
import com.example.projedenemetasarm.RecylerViewAdaptor;
import com.example.projedenemetasarm.RexyclerViewInterface;
import com.example.projedenemetasarm.YemekListesi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FavorilerFragment extends Fragment implements RexyclerViewInterface {

    private RecyclerView myres;
    private ArrayList<YemekListesi> listem;
    private RecylerViewAdaptor myreycAdaptor;;
    private String username;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tasarim = inflater.inflate(R.layout.fragment_tarifler, container, false);

        myres = tasarim.findViewById(R.id.yemekReyclist);
        listem = new ArrayList<>();
        myreycAdaptor = new RecylerViewAdaptor(listem, this);
        myres.setAdapter(myreycAdaptor);
        myres.setLayoutManager(new LinearLayoutManager(getActivity()));
        diziolustur();
        eventChangeListener();
        myreycAdaptor.notifyDataSetChanged();


        return tasarim;

    }
    private void eventChangeListener() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        if (user != null) {
            String userId = user.getUid();
            db.collection("kullanicilar").document(userId).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                username = document.getString("Kullanici Adi");
                                System.out.println("deneme1 " + username);

                                mFirestore.collection(username).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            List<YemekListesi> wordList = new ArrayList<>();
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                String yemekAdi = document.getString("yemekAdi");
                                                String yemekSaati = document.getString("yemekSaati");
                                                String yemekYapan = document.getString("yemekYapan");
                                                String yemekKisi = document.getString("yemekKisi");
                                                String yemekAciklama = document.getString("yemekAciklama");
                                                String yemekGorsel = document.getString("imagePath"); // Fotoğrafın Storage'da bulunduğu yol
                                                YemekListesi deneme = new YemekListesi(yemekAdi, yemekYapan, yemekGorsel, yemekKisi, yemekSaati,yemekAciklama);
                                                wordList.add(deneme);
                                            }

                                            listem.clear();
                                            listem.addAll(wordList);
                                            myreycAdaptor.notifyDataSetChanged();

                                            // Resimleri indirme ve ImageView'e yerleştirme
                                            for (YemekListesi yemek : wordList) {
                                                StorageReference photoRef = storageRef.child(yemek.getYemekGorseli());
                                                ImageView imageView = new ImageView(getActivity());

                                                Glide.with(getActivity())
                                                        .load(photoRef)
                                                        .into(imageView);


                                                // ImageView yerine imagePath'i saklayın
                                                yemek.setYemekGorseli(yemek.getYemekGorseli());

                                            }
                                        } else {
                                            // Hata durumuyla başa çıkın
                                        }
                                    }
                                });

                            } else {
                            }
                        } else {
                            //Log.d("Firestore", "Error getting document: ", task.getException());
                        }
                    });
        } else {
        }

        System.out.println("deneme2 " + username);
    }




    private void diziolustur() {

        /*listem.add(new YemekListesi("PASALİNA","TOGG",R.drawable.amblemm,"5 DK","4 kisi"));
        listem.add(new YemekListesi("PİZZA","MAHMUT",R.drawable.patates,"110 DK","24 kisi"));
        listem.add(new YemekListesi("PASTA","FATMA",R.drawable.pasta,"25 DK","2 kisi"));
        listem.add(new YemekListesi("PASALİNA","TOGG",R.drawable.amblemm,"55 DK","4 kisi"));
        listem.add(new YemekListesi("PASALİNA","TOGG",R.drawable.amblemm,"5 DK","4 kisi"));
    */
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
        intent.putExtra("yemekAciklama",listem.get(position).getYemekAciklama());
        startActivity(intent);


    }
}