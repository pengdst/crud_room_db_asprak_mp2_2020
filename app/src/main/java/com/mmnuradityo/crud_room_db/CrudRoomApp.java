package com.mmnuradityo.crud_room_db;

import android.app.Application;

import androidx.room.Room;

import com.mmnuradityo.crud_room_db.database.AppDataBase;
import com.mmnuradityo.crud_room_db.database.DataBaseMigrations;

/**
 * Created on : 06/11/20
 * Author     : mmnuradityo
 * GitHub     : https://github.com/mmnuradityo
 */
public class CrudRoomApp extends Application {

    private static CrudRoomApp INSTANCE;
    private AppDataBase dataBase;

    public static CrudRoomApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(this, AppDataBase.class, "database_mahasiswa")
                .addMigrations(
                        DataBaseMigrations.MIGRATION_1_TO_2,
                        DataBaseMigrations.MIGRATION_2_TO_3
                )
                .allowMainThreadQueries()
                .build();

        INSTANCE = this;
    }

    public AppDataBase getDataBase() {
        return dataBase;
    }

}
