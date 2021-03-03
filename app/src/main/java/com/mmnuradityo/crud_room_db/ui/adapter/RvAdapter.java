package com.mmnuradityo.crud_room_db.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mmnuradityo.crud_room_db.CrudRoomApp;
import com.mmnuradityo.crud_room_db.R;
import com.mmnuradityo.crud_room_db.database.models.Mahasiswa;
import com.mmnuradityo.crud_room_db.ui.activity.TambahDanUbahMahasiswaActivity;
import com.mmnuradityo.crud_room_db.ui.common.DataListListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on : 07/11/20
 * Author     : mmnuradityo
 * GitHub     : https://github.com/mmnuradityo
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private List<Mahasiswa> dataList = new ArrayList<>();
    private DataListListener listener;

    public void setData(List<Mahasiswa> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Mahasiswa data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.dataList.add(data);
                notifyItemInserted(this.dataList.size() - 1);
            } else {
                this.dataList.remove(position);
                this.dataList.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(Mahasiswa data) {
        int position = -1;

        if (!this.dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (this.dataList.get(i).getId() == data.getId()) {
                    position = i;
                }
            }
        }

        return position;
    }

    public void removeData(Mahasiswa data) {
        if (this.dataList.isEmpty()) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (this.dataList.get(i).getId() == data.getId()) {
                this.dataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void setRemoveListener(DataListListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RequestOptions requestOptions;
        private TextView tvNama, tvNim;
        private ImageView imageView;
        private Button btnHapus;
        private Mahasiswa data;
        private DataListListener listener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .circleCrop()
                    .placeholder(R.drawable.ic_account)
                    .error(R.drawable.ic_account);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNim = itemView.findViewById(R.id.tv_nim);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
            btnHapus.setOnClickListener(this);
        }

        void bind(Mahasiswa data, DataListListener listener) {
            this.data = data;
            this.listener = listener;

            tvNama.setText(data.getNama());
            tvNim.setText(data.getNim());

            loadImage(new File(data.getGambar()));
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_hapus) {

                CrudRoomApp.getInstance().getDataBase().mahasiswaDao().delete(data);
                listener.onRemoveClick(data);
                Toast.makeText(itemView.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();

            } else if (view.getId() == R.id.item_list) {

                Intent intent = new Intent(itemView.getContext(), TambahDanUbahMahasiswaActivity.class);
                intent.putExtra(TambahDanUbahMahasiswaActivity.TAG_DATA_INTENT, data.getId());
                itemView.getContext().startActivity(intent);

            }
        }

        private void loadImage(File image) {
            if (image == null) return;

            Glide.with(itemView.getContext())
                    .asBitmap()
                    .apply(requestOptions)
                    .load(image)
                    .into(imageView);
        }
    }

}
