package com.mmnuradityo.crud_room_db.database.relations;

import androidx.room.Embedded;
import androidx.room.Entity;
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

@Entity
public class MahasiswaWithMatkul {
    @Embedded
    public Mahasiswa mahasiswa;
    @Relation(
            parentColumn = "id",
            entityColumn = "id",
            associateBy = @Junction(
                    value=MahasiswaMatkulCrossRef.class,
                    parentColumn = "mahasiswaId",//variable name in your OwnerDogCrossRef
                    entityColumn = "matkulId")
    )
    public List<Matkul> matkuls;
}
