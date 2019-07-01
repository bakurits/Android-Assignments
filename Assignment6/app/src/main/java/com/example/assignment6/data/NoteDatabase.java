package com.example.assignment6.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;


import com.example.assignment6.App;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "app_database";

    private static NoteDatabase INSTANCE;

    private static final Object lock = new Object();

    public abstract NoteDao noteDao();

    static NoteDatabase getInstance() {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        App.getContext(),
                        NoteDatabase.class,
                        DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }

}
