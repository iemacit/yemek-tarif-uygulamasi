package com.example.projedenemetasarm.Fragment;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.projedenemetasarm.Activty.AyrintiliActivity;
import com.example.projedenemetasarm.R;
import com.example.projedenemetasarm.RecylerViewAdaptor;
import com.example.projedenemetasarm.RexyclerViewInterface;
import com.example.projedenemetasarm.YemekListesi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;


public class TariflerFragment extends Fragment implements RexyclerViewInterface {
    private RecyclerView myres;
    private ArrayList<YemekListesi> listem;
    private RecylerViewAdaptor myreycAdaptor;;
    private FirebaseFirestore mFirestore=FirebaseFirestore.getInstance();
    List<YemekListesi> wordList = new ArrayList<>();
    private SearchView searchView;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View tasarim = inflater.inflate(R.layout.fragment_tarifler, container, false);

        myres = tasarim.findViewById(R.id.yemekReyclist);
        searchView = tasarim.findViewById(R.id.searchTarifler);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<YemekListesi> filteredList = new ArrayList<>();
                for (YemekListesi list : listem) {
                    if (list.getYemekAdi().toLowerCase().contains(s.toLowerCase())) {
                        filteredList.add(list);
                    }
                }
                if (filteredList.isEmpty()) {

                } else {
                    myreycAdaptor.setFilterList(filteredList);
                }
                return true;
            }
        });


        listem = new ArrayList<>();
        myreycAdaptor = new RecylerViewAdaptor(listem, this);
        myres.setAdapter(myreycAdaptor);
        myres.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventChangeListener();
        myreycAdaptor.notifyDataSetChanged();

        return tasarim;

    }
    private void eventChangeListener() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        mFirestore.collection("yemekler").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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