package com.example.assignment4.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
@TypeConverters({Converters.class})
public interface DataDao {
    @Query("SELECT * FROM notes WHERE name LIKE :name")
    List<NoteEntity> getMatchedNotes(String name);

    @Query("SELECT * FROM notes")
    List<NoteEntity> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :id limit 1")
    NoteEntity getNote(int id);

    @Query("update notes set is_pinned=:pin WHERE id = :id")
    void pinNote(int id, boolean pin);

    @Query("update notes set is_pinned=:isPinned, name=:name, content=:content  WHERE id = :id")
    void updateEntity(int id, String name, List<NoteItem> content, boolean isPinned);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(NoteEntity dataModelEntity);

    @Delete
    void delete(NoteEntity user);

}
