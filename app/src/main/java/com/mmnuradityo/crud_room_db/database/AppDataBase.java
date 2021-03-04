package com.mmnuradityo.crud_room_db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mmnuradityo.crud_room_db.database.dao.MahasiswaDao;
import com.mmnuradityo.crud_room_db.database.dao.MatkulDao;
import com.mmnuradityo.crud_room_db.database.models.Mahasiswa;
import com.mmnuradityo.crud_room_db.database.models.Matkul;
import com.mmnuradityo.crud_room_db.database.relations.MahasiswaMatkulCrossRef;

/**
 * Created on : 07/11/20
 * Author     : mmnuradityo
 * GitHub     : https://github.com/mmnuradityo
 */
@Database(entities = {Mahasiswa.class, Matkul.class, MahasiswaMatkulCrossRef.class}, version = 4)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MahasiswaDao mahasiswaDao();
    public abstract MatkulDao matkulDao();

}