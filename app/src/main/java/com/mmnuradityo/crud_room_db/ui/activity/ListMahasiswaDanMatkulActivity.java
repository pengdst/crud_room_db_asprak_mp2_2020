package com.mmnuradityo.crud_room_db.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mmnuradityo.crud_room_db.CrudRoomApp;
import com.mmnuradityo.crud_room_db.R;
import com.mmnuradityo.crud_room_db.database.dao.MahasiswaDao;
import com.mmnuradityo.crud_room_db.database.relations.MahasiswaWithMatkul;
import com.mmnuradityo.crud_room_db.ui.adapter.MahasiswaMatkulRvAdapter;
import com.mmnuradityo.crud_room_db.ui.common.DataClickListener;

import java.util.List;

public class ListMahasiswaDanMatkulActivity extends AppCompatActivity {

    private RecyclerView rvListMahasiswa;
    private MahasiswaMatkulRvAdapter adapter;
    private MahasiswaDao mahasiswaDao = CrudRoomApp.getInstance().getDataBase().mahasiswaDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa_dan_matkul);

        adapter = new MahasiswaMatkulRvAdapter();

        rvListMahasiswa = findViewById(R.id.rv_list_mahasiswa_matkul);
        rvListMahasiswa.setAdapter(adapter);

        adapter.setOnItemClickListener(new DataClickListener<MahasiswaWithMatkul>() {
            @Override
            public void onItemClick(View view, MahasiswaWithMatkul mahasiswaWithMatkul, int position) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<MahasiswaWithMatkul> list = mahasiswaDao.getMahasiswaWithMatkul();
        adapter.submitList(list);
    }
}