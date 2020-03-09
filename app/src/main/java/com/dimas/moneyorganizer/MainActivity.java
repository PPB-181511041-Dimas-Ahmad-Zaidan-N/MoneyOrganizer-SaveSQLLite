package com.dimas.moneyorganizer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView rvMoneyTrack;
    private ArrayList<MoneyTrack> list =new ArrayList<>();
    int pendapatan;
    int pengeluaran;
    DBAdapter db = new DBAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMoneyTrack=findViewById(R.id.rv_money_track);
        rvMoneyTrack.setHasFixedSize(false);

        //getData in SQL LIte
        String hari,tanggal,pendapatan1,pengeluaran1;
        db.open();
        Cursor c = db.getAllMoney();
        if (c.moveToFirst())
        {
            do {
                hari=c.getString(1);
                tanggal=c.getString(2);
                pendapatan1=c.getString(3);
                pengeluaran1=c.getString(4);
                pendapatan=Integer.parseInt(pendapatan1);
                pengeluaran=Integer.parseInt(pengeluaran1);
                MoneyTrack moneyTrack = new MoneyTrack();
                moneyTrack.setHari(hari);
                moneyTrack.setTanggal(tanggal);
                moneyTrack.setPendapatan(pendapatan);
                moneyTrack.setPengeluaran(pengeluaran);
                list.add(moneyTrack);
            } while (c.moveToNext());
        }
        db.close();
        showRecycler();


//        if (savedInstanceState != null){
//            list.addAll(MoneyTrackData.getListData());
//            savedInstanceState.getParcelableArrayList("content");
//        }

        CircleImageView circleImageView = findViewById(R.id.profile_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToProfile = new Intent(MainActivity.this, MyProfile.class);
                startActivity(moveToProfile);
            }
        });

        Button btnTambah = findViewById(R.id.btn_tambah);
        btnTambah.setOnClickListener(this);

        pendapatan = ambilPendapatan();
        pengeluaran = ambilPengeluaran();

        FragmentManager manager =getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        final Fragment1 myFragment = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putInt("pendapatan", pendapatan);
        bundle.putInt("pengeluaran", pengeluaran);
        myFragment.setArguments(bundle);
        transaction.add(R.id.fragment1,myFragment);
        transaction.commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String hari,tanggal,pendapatan,pengeluaran;
        if(resultCode == 12) {
            Bundle b = data.getExtras();
            MoneyTrack moneyTrack =new MoneyTrack();
            moneyTrack.setHari(b.getString("hari"));
            moneyTrack.setTanggal(b.getString("tanggal"));
            moneyTrack.setPendapatan(b.getInt("pendapatan",0));
            moneyTrack.setPengeluaran(b.getInt("pengeluaran",0));
            //Log.i("HARI", getIntent().getStringExtra("hari"));
            Toast.makeText(getApplicationContext(), b.getString("hari"),Toast.LENGTH_SHORT).show();
            list.add(moneyTrack);

            hari=b.getString("hari");
            tanggal=b.getString("tanggal");
            pendapatan=String.valueOf(b.getInt("pendapatan", 0));
            pengeluaran=String.valueOf(b.getInt("pengeluaran", 0));
            //add moneyTrack
            db.open();
            long id = db.insertMoney(hari, tanggal, pendapatan, pengeluaran);
            db.close();
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void showRecycler() {
        rvMoneyTrack.setLayoutManager(new LinearLayoutManager(this));
        MoneyTrackAdapter moneyTrackAdapter = new MoneyTrackAdapter(this);
        moneyTrackAdapter.setList(list);
        rvMoneyTrack.setAdapter(moneyTrackAdapter);
        moneyTrackAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tambah:
                Intent moveIntent = new Intent(MainActivity.this, TambahTransaksi.class);
                startActivityForResult(moveIntent,12);
                break;
        }
    }

    int ambilPendapatan(){
        int b=0;
        for (MoneyTrack m : list){
            b=b+m.getPendapatan();
        }
        return b;
    }

    int ambilPengeluaran(){
        int b=0;
        for (MoneyTrack m : list){
            b=b+m.getPengeluaran();
        }
        return b;
    }
}
