package com.sugarseries.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.sugarseries.projectmobile.adapter.PesanAdapter;
import com.sugarseries.projectmobile.model.DataPesan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pemesanan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton btnAdd;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<DataPesan> list = new ArrayList<>();
    private PesanAdapter pesanAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.addPesan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(pesanAdapter);

        progressDialog = new ProgressDialog(Pemesanan.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Sedang Mengambil Data...");
        pesanAdapter = new PesanAdapter(getApplicationContext(),list);
        pesanAdapter.setDialog(new PesanAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Pemesanan.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), AddPemesanan.class);
                                intent.putExtra("id",list.get(pos).getId());
                                intent.putExtra("alamat",list.get(pos).getAlamat());
                                intent.putExtra("sewa",list.get(pos).getSewa());
                                intent.putExtra("selesai",list.get(pos).getSelesai());
                                intent.putExtra("harga",list.get(pos).getHarga());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        btnAdd.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(),AddPemesanan.class));
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }
    private void getData(){
        progressDialog.show();
        db.collection("pesans")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                DataPesan dataPesan = new DataPesan(document.getString("alamat"),document.getString("harga"),document.getString("selesai"),document.getString("sewa"));
                                dataPesan.setId(document.getId());
                                list.add(dataPesan);
                            }
                            pesanAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(),"Data gagal diambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    private void deleteData(String id){
        progressDialog.show();
        db.collection("pesans").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data gagal dihapus", Toast.LENGTH_SHORT).show();

                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }
}