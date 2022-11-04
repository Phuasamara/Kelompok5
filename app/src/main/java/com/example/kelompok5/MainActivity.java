package com.example.kelompok5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE_IMAGE_ONE = 1;
    private static final int GALLERY_REQUEST_CODE_IMAGE_TWO = 2 ;
    private static final String TAG = MainActivity.class.getCanonicalName();

    private EditText Hometext;
    private EditText Awaytext;
    private String textHome;
    private String textAway;
    public Bitmap bitmap;
    private ImageView imgHome;
    private ImageView imgAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team (tidak boleh input kosong, tidak boleh sama dengan input away, dilakukan di handlerNext)
        //2. Validasi Input Away Team (tidak boleh input kosong, tidak boleh sama dengan input home, dilakukan di handlerNext)

        imgHome = findViewById(R.id.home_logo);
        imgAway = findViewById(R.id.away_logo);
        Hometext = findViewById(R.id.home_team);
        textHome = Hometext.getText().toString();
        Awaytext = findViewById(R.id.away_team);
        textAway = Awaytext.getText().toString();

    }

    public void handlerNext(View view) {

        textHome = Hometext.getText().toString();
        textAway = Awaytext.getText().toString();

        if(Hometext.getText().toString().length()==0){
            Hometext.setError("Data Tidak Boleh kosong!");
            return;
        }else if(Awaytext.getText().toString().length()==0){
            Awaytext.setError("Data Tidak Boleh kosong!");
            return;
        }else if(textHome == textAway){
            Hometext.setError("Data Tidak Boleh sama!");
            return;
        }else {
            Toast.makeText(getApplicationContext(), "Data Berhasil!",
                    Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(this, ActivityMatch.class);
        intent.putExtra("home_team", Hometext.getText().toString());
        intent.putExtra("away_team", Awaytext.getText().toString());
        // past image home to next activity
        if(imgHome != null){
            imgHome.buildDrawingCache();
            Bitmap bitmaphome = imgHome.getDrawingCache();
            intent.putExtra("image_home_bitmap", bitmaphome);
        }if(imgAway != null){
            imgAway.buildDrawingCache();
            Bitmap bitmapaway = imgAway.getDrawingCache();
            intent.putExtra("image_away_bitmap", bitmapaway);
        }

        startActivity(intent);
    }

    public void handlerImageHome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE_IMAGE_ONE);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        // Request image dari gallery
        if (resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    // if img home / set image
                    if(requestCode == GALLERY_REQUEST_CODE_IMAGE_ONE){
                        imgHome.setImageBitmap(bitmap);
                    }if(requestCode == GALLERY_REQUEST_CODE_IMAGE_TWO) {
                        imgAway.setImageBitmap(bitmap);
                    }
                    // if img away / set image
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handlerImageAway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE_IMAGE_TWO);
    }
}