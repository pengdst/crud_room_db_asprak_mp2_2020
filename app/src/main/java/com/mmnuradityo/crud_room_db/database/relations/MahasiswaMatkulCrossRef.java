package com.mmnuradityo.crud_room_db.database.relations;

import androidx.room.Entity;

/**
 * Created by pengdst on 04/03/2021
 * <p>
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengdst/
 */

@Entity(primaryKeys = {"mahasiswaId", "matkulId"})
public class MahasiswaMatkulCrossRef {
    public int mahasiswaId;
    public int matkulId;
}
