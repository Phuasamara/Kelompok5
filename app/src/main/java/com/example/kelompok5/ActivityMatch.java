package com.example.kelompok5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityMatch extends AppCompatActivity {
    private TextView textHome;

    private TextView textAway;

    private TextView scoreHome;

    private TextView scoreAway;

    private ImageView homeLogo;

    private ImageView awayLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        textHome = findViewById(R.id.txt_home);

        textAway = findViewById(R.id.txt_away);

        homeLogo = findViewById(R.id.home_logo);

        awayLogo = findViewById(R.id.away_logo);

        scoreHome = findViewById(R.id.score_home);

        scoreAway = findViewById(R.id.score_away);

        //TODO
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        Intent intent = getIntent();
        String home = intent.getStringExtra("home_team");
        textHome.setText(home);
        Bitmap imgHome = (Bitmap) intent.getParcelableExtra("image_home_bitmap");
        homeLogo.setImageBitmap(imgHome);

        String away = intent.getStringExtra("away_team");
        textAway.setText(away);
        Bitmap imgAway = (Bitmap) intent.getParcelableExtra("image_away_bitmap");
        awayLogo.setImageBitmap(imgAway);

    }

    public void handlerAddHome(View view) {
        int lastScore = Integer.parseInt(scoreHome.getText().toString());
        String score = String.valueOf(lastScore + 1);
        scoreHome.setText(score);
    }

    public void handlerAddAway(View view) {
        int lastScore = Integer.parseInt(scoreAway.getText().toString());
        String score = String.valueOf(lastScore + 1);
        scoreAway.setText(score);
    }

    public void handlerResult(View view) {
        Intent intent = new Intent(this, ActivityResult.class);
        int homeScore = Integer.parseInt(scoreHome.getText().toString());
        int awayScore = Integer.parseInt(scoreAway.getText().toString());
        if (homeScore > awayScore){
            scoreHome.setText("");
            String home = intent.getStringExtra("home_team");
            textHome.setText(home);
            Bitmap imgHome = (Bitmap) intent.getParcelableExtra("image_home_bitmap");
            homeLogo.setImageBitmap(imgHome);
        }if (awayScore > homeScore){
            scoreAway.setText("");
            String away = intent.getStringExtra("away_team");
            textAway.setText(away);
            Bitmap imgAway = (Bitmap) intent.getParcelableExtra("image_away_bitmap");
            awayLogo.setImageBitmap(imgAway);
        }if (scoreHome == scoreAway){
            intent.putExtra("home_team", "DRAW");
        }
        startActivity(intent);
    }
}