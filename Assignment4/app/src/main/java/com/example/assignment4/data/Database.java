package com.example.assignment4.data;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;


import com.example.assignment4.App;


public abstract class Database extends RoomDatabase {

    private static final String DATABASE_NAME = "app_database";

    private static Database INSTANCE;

    private static final Object lock = new Object();

    public abstract DataDao dataDao();

    public static Database getInstance() {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        App.getContext(),
                        Database.class,
                        DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }

}
