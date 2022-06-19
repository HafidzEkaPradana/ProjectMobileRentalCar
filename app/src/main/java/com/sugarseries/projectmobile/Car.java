package com.sugarseries.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sugarseries.projectmobile.adapter.CarAdapter;
import com.sugarseries.projectmobile.model.DataCar;

import java.util.ArrayList;

public class Car extends AppCompatActivity {
    private DatabaseReference database;
    private ArrayList<DataCar> list;
    RecyclerView recyclerView;
    CarAdapter carAdapter;
    private ProgressDialog progressDialog;
    private LinearLayoutManager lmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        recyclerView = findViewById(R.id.carList);
        recyclerView.setHasFixedSize(true);

        lmanager = new LinearLayoutManager(this);
        lmanager.setReverseLayout(true);
        recyclerView.setLayoutManager(lmanager);

        database = FirebaseDatabase.getInstance().getReference().child("cars");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    DataCar car = dataSnapshot.getValue(DataCar.class);
                    list.add(car);
                }
                carAdapter = new CarAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(carAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Terjadi Kesalahan",Toast.LENGTH_SHORT).show();
            }
            });


    }
}