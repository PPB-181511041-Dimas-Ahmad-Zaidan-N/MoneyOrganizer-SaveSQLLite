package com.dimas.moneyorganizer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class TambahTransaksi extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String inputHari, inputTanggal, inputPendapatan, inputPengeluaran;
    Double pendapatan, pengeluaran;
    int dataPendapatan, dataPengeluaran;
    Spinner spinner;
    String kategori;

    private Button btnDatePicker;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_transaksi);

//        final EditText txtHari=(EditText)findViewById(R.id.et_hari);
//        final EditText txtTanggal=(EditText)findViewById(R.id.et_tanggal);
        final EditText txtPendapatan=(EditText)findViewById(R.id.et_pendapatan);
        final EditText txtPengeluaran=(EditText)findViewById(R.id.et_pengeluaran);
        spinner = findViewById(R.id.spinner_kategori);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kategori,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnDatePicker = findViewById(R.id.btn_pilih_tanggal);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(TambahTransaksi.this, PilihTanggal.class);
                startActivityForResult(moveIntent,12);
            }
        });

        Button b = (Button) findViewById(R.id.btn_selesai);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                inputHari = txtHari.getText().toString();
//                inputTanggal=txtTanggal.getText().toString();
                inputPendapatan=txtPendapatan.getText().toString();
                inputPengeluaran=txtPengeluaran.getText().toString();
                pendapatan=toDouble(inputPendapatan);
                pengeluaran=toDouble(inputPengeluaran);

                dataPendapatan=pendapatan.intValue();
                dataPengeluaran=pengeluaran.intValue();
                Intent moveIntent = new Intent ();
                Bundle b = new Bundle();

                b.putString("hari", inputHari);
                b.putString("tanggal", inputTanggal);
                b.putInt("pendapatan", dataPendapatan);
                b.putInt("pengeluaran", dataPengeluaran);
                moveIntent.putExtras(b);
                setResult(12, moveIntent);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==12){
            Bundle b = data.getExtras();
            inputTanggal=b.getString("tanggal");
            inputHari=b.getString("hari");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private Double toDouble(String str){
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e){
            return null;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        kategori = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), kategori,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
