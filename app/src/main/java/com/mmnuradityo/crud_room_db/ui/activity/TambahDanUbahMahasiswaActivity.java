package com.mmnuradityo.crud_room_db.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mmnuradityo.crud_room_db.BuildConfig;
import com.mmnuradityo.crud_room_db.CrudRoomApp;
import com.mmnuradityo.crud_room_db.R;
import com.mmnuradityo.crud_room_db.database.models.Mahasiswa;
import com.mmnuradityo.crud_room_db.database.dao.MahasiswaDao;
import com.mmnuradityo.crud_room_db.ui.common.DialogImageOptionsListener;
import com.mmnuradityo.crud_room_db.ui.dialog.CustomDialogImageOptions;

import java.io.File;
import java.io.IOException;

public class TambahDanUbahMahasiswaActivity extends AppCompatActivity implements View.OnClickListener,
        DialogImageOptionsListener {

    public final static String TAG_DATA_MAHASISWA = "data_mahasiswa";
    public final static int REQUEST_CAMERA = 101;
    public final static int REQUEST_GALLERY = 202;
    public final static int PICK_CAMERA = 1001;
    public final static int PICK_GALLERY = 2002;
    private EditText etNama, etNim;
    private Button btnTambah;
    private MahasiswaDao dao;
    private Mahasiswa mahasiswa;
    private ImageView imageView;
    private ImageView addImage;
    private RequestOptions requestOptions;
    private File fileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_dan_ubah_mahasiswa);
        dao = CrudRoomApp.getInstance().getDataBase().mahasiswaDao();

        if (getIntent() != null) {
            int id = getIntent().getIntExtra(TAG_DATA_MAHASISWA, 0);
            mahasiswa = dao.findById(id);
        }
        if (mahasiswa == null) {
            mahasiswa = new Mahasiswa();
        }

        requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(false)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.ic_account)
                .error(R.drawable.ic_account);

        etNama = findViewById(R.id.etNama);
        etNim = findViewById(R.id.etNim);
        btnTambah = findViewById(R.id.btn_tambah);
        imageView = findViewById(R.id.image);
        addImage = findViewById(R.id.add_image);

        btnTambah.setOnClickListener(this);
        addImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tambah:
                addOrUpdate();
                if (mahasiswa.getId() == 0) {
                    dao.insertData(mahasiswa);
                } else {
                    dao.update(mahasiswa);
                }

                Toast.makeText(this, btnTambah.getText().toString(), Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.add_image:
            default:
                new CustomDialogImageOptions(TambahDanUbahMahasiswaActivity.this,
                        TambahDanUbahMahasiswaActivity.this)
                        .show();
                break;
        }
    }

    private void addOrUpdate() {
        mahasiswa.setNama(etNama.getText().toString());
        mahasiswa.setNim(etNim.getText().toString());
    }

    @Override
    public void onCameraClick() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    @Override
    public void onGalleryClick() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            openCamera();
        } else if (requestCode == REQUEST_GALLERY) {
            openGallery();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        if (mahasiswa.getId() > 0) {
            etNama.setText(mahasiswa.getNama());
            etNim.setText(mahasiswa.getNim());
            btnTambah.setText("Ubah Data");
            loadImage(new File(mahasiswa.getGambar()));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String path;
            if (requestCode == PICK_GALLERY) {
                path = getRealPathFromUri(data.getData());
            } else {
                path = fileImage.getAbsolutePath();
            }

            mahasiswa.setGambar(path);
            loadImage(new File(path));
        }
    }

    private void openCamera() {
        try {
            fileImage = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg",
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            Uri imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", fileImage);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, PICK_CAMERA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("IntentReset")
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_GALLERY);
    }

    private void loadImage(File image) {
        if (image == null) return;

        Glide.with(this)
                .asBitmap()
                .apply(requestOptions)
                .load(image)
                .into(imageView);
    }

    public String getRealPathFromUri(Uri contentUri) {
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null
                , MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

}
