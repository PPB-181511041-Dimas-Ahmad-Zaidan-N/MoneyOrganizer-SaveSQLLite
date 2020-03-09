package com.dimas.moneyorganizer;

import java.util.ArrayList;

public class MoneyTrackData {
    private static String[] hari = {
            "Senin", "Selasa", "Rabu","Kamis","Jum'at","Sabtu","Minggu","Senin"
    };

    private static String[] tanggal = {
            "02/03/2020","03/03/2020","04/03/2020","05/03/2020",
            "06/03/2020","07/03/2020" ,"08/03/2020","09/03/2020"
    };

    private static int[] pendapatan = {
            10000,20000,30000,40000,50000,60000,70000,80000
    };

    private static int[] pengeluaran = {
            10000,20000,30000,40000,50000,60000,70000,80000
    };

    static ArrayList<MoneyTrack> getListData(){
        ArrayList<MoneyTrack> list =new ArrayList<>();
        for (int position=0; position<hari.length; position++){
            MoneyTrack moneyTrack=new MoneyTrack();
            moneyTrack.setHari(hari[position]);
            moneyTrack.setTanggal(tanggal[position]);
            moneyTrack.setPendapatan(pendapatan[position]);
            moneyTrack.setPengeluaran(pengeluaran[position]);
            list.add(moneyTrack);
        }


        return list;
    }



}
