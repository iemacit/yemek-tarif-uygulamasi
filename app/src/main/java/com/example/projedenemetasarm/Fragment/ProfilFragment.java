package com.example.projedenemetasarm.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projedenemetasarm.Activty.MainActivity222;
import com.example.projedenemetasarm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfilFragment extends Fragment {

    TextView email,kullaniciad,sifre;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profil, container, false);
        email=view.findViewById(R.id.profilEmail);
        sifre=view.findViewById(R.id.profilSifre);
        kullaniciad=view.findViewById(R.id.profilName);
        email.setText(MainActivity222.gelenEmail);
        sifre.setText(MainActivity222.gelenSifre);

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
                                String username = document.getString("Kullanici Adi");
                                kullaniciad.setText(username);
                            } else {
                                kullaniciad.setText("");
                            }
                        } else {
                            //Log.d("Firestore", "Error getting document: ", task.getException());
                        }
                    });
        } else {
            kullaniciad.setText("");
        }



        return view;
    }

}