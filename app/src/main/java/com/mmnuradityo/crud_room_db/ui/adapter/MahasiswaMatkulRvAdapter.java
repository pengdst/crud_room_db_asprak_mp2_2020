package com.mmnuradityo.crud_room_db.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mmnuradityo.crud_room_db.R;
import com.mmnuradityo.crud_room_db.database.models.Matkul;
import com.mmnuradityo.crud_room_db.database.relations.MahasiswaWithMatkul;
import com.mmnuradityo.crud_room_db.ui.common.DataClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengdst on 04/03/2021
 * <p>
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengdst/
 */
public class MahasiswaMatkulRvAdapter extends RecyclerView.Adapter<MahasiswaMatkulRvAdapter.ViewHolder> {

    private List<MahasiswaWithMatkul> list = new ArrayList<>();
    private DataClickListener<MahasiswaWithMatkul> listener;
    private DataClickListener<Matkul> subListener;

    public void submitList(List<MahasiswaWithMatkul> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            MahasiswaWithMatkul data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.list.add(data);
                notifyItemInserted(this.list.size() - 1);
            } else {
                this.list.remove(position);
                this.list.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(MahasiswaWithMatkul data) {
        int position = -1;

        if (!this.list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (this.list.get(i) == data) {
                    position = i;
                }
            }
        }

        return position;
    }

    public void removeData(MahasiswaWithMatkul data) {
        if (this.list.isEmpty()) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (this.list.get(i) == data) {
                this.list.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void setOnItemClickListener(DataClickListener<MahasiswaWithMatkul> listener) {
        this.listener = listener;
    }

    public void setOnSubItemClickListener(DataClickListener<Matkul> listener) {
        this.subListener = listener;
    }

    @NonNull
    @Override
    public MahasiswaMatkulRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MahasiswaMatkulRvAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa_matkul, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaMatkulRvAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvNama, tvNim;
        private final Button btnHapus;
        private final ImageView image;
        private final RecyclerView rvListMatkul;
        private MahasiswaWithMatkul data;
        private DataClickListener<MahasiswaWithMatkul> listener;
        private MatkulRvAdapter adapter;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            adapter = new MatkulRvAdapter();

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNim = itemView.findViewById(R.id.tv_nim);
            image = itemView.findViewById(R.id.image);
            rvListMatkul = itemView.findViewById(R.id.rv_list_matkul);
            btnHapus = itemView.findViewById(R.id.btn_hapus);

            rvListMatkul.setAdapter(adapter);

            itemView.setOnClickListener(this);
            btnHapus.setOnClickListener(this);
        }

        void bind(MahasiswaWithMatkul data, DataClickListener<MahasiswaWithMatkul> listener) {
            this.data = data;
            this.listener = listener;

            tvNama.setText(data.mahasiswa.getNama());
            tvNim.setText("Banyak SKS " + data.mahasiswa.getNim());

            adapter.submitList(data.matkuls);
        }

        void collapseListMatkul(){
            if (rvListMatkul.getVisibility() == View.VISIBLE){
                rvListMatkul.setVisibility(View.GONE);
            }else {
                rvListMatkul.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View view) {
            collapseListMatkul();
            if (listener != null) listener.onItemClick(view, data, getAdapterPosition());
        }
    }

}