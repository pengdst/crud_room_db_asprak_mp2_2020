package com.mmnuradityo.crud_room_db.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mmnuradityo.crud_room_db.CrudRoomApp;
import com.mmnuradityo.crud_room_db.ui.adapter.RvAdapter;
import com.mmnuradityo.crud_room_db.ui.common.DataListListener;
import com.mmnuradityo.crud_room_db.R;
import com.mmnuradityo.crud_room_db.database.models.Mahasiswa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListMahasiswa;
    private FloatingActionButton fabTambah;
    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new RvAdapter();

        rvListMahasiswa = findViewById(R.id.rv_list_mahasiswa);
        fabTambah = findViewById(R.id.fab_tambah_data);

        rvListMahasiswa.setAdapter(adapter);

        adapter.setRemoveListener(new DataListListener() {
            @Override
            public void onRemoveClick(Mahasiswa mahasiswa) {
                adapter.removeData(mahasiswa);
            }
        });

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahDanUbahDataActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Mahasiswa> datas = CrudRoomApp.getInstance().getDataBase().mahasiswaDao().getAll();
        adapter.setData(datas);
    }

}
