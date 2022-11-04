package com.example.kelompok5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = new Intent(this, ActivityMatch.class);
        //TODO
        //1.Menampilkan detail pemenang pertandingan berdasarkan score tertinggi data berasal dari MatchActivity
    }
}