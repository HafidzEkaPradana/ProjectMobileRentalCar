package com.sugarseries.projectmobile.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sugarseries.projectmobile.R;
import com.sugarseries.projectmobile.model.DataCar;
import com.sugarseries.projectmobile.model.DataPesan;

import java.util.ArrayList;
import java.util.List;

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.MyViewHolder> {
    private Context context;
    private List<DataPesan> list;
    private Dialog dialog;

    public PesanAdapter(Context cont,List<DataPesan> data) {
        context = cont;
        list = data;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView vAlamat, vSewa, vSelesai, vHarga;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vAlamat = itemView.findViewById(R.id.tv_alamat);
            vSewa = itemView.findViewById(R.id.tv_sewa);
            vSelesai = itemView.findViewById(R.id.tv_selesai);
            vHarga = itemView.findViewById(R.id.tv_harga);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog !=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public PesanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pesan, parent, false);
        return new MyViewHolder(itemView);
    }
    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    @Override
    public void onBindViewHolder(@NonNull PesanAdapter.MyViewHolder holder, int position) {
        holder.vAlamat.setText(list.get(position).getAlamat());
        holder.vSewa.setText(list.get(position).getTglSewa());
        holder.vSelesai.setText(list.get(position).getTglSelesai());
        holder.vHarga.setText(list.get(position).getHarga());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
