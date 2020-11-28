package com.mmnuradityo.crud_room_db.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created on : 07/11/20
 * Author     : mmnuradityo
 * GitHub     : https://github.com/mmnuradityo
 */
@Database(entities = {Mahasiswa.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MahasiswaDao mahasiswaDao();

}