package com.osmanlioglu.javainstagramclone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.osmanlioglu.javainstagramclone.adapter.postAdapter;
import com.osmanlioglu.javainstagramclone.databinding.ActivityShowBinding;
import com.osmanlioglu.javainstagramclone.models.postModel;

import java.util.ArrayList;

public class showActivity extends AppCompatActivity {

    private ActivityShowBinding binding;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ArrayList<postModel> postModelArrayList;
    postAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        postModelArrayList = new ArrayList<>();

        getDatas();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this)); //itemleri LinearLayout olarak sırala.
        postAdapter = new postAdapter(postModelArrayList);
        binding.recyclerView.setAdapter(postAdapter);

    }

    public void getDatas(){
        firebaseFirestore.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(showActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }

                if (value != null){

                    for (DocumentSnapshot document : value.getDocuments()){  //Java'da foreach şeklindeki for kullanımı
                        // (Value.getDocuments'te tüm dökümanlar yer alır,
                        // Döngüyü foreach şeklinde kullandığımızda her bir döküman için yapılacak işlemleri temsil eder.

                        String explaination = (String) document.get("explaination");
                        String email = (String) document.get("email");
                        String downloadUrl = (String) document.get("downloadUrl");
                        Timestamp timestamp = (Timestamp) document.get("date");

                        postModel postModel = new postModel(explaination,email,downloadUrl,timestamp);
                        postModelArrayList.add(postModel);


                    }

                    postAdapter.notifyDataSetChanged();

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addPost){
            Intent intent = new Intent(showActivity.this, uploadActivity.class);
            startActivity(intent);
            
        } else if (item.getItemId() == R.id.logOut) {
            firebaseAuth.signOut();
            Intent intent = new Intent(showActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}