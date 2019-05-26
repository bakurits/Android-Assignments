package com.example.assignment4.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.widget.ListView;

import java.util.List;

@Entity(tableName = "notes")
@TypeConverters({Converters.class})
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "content")
    private List<NoteItem> content;

    @ColumnInfo(name = "is_pinned")
    private boolean isPinned;

    public NoteEntity(String name, List<NoteItem> content) {
        this.content = content;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<NoteItem> getContent() {
        return content;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setContent(List<NoteItem> content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
