package com.sugarseries.projectmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sugarseries.projectmobile.R;
import com.sugarseries.projectmobile.model.DataCar;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DataCar> dataCars;

    public CarAdapter(Context cont, ArrayList<DataCar> data){
        context = cont;
        dataCars = data;
    }

    public CarAdapter(ArrayList<DataCar> list, int item_car, Context applicationContext) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.vMobil.setText(dataCars.get(position).getMobil());
        holder.vJenis.setText(dataCars.get(position).getJenis());
        holder.vJumlah.setText(dataCars.get(position).getJumlah());
        holder.vMerk.setText(dataCars.get(position).getMerk());
        holder.vHarga.setText(dataCars.get(position).getHarga());
    }

    @Override
    public int getItemCount() {
        return dataCars.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vMobil, vJenis, vJumlah, vMerk, vHarga;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vMobil = itemView.findViewById(R.id.tv_mobil);
            vJenis = itemView.findViewById(R.id.tv_jenis);
            vJumlah = itemView.findViewById(R.id.tv_jumlah);
            vMerk = itemView.findViewById(R.id.tv_merk);
            vHarga = itemView.findViewById(R.id.tv_harga);
        }
    }
}
