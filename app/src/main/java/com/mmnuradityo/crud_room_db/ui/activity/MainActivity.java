package com.mmnuradityo.crud_room_db.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mmnuradityo.crud_room_db.R;

public class MainActivity extends AppCompatActivity {

    private Button btnMahasiswaMatkul;
    private Button btnMahasiswa;
    private Button btnMatkul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMahasiswaMatkul = findViewById(R.id.btn_mahasiswa_matkul);
        btnMahasiswa = findViewById(R.id.btn_mahasiswa);
        btnMatkul = findViewById(R.id.btn_matkul);

        btnMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(getApplicationContext(), ListMahasiswaActivity.class);
                startActivity(intentMain);
            }
        });

        btnMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMatkul = new Intent(getApplicationContext(), ListMatkulActivity.class);
                startActivity(intentMatkul);
            }
        });

        btnMahasiswaMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMatkul = new Intent(getApplicationContext(), ListMahasiswaDanMatkulActivity.class);
                startActivity(intentMatkul);
            }
        });
    }
}