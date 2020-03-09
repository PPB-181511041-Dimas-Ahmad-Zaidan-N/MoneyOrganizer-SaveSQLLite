package com.dimas.moneyorganizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoneyTrackAdapter extends RecyclerView.Adapter<MoneyTrackAdapter.MoneyTrackViewHolder> {
    private Context context;
    private ArrayList<MoneyTrack> moneyTracks;

    MoneyTrackAdapter(Context context){this.context=context; }

    void setList(ArrayList<MoneyTrack> list){
        this.moneyTracks=list;
    }

    private ArrayList<MoneyTrack> getMoneyTracks(){ return moneyTracks;}

    @NonNull
    @Override
    public MoneyTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.money_track_list, parent, false);
        return new MoneyTrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoneyTrackViewHolder holder, final int position) {
        holder.tvHari.setText(getMoneyTracks().get(position).getHari());
        holder.tvTanggal.setText(getMoneyTracks().get(position).getTanggal());
        holder.tvPendapatan.setText(String.valueOf(getMoneyTracks().get(position).getPendapatan()));
        holder.tvPengeluaran.setText(String.valueOf(getMoneyTracks().get(position).getPengeluaran()));
    }

    @Override
    public int getItemCount() { return moneyTracks.size(); }


    public class MoneyTrackViewHolder extends RecyclerView.ViewHolder {
        TextView tvHari, tvTanggal, tvPendapatan, tvPengeluaran;
        Button btnDeskripsi;
        public MoneyTrackViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHari=itemView.findViewById(R.id.tv_hari);
            tvTanggal=itemView.findViewById(R.id.tv_tanggal);
            tvPendapatan=itemView.findViewById(R.id.tv_pendapatan);
            tvPengeluaran=itemView.findViewById(R.id.tv_pengeluaran);
        }
    }
}
