package com.example.assignment6.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "notes")
@TypeConverters({Converters.class})
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "content")
    private List<Item> content;

    @ColumnInfo(name = "is_pinned")
    private boolean isPinned;

    public Note(String name, List<Item> content) {
        this.content = content;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Item> getContent() {
        return content;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setContent(List<Item> content) {
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
