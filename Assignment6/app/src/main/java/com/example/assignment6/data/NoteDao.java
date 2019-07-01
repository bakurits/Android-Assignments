package com.example.assignment6.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.assignment6.converters.Converters;

import java.util.List;

@Dao
@TypeConverters({Converters.class})
public interface NoteDao {
    @Query("SELECT * FROM notes where name like '%' || :name || '%' ORDER BY is_pinned DESC")
    List<Note> getMatchedNotes(String name);

    @Query("update notes set is_pinned=1 WHERE id = :id")
    void pinNote(int id);

    @Query("update notes set is_pinned=0 WHERE id = :id")
    void unpinNote(int id);

    @Update
    void update(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note dataModelEntity);

    @Delete
    void delete(Note user);

}
