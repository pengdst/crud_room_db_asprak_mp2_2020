package com.mmnuradityo.crud_room_db.database.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by pengdst on 03/03/2021
 * <p>
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengdst/
 */
@Entity
public class Matkul {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "nama")
    private String nama = "";

    @ColumnInfo(name = "sks")
    private Float sks = 0F;

    @ColumnInfo(name = "semester")
    private String semester = "";

    public Matkul() {
    }

    public Matkul(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Float getSks() {
        return sks;
    }

    public void setSks(Float sks) {
        this.sks = sks;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
