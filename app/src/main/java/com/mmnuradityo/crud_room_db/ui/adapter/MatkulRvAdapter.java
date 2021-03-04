package com.mmnuradityo.crud_room_db.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mmnuradityo.crud_room_db.R;
import com.mmnuradityo.crud_room_db.database.models.Matkul;
import com.mmnuradityo.crud_room_db.database.models.Matkul;
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
public class MatkulRvAdapter extends RecyclerView.Adapter<MatkulRvAdapter.ViewHolder> {

    private List<Matkul> list = new ArrayList<>();
    private DataClickListener<Matkul> listener;

    public void submitList(List<Matkul> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Matkul data = dataList.get(i);
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

    private int findPosition(Matkul data) {
        int position = -1;

        if (!this.list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (this.list.get(i).getId() == data.getId()) {
                    position = i;
                }
            }
        }

        return position;
    }

    public void removeData(Matkul data) {
        if (this.list.isEmpty()) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (this.list.get(i).getId() == data.getId()) {
                this.list.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void setOnItemClickListener(DataClickListener<Matkul> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MatkulRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MatkulRvAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matkul, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulRvAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvNamaMatkul, tvSks, tvSemester;
        private final Button btnHapus;
        private Matkul data;
        private DataClickListener<Matkul> listener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaMatkul = itemView.findViewById(R.id.tv_nama_matkul);
            tvSks = itemView.findViewById(R.id.tv_sks);
            tvSemester = itemView.findViewById(R.id.tv_semester);
            btnHapus = itemView.findViewById(R.id.btn_hapus);

            itemView.setOnClickListener(this);
            btnHapus.setOnClickListener(this);
        }

        void bind(Matkul data, DataClickListener<Matkul> listener) {
            this.data = data;
            this.listener = listener;

            tvNamaMatkul.setText(data.getNama());
            tvSks.setText("Banyak SKS " + data.getSks());
            tvSemester.setText("Semester " + data.getSemester());

        }

        @Override
        public void onClick(View view) {
            if (listener != null) listener.onItemClick(view, data, getAdapterPosition());
        }
    }

}