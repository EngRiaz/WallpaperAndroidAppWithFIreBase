package com.example.yourwallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yourwallpaper.adapter.WallpaperAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvmain;
    private DatabaseReference databaseReference;
    private ArrayList<String > list;
    private WallpaperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("images");
        rvmain=findViewById(R.id.rvmain);

        getData();
    }

    private void getData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list= new ArrayList<>();
                for (DataSnapshot shot: snapshot.getChildren()){
                    String data= shot.getValue().toString();
                    list.add(data);
                }
                rvmain.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                adapter=new WallpaperAdapter(list,MainActivity.this);
                rvmain.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error"+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
