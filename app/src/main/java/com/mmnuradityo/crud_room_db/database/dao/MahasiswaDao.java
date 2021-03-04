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
public interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    List<Mahasiswa> getAll();

    @Query("SELECT * FROM matkul " +
            "JOIN mahasiswamatkulcrossref mref ON mref.matkulId = matkul.id " +
            "WHERE mref.mahasiswaId LIKE :id")
    List<Matkul> getMatkulByMahasiswa(int id);

    @Query("SELECT * FROM mahasiswa WHERE id LIKE :mahasiswaId LIMIT 1")
    Mahasiswa findById(int mahasiswaId);

    @Update
    void update(Mahasiswa mahasiswa);

    @Insert
    void insertData(Mahasiswa mahasiswa);

    @Delete
    void delete(Mahasiswa mahasiswa);
}
