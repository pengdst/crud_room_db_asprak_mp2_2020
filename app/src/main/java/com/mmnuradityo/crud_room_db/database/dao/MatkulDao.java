package com.mmnuradityo.crud_room_db.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mmnuradityo.crud_room_db.database.models.Matkul;
import com.mmnuradityo.crud_room_db.database.relations.MahasiswaMatkulCrossRef;

import java.util.List;

/**
 * Created by pengdst on 03/03/2021
 * <p>
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengdst/
 */
@Dao
public interface MatkulDao {
    @Query("SELECT * FROM matkul")
    List<Matkul> getAll();

//    @Transaction
//    @Query("SELECT * FROM matkul")
//    public List<MatkulWithMahasiswa> getMatkulWithMahasiswa();

    @Query("SELECT * FROM matkul WHERE id LIKE :matkulId LIMIT 1")
    Matkul findById(int matkulId);

    @Update
    void update(Matkul matkul);

    @Insert
    void insert(Matkul matkul);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MahasiswaMatkulCrossRef crossRef);

    @Delete
    void delete(Matkul matkul);
}
