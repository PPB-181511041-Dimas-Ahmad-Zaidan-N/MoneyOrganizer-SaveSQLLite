package com.dimas.moneyorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PilihTanggal extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView Output;
    private Button SetChange;
    private String tanggal, hari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_tanggal);
        datePicker = findViewById(R.id.kalender);
        Output = findViewById(R.id.output);
        SetChange = findViewById(R.id.set_kalender);
        SetChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mencetak Output
                Output.setText(getDate());
                Toast.makeText(getApplicationContext(), "Berhasil Diubah", Toast.LENGTH_SHORT).show();
                tanggal=getDate();
                hari=getDay();
                Intent moveIntent = new Intent ();
                Bundle b = new Bundle();
                b.putString("tanggal", tanggal);
                b.putString("hari", hari);
                moveIntent.putExtras(b);
                setResult(12, moveIntent);
                finish();
            }
        });


    }

    private String getDate(){
        int hari;
        int bulan;
        int tahun;
        int coba;
        StringBuilder date = new StringBuilder();
        hari = datePicker.getDayOfMonth(); //Mendapatkan Input Hari dari DatePicker
        bulan = datePicker.getMonth() + 1; //Mendapatkan Input Bulan dari DatePicker
        tahun = datePicker.getYear(); //Mendapatkan Input Tahun dari DatePicker
        coba=datePicker.getFirstDayOfWeek();

        //Meambah Isi/Nilai pada Variable date(StringBuilder)
        if(hari<10 && bulan<10){
            date.append("0").append(hari).append("/0").append(bulan).append("/").append(tahun);
        }else if (bulan<10){
            date.append(hari).append("/0").append(bulan).append("/").append(tahun);
        }else if (hari<10){
            date.append("0").append(hari).append("/").append(bulan).append("/").append(tahun);
        }else{
            date.append(hari).append("/").append(bulan).append("/").append(tahun);
        }
        return date.toString();//Mengembalikan Nilai date
    }

    private String getDay(){
        int firstDayWeek;
        int hari;
        int hasil;
        String[] day = {
                "Senin", "Selasa", "Rabu","Kamis","Jum'at","Sabtu","Minggu"
        };
        firstDayWeek=datePicker.getFirstDayOfWeek();
        hari = datePicker.getDayOfMonth();

        if(hari<firstDayWeek){
            hasil=firstDayWeek-hari;
            hasil=7-hasil;
        }else{
            hasil=hari-firstDayWeek;
            hasil=hasil%7;
        }
        return day[hasil];
    }
}
