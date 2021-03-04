package com.mmnuradityo.crud_room_db.database.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.mmnuradityo.crud_room_db.database.models.Mahasiswa;
import com.mmnuradityo.crud_room_db.database.models.Matkul;

import java.util.List;

/**
 * Created by pengdst on 04/03/2021
 * <p>
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengdst/
 */
public class MatkulWithMahasiswa {
    @Embedded public Matkul matkul;
    @Relation(
            parentColumn = "id",
            entityColumn = "mahasiswaId",
            associateBy = @Junction(MahasiswaMatkulCrossRef.class)
    )
    public List<Mahasiswa> mahasiswas;
}
