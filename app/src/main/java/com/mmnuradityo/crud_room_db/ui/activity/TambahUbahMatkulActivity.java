package com.mmnuradityo.crud_room_db.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;
import com.mmnuradityo.crud_room_db.CrudRoomApp;
import com.mmnuradityo.crud_room_db.R;
import com.mmnuradityo.crud_room_db.database.dao.MatkulDao;
import com.mmnuradityo.crud_room_db.database.models.Matkul;

public class TambahUbahMatkulActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNamaMatkul, etSemester;
    private Slider sliderSks;
    private Button btnTambah;
    private Matkul matkul;
    private MatkulDao dao = CrudRoomApp.getInstance().getDataBase().matkulDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ubah_matkul);

        if (getIntent() != null) {
            int id = getIntent().getIntExtra(ListMatkulActivity.TAG_DATA_MATKUL, 0);
            matkul = dao.findById(id);
        }
        if (matkul == null) {
            matkul = new Matkul();
        }

        etNamaMatkul = findViewById(R.id.et_nama_matkul);
        etSemester = findViewById(R.id.et_semester_matkul);
        sliderSks = findViewById(R.id.slide_sks);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah:
                addOrUpdate();
                if (matkul.getId() == 0) {
                    dao.insert(matkul);
                } else {
                    dao.update(matkul);
                }

                Toast.makeText(this, btnTambah.getText().toString(), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (matkul.getId() > 0) {
            etNamaMatkul.setText(matkul.getNama());
            etSemester.setText(matkul.getSemester());
            sliderSks.setValue(matkul.getSks());
            btnTambah.setText("Ubah Data");
        }
    }

    private void addOrUpdate() {
        matkul.setNama(etNamaMatkul.getText().toString());
        matkul.setSks(sliderSks.getValue());
        matkul.setSemester(etSemester.getText().toString());
    }
}