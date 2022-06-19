package com.sugarseries.projectmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class AddPemesanan extends AppCompatActivity {

    private EditText harga, alamat,sewa, selesai;
    private DatePickerDialog.OnDateSetListener setListener;
    private MaterialButton saveBtn;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pemesanan);

        alamat = findViewById(R.id.tx_Alamat);
        harga = findViewById((R.id.tx_harga));
        sewa = findViewById(R.id.tx_sewa);
        selesai = findViewById(R.id.tx_selesai);
        saveBtn = findViewById(R.id.savebtn);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddPemesanan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        sewa.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddPemesanan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        selesai.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        saveBtn.setOnClickListener(v ->{
            if(alamat.getText().length()>0 && sewa.getText().length()>0 && selesai.getText().length()>0 && harga.getText().length()>0){
                saveData(alamat.getText().toString(), sewa.getText().toString(), selesai.getText().toString(), harga.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(),"Silahkan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if(intent!=null){
            id = intent.getStringExtra("id");
            alamat.setText(intent.getStringExtra("alamat"));
            sewa.setText(intent.getStringExtra("sewa"));
            selesai.setText(intent.getStringExtra("selesai"));
            harga.setText(intent.getStringExtra("harga"));
        }

    }
    private void saveData(String alamat, String sewa, String selesai, String harga){
        Map<String, Object> pesan = new HashMap<>();
        pesan.put("alamat", alamat);
        pesan.put("sewa",sewa);
        pesan.put("selesai",selesai);
        pesan.put("harga",harga);

        if(id!=null){
            db.collection("pesans").document(id)
                    .set(pesan)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            db.collection("pesans")
                    .add(pesan)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(),"Berhasil", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener(){
                        @Override
                        public void onFailure(@NonNull Exception e){
                            Toast.makeText(getApplicationContext(),"Gagal", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}