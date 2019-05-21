package com.example.assignment4.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DataDao {
    @Query("SELECT * FROM notes WHERE name LIKE :name")
    List<NoteEntity> getMatchedNotes(String name);

    @Insert
    void insertAll(NoteEntity... notes);

    @Delete
    void delete(NoteEntity user);

}
