package com.example.projedenemetasarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.integrity.internal.m;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Field;
import java.util.HashMap;

public class KayitEkrani extends AppCompatActivity {

    private Button kaydol;
    EditText kulAdi,kulSifre,kulMail;
    String kullaniciAdi,kullaniciSifre,kullaniciEmail;
    FirebaseAuth mAuth;
    private FirebaseFirestore mFstore=FirebaseFirestore.getInstance();
    HashMap<String,Object> mData;

    private FirebaseUser mUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekrani);
        kaydol=findViewById(R.id.kaydol);
        kulAdi=findViewById(R.id.kullaniciAdi);
        kulSifre=findViewById(R.id.kullaniciSifre);
        kulMail=(EditText) findViewById(R.id.kullaniciMail);
        mAuth=FirebaseAuth.getInstance();

        kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kullaniciAdi=kulAdi.getText().toString();
                kullaniciSifre=kulSifre.getText().toString();
                kullaniciEmail=kulMail.getText().toString().trim();
                //Burası verileri 
                if(!TextUtils.isEmpty(kullaniciAdi) && !TextUtils.isEmpty(kullaniciSifre) && !TextUtils.isEmpty(kullaniciEmail)){
                    mAuth.createUserWithEmailAndPassword(kullaniciEmail,kullaniciSifre)
                            .addOnCompleteListener(KayitEkrani.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        mUser=mAuth.getCurrentUser();
                                        mData=new HashMap<>();
                                        mData.put("Kullanici Adi",kullaniciAdi);
                                        mData.put("Kullanici Sifre",kullaniciSifre);
                                        mData.put("Kullanici Email",kullaniciEmail);
                                        mData.put("Kullanici ID",mUser.getUid());
                                        mFstore.collection("kullanicilar").document("Person")
                                                .set(mData)
                                                .addOnCompleteListener(KayitEkrani.this, new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                    }
                                                });
                                        Toast.makeText(KayitEkrani.this,"Kayıt Olma İşleminiz Gerçekleşmiştir",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(KayitEkrani.this,MainActivity.class);
                                        finish();
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(KayitEkrani.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(KayitEkrani.this,"Lütfen Gerekli Tüm Alanları Doldurunuz",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}