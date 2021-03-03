package com.mmnuradityo.crud_room_db.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mmnuradityo.crud_room_db.database.models.Mahasiswa;

import java.util.List;

/**
 * Created on : 07/11/20
 * Author     : mmnuradityo
 * GitHub     : https://github.com/mmnuradityo
 */
@Dao
public interface MatkulDao {
    @Query("SELECT * FROM matkul")
    List<Mahasiswa> getAll();

    @Query("SELECT * FROM matkul WHERE id LIKE :matkulId LIMIT 1")
    Mahasiswa findById(int matkulId);

    @Update
    void update(Mahasiswa mahasiswa);

    @Insert
    void insert(Mahasiswa mahasiswa);

    @Delete
    void delete(Mahasiswa mahasiswa);
}
